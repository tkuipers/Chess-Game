import java.util.*;
public class Runner{
	public static void main(String args[]){
		Board chessBoard = new Board();
		while(chessBoard.gameOver() == null){
			System.out.println(chessBoard);
			Board tempBoard = promptUser("Enter A Move", chessBoard, chessBoard.getBlack());
			chessBoard = tempBoard;
		}
		if(chessBoard.gameOver() == chessBoard.getBlack()){
			System.out.println("Black Wins");
		}
		else if(chessBoard.gameOver() == chessBoard.getWhite()){
			System.out.println("White wins");
		}
		
	}
	public static Board promptUser(String in, Board board, Team curTeam){
		Scanner scantron = new Scanner(System.in);
		System.out.println(in);
		String out = scantron.next();
		Move move = toMove(out, board);
		Board outBoard = new Board(board, move, curTeam);
		return outBoard;
		// System.out.println();
	}
	public static Move toMove(String move, Board board){
		if(move.length() == 4){
			char[] moveParse = move.toUpperCase().toCharArray();
			int firstCol  = letterToNum(moveParse[0]); 
			int firstRow = letterToNum(moveParse[1]);
			int secondCol = letterToNum(moveParse[2]);
			int secondRow = letterToNum(moveParse[3]);
			Position oldPos = new Position(firstRow, firstCol);
			Position newPos = new Position(secondRow, secondCol);
			Piece curPiece = board.getCurrentBoard()[firstRow][firstCol];
			return new Move(newPos, curPiece, oldPos);
		}
		else{
			System.out.println("Not a valid move.  The format is L5N6.");
			return null;
		}

	}
	public static int letterToNum(char in){
		if(in == 'A'){
			return 0;
		}
		if(in == 'B'){
			return 1;
		}
		if(in == 'C'){
			return 2;
		}
		if(in == 'D'){
			return 3;
		}
		if(in == 'E'){
			return 4;
		}
		if(in == 'F'){
			return 5;
		}
		if(in == 'G'){
			return 6;
		}
		if(in == 'H'){
			return 7;
		}
		if(in == '1'){
			return 7;
		}
		if(in == '2'){
			return 6;
		}
		if(in == '3'){
			return 5;
		}
		if(in == '4'){
			return 4;
		}
		if(in == '5'){
			return 3;
		}
		if(in == '6'){
			return 2;
		}
		if(in == '7'){
			return 1;
		}
		if(in == '8'){
			return 0;
		}
		return -1;
	}
}