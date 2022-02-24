package game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import model.RoundInfo;
import utils.OperUtils;

public class Main {
	private static final Random digitRandom = new Random();
	private static final String AGGIN_STRING = "Try it again!";
	private static final String SPILITOR_STRING = "###########################";

	public static void main(String[] args) {
		while (true) {
			Scanner scanner = new Scanner(System.in);
			String[] players = OperUtils.getPlayers(scanner);
			System.out.println(SPILITOR_STRING);
			List<RoundInfo> dataList = OperUtils.getData(scanner, players, digitRandom);
			System.out.println(SPILITOR_STRING);
			OperUtils.analyseWinner(dataList);
			System.out.println(SPILITOR_STRING);
			int score = OperUtils.analyseScore(players, dataList);
			System.out.println(SPILITOR_STRING);
			OperUtils.getOverall(players, dataList, score);
			System.out.println(SPILITOR_STRING);
			System.out.println(AGGIN_STRING);
		}
	}

}
