import java.util.*;
public class Runner{
	public static void main(String args[]){
		Player white = new AIPlayer('W');
		Player black = new AIPlayer('B');
		Board chessBoard = new Board();
		int a = 10;
		while(a==10){
			// System.out.println("chessBoard Looks like: ");
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