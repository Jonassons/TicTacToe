
import logic.TacTacLogic;
import minmax.MinMax;
import view.Board;

public class Main {
	public static void main(String[] args) {
		MinMax minMax = new MinMax();
		TacTacLogic gameLogic = new TacTacLogic(minMax);
		Board board = new Board(gameLogic, minMax);
		board.initializeGame();
	}
}
