import java.util.*;
public class Runner{
	public static void main(String args[]){
		Player white = null;
		Player black = null;
		Board chessBoard = new Board();
		int drawLimit = 8;
		while(white == null){
			Scanner scantron = new Scanner(System.in);
			System.out.println("Enter the type of player for white");
			String out = scantron.next();
			if(out.toUpperCase().equals("HUMAN")){
				white = new HumanPlayer('W');
			}
			if(out.toUpperCase().equals("AI")){
				int a = 10;
				while(a == 10){
					Scanner scantron2 = new Scanner(System.in);
					System.out.println("Enter the ply for the AI");
					String out2 = scantron.next();
					if(out2.equals("2")){
						white = new AIPlayer('W', 2);
						break;
					}
					else if(out2.equals("4")){
						white = new AIPlayer('W', 4);
						break;
					}
					else if(out2.equals("6")){
						white = new AIPlayer('W', 6);
						break;
					}
					else if(out2.toLowerCase().equals("quit")){
						System.exit(0);
					}
					System.out.println("Sorry, I didnt get that");
				}
			}
			if(out.toLowerCase().equals("quit")){
				System.exit(0);
			}
		}
		while(black == null){
			Scanner scantron = new Scanner(System.in);
			System.out.println("Enter the type of player for black");
			String out = scantron.next();
			if(out.toUpperCase().equals("HUMAN")){
				black = new HumanPlayer('B');
			}
			if(out.toUpperCase().equals("AI")){
				int a = 10;
				while(a == 10){
					Scanner scantron2 = new Scanner(System.in);
					System.out.println("Enter the ply for the AI");
					String out2 = scantron.next();
					if(out2.equals("2")){
						black = new AIPlayer('B', 2);
						break;
					}
					else if(out2.equals("4")){
						black = new AIPlayer('B', 4);
						break;
					}
					else if(out2.equals("6")){
						black = new AIPlayer('B', 6);
						break;
					}
					else if(out2.toLowerCase().equals("quit")){
						System.exit(0);
					}
					System.out.println("Sorry, I didnt get that");
				}
			}
			if(out.toLowerCase().equals("quit")){
				System.exit(0);
			}
		}
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
