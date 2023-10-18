package view;

import logic.TacTacLogic;
import minmax.MinMax;

import java.util.Scanner;

public class Board {
	private final TacTacLogic gameLogic;
	private final MinMax minMax;

	public Board(TacTacLogic gameLogic, MinMax minMax) {
		this.gameLogic = gameLogic;
		this.minMax = minMax;
	}

	public int[] getSuggestedMove() {
		return minMax.findBestMove(gameLogic, true, 9);
	}

	public void initializeGame() {
		gameLogic.resetGame(); // You can add a method to reset the game state
		playGame();
	}

	public void playGame() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			gameLogic.printBoard();
			System.out.println("Enter row and column (e.g., 1 2): ");
			int row = scanner.nextInt();
			int col = scanner.nextInt();

			if (gameLogic.isValidMove(row, col)) {
				gameLogic.placeMark('X', row, col);

				if (gameLogic.isXWinner()) {
					System.out.println("Player Wins!");
					break;
				} else if (gameLogic.isDraw()) {
					System.out.println("It's a Draw!");
					break;
				}

				gameLogic.makeComputerMove();
				System.out.println(
						"Best move for you: Row = " + getSuggestedMove()[0] + ", Col = " + getSuggestedMove()[1]);
				if (gameLogic.isOWinner()) {
					System.out.println("Computer Wins!");
					break;
				} else if (gameLogic.isDraw()) {
					System.out.println("It's a Draw!");
					break;
				}
			} else {
				System.out.println("Invalid move. Try again.");
			}
		}

		scanner.close();
	}
}
