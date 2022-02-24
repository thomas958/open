package model;

public class RoundInfo {
	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public String getInputNum() {
		return inputNum;
	}

	public void setInputNum(String inputNum) {
		this.inputNum = inputNum;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	private String round;
	private String inputNum;
	private String randomNum;
}
