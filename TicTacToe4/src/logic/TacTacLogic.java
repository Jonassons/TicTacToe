package logic;

import interfaces.BoardOperations;
import minmax.MinMax;
import view.Board;

public class TacTacLogic implements BoardOperations {
	private final MinMax minMax;
	private final char[][] gameState;

	public TacTacLogic(MinMax minMax) {
		this.minMax = minMax;
		this.gameState = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameState[i][j] = ' ';
			}
		}
	}

	public void updateGameState(char mark, int row, int col) {
		gameState[row][col] = mark;
	}

	@Override
	public boolean isValidMove(int row, int col) {
		return gameState[row][col] == ' ';
	}

	@Override
	public void placeMark(char mark, int row, int col) {
		gameState[row][col] = mark;
	}

	@Override
	public int isWinner() {
		if (isXWinner()) {
			return 10;
		}
		if (isOWinner()) {
			return -10;
		}
		return 0; // default
	}

	@Override
	public boolean isDraw() {
		if (isWinner() != 0) {
			return false;
		}

		return !hasEmptyCells();
	}

	@Override
	public boolean isXWinner() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (gameState[i][0] == 'X' && gameState[i][1] == 'X' && gameState[i][2] == 'X') {
				return true;
			}
		}
		// Columns
		for (int i = 0; i < 3; i++) {
			if (gameState[0][i] == 'X' && gameState[1][i] == 'X' && gameState[2][i] == 'X') {
				return true;
			}
		}
		// Diagonals
		if (gameState[0][0] == 'X' && gameState[1][1] == 'X' && gameState[2][2] == 'X') {
			return true;
		}
		if (gameState[0][2] == 'X' && gameState[1][1] == 'X' && gameState[2][0] == 'X') {
			return true;
		}
		return false;
	}

	@Override
	public boolean isOWinner() {
		// Check rows
		for (int i = 0; i < 3; i++) {
			if (gameState[i][0] == 'O' && gameState[i][1] == 'O' && gameState[i][2] == 'O') {
				return true;
			}
		}
		// Check columns
		for (int i = 0; i < 3; i++) {
			if (gameState[0][i] == 'O' && gameState[1][i] == 'O' && gameState[2][i] == 'O') {
				return true;
			}
		}
		// Check diagonals
		if (gameState[0][0] == 'O' && gameState[1][1] == 'O' && gameState[2][2] == 'O') {
			return true;
		}
		if (gameState[0][2] == 'O' && gameState[1][1] == 'O' && gameState[2][0] == 'O') {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasEmptyCells() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameState[i][j] == ' ') {
					return true;
				}
			}
		}
		return false;
	}

	public void makeComputerMove() {
		int[] bestMove = minMax.findBestMove(this, false, 9);

		if (bestMove[0] != -1 && bestMove[1] != -1) {
			// You can print the computer's move to the console
			System.out.println("Computer moves to row " + bestMove[0] + " and column " + bestMove[1]);
			placeMark('O', bestMove[0], bestMove[1]);
		} else {
			System.out.println("No valid move found.");
		}
	}

	@Override
	public void removeMark(int row, int col) {
		gameState[row][col] = ' ';

	}

	public void printBoard() {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(gameState[i][j] + " | ");
			}
			System.out.println("\n-------------");
		}
	}

	public void resetGame() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameState[i][j] = ' ';
			}
		}
	}

	@Override
	public void makeComputerMove(Board board) {
		// TODO Auto-generated method stub

	}

}