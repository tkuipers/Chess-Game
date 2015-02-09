import java.util.*;
public class Runner{
	public static void main(String args[]){
		Player white = new AIPlayer('W', 4);
		Player black = new AIPlayer('B', 2);
		Board chessBoard = new Board();
		// int a = 10;
		int prevValue = 1400;
		int counter = 0;
		while(counter < 20){
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
		if(counter >= 20){
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