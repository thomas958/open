package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.RoundInfo;

public final class OperUtils {
	private static final String TITLETIP_STRING = "Please enter player name:";
	private static final int PLAYERNUM = 5;
	private static final int GUESSROUNDS = 5;
	private static final String ROUND_STRING = "round %s";
	private static final String GUESS_STRING = "please enter your guess player %s:";
	private static final String GUESSNUMBER_STRING = "  Guess Number for round %s:%s";
	private static final String WINNER_STRING = "Winner/s";
	private static final String PLAYER_STRING = "  Player: ";
	private static final String NOWINNER_STRING = "No winner";
	private static final String PLAYERNAME_STRING = "Player name:%s";
	private static final String ROUNDGUESSCORRECT_STRING = "Round %s Guess value:%s Correct value:%s";
	private static final String YOURSCORE_STRING = "Your score is:%s";
	private static final String OVERALLWINNER_STRING = "The Overall winner/s:";
	private static final String PLAYER_S_STRING = "Player:%s";
	
	private OperUtils() {}
	
	public static String[] getPlayers(Scanner scanner) {
		String[] players = new String[PLAYERNUM];
		for (int i = 0; i < players.length; i++) {
			System.out.print(TITLETIP_STRING);
			players[i] = scanner.nextLine();
		}
		Arrays.sort(players);
		return players;
	}
	
	public static List<RoundInfo> getData(Scanner scanner, String[] players, Random digitRandom) {
		List<RoundInfo> tempList = new ArrayList<>();
		for (int k = 1; k <= GUESSROUNDS; k++) {
			int randomDigit = digitRandom.nextInt(10) + 1;
			System.out.println(String.format(ROUND_STRING, k));
			String[] guesses = new String[PLAYERNUM];
			for (int i = 0; i < players.length; i++) {
				System.out.print(String.format(GUESS_STRING, players[i]));
				guesses[i] = scanner.nextLine();
				RoundInfo roundInfo = new RoundInfo();
				roundInfo.setInputNum(guesses[i]);
				roundInfo.setPlayerName(players[i]);
				roundInfo.setRandomNum(String.valueOf(randomDigit));
				roundInfo.setRound(String.valueOf(k));
				tempList.add(roundInfo);
			}
		}
		return tempList;
	}
	
	public static void analyseWinner(List<RoundInfo> tempList) {
		for (int j = 1; j <= GUESSROUNDS; j++) {
			String tempString = String.valueOf(j);
			System.out.println(String.format(GUESSNUMBER_STRING, j, tempList.stream()
					.filter(x -> x.getRound().equals(tempString)).findFirst().get().getRandomNum()));

			int icount = tempList.stream()
					.filter(x -> x.getRound().equals(tempString) && x.getInputNum().equals(x.getRandomNum()))
					.collect(Collectors.toList()).size();
			if (icount > 0) {
				System.out.println(WINNER_STRING);
				tempList.stream()
						.filter(x -> x.getRound().equals(tempString) && x.getInputNum().equals(x.getRandomNum()))
						.map(x -> PLAYER_STRING + x.getPlayerName()).forEach(System.out::println);
			} else {
				System.out.println(NOWINNER_STRING);
			}
		}
	}
	
	public static int analyseScore(String[] players, List<RoundInfo> tempList) {
		int score = 0;
		for (int m = 0; m < players.length; m++) {
			String tempString = players[m];
			System.out.println(String.format(PLAYERNAME_STRING, tempString));
			tempList.stream().filter(x -> x.getPlayerName().equals(tempString)).map(x -> String
					.format(ROUNDGUESSCORRECT_STRING, x.getRound(), x.getInputNum(), x.getRandomNum()))
					.forEach(System.out::println);
			int tempScore = tempList.stream()
					.filter(x -> x.getPlayerName().equals(tempString) && x.getInputNum().equals(x.getRandomNum()))
					.collect(Collectors.toList()).size();
			if (tempScore > score) {
				score = tempScore;
			}
			System.out.println(String.format(YOURSCORE_STRING, tempScore));
		}
		return score;
	}
	
	public static void getOverall(String[] players, List<RoundInfo> tempList, int score) {
		List<String> winnerStrings = new ArrayList<>();
		for (int m = 0; m < PLAYERNUM; m++) {
			String tempString = players[m];
			int tempScore = tempList.stream()
					.filter(x -> x.getPlayerName().equals(tempString) && x.getInputNum().equals(x.getRandomNum()))
					.collect(Collectors.toList()).size();

			if (tempScore == score && score > 0) {
				winnerStrings.add(tempString);
			}
		}

		System.out.println(OVERALLWINNER_STRING);
		System.out.println(String.format(PLAYER_S_STRING, Arrays.toString(winnerStrings.toArray())));
	}
	
}
