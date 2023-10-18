package interfaces;

import view.Board;

public interface BoardOperations {

	boolean isValidMove(int row, int col);

	void placeMark(char mark, int row, int col);

	int isWinner();

	boolean isDraw();

	boolean isXWinner();

	boolean isOWinner();

	boolean hasEmptyCells();

	void makeComputerMove(Board board);

	void removeMark(int row, int col);
}