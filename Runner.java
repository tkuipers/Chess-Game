import java.util.*;
public class Runner{
	public static void main(String args[]){
		Player white = new HumanPlayer('W');
		Player black = new AIPlayer('B');
		Board chessBoard = new Board();
		int drawLimit = 5;
		// int a = 10;
		int prevValue = 1400;
		int counter = 0;
		while(counter < drawLimit){
			if(chessBoard.getWinner() != prevValue){
				prevValue = chessBoard.getWinner();
				counter = 0;
			}
			else{
				counter++;
			}
			System.out.println(chessBoard);
			chessBoard = white.promptUser(chessBoard);
			if(chessBoard == null){
				break;
			}
			if(chessBoard.gameOver() != null){
				break;
			}
			// System.out.println("ChessBoard looks like: ");
			System.out.println(chessBoard);
			chessBoard = black.promptUser(chessBoard);
			if(chessBoard == null){
				break;
			}
		}
		if(counter >= drawLimit){
			System.out.println("It Was a Draw");
			System.exit(0);
		}
		if(chessBoard != null){
			if(chessBoard.gameOver() == chessBoard.getBlack()){
				System.out.println(chessBoard);
				System.out.println("Black Wins");
			}
			else if(chessBoard.gameOver() == chessBoard.getWhite()){
				System.out.println(chessBoard);
				System.out.println("White wins");
			}
		}
		else{
			System.out.println("error");
		}
		
	}
}
