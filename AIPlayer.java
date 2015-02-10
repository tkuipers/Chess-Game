import java.lang.Thread;
import java.util.Timer;
import java.util.*;
public class AIPlayer extends Player{
	private int difficulty;
	public AIPlayer(char teamType){
		super(teamType);
		this.difficulty = difficulty;
	}
	public Board promptUser(Board board){
		Board outBoard = null;
		int a = 10;
		while(a==10){
			Scanner scantron = new Scanner(System.in);
			System.out.println("Enter the ply for the computer to use");
			String out = scantron.next();
			if(out.equals("quit")){
				System.exit(0);
			}
			else if(out.equals("2")){
				outBoard = getBestBoard(board, super.getTeam(), 2);
				break;
			}
			else if(out.equals("4")){
				outBoard = getBestBoard(board, super.getTeam(), 4);
				break;
			}
			else if(out.equals("6")){
				outBoard = getBestBoard(board, super.getTeam(), 6);
				break;
			}
			else{
				System.out.println("Sorry, I didnt get that");
			}
		}
		// int difficulty = 6;
		// outBoard = getBestBoard(board, super.getTeam(), difficulty);
		return outBoard;
		// System.out.println();
	}
	public Board getBestBoard(Board inBoard, char teamType, int inDifficulty){
		Team myCurTeam = (teamType == 'W') ? inBoard.getWhite() : inBoard.getBlack();
		Team otherTeam = (teamType == 'W') ? inBoard.getBlack() : inBoard.getWhite();
		// System.out.println("My team has the following: \n" + myCurTeam);
		char otherTeamType = (teamType == 'W') ? 'B' : 'W';
		MoveNode tempNode = myCurTeam.getMoves(inBoard).start;
		MoveList possibleMoves = myCurTeam.getMoves(inBoard);
		Board saveBoard = new Board(inBoard);
		Board outBoard = null;
		Board masterBoard = new Board(saveBoard);

		Move bestMove = null;
		int curLow = -1400; // 2 less than the worst possible score
		int curHigh = 1400;

		int value;
		// System.out.println("Starting Move: ");
		long startTime = System.nanoTime();
		while(tempNode != null){
			Board tempBoard = null;
			tempBoard = new Board(saveBoard, tempNode.move);
			if(teamType == 'W'){
				value = -1400;
				int moveScore = getBestMoveBlack(tempBoard, inDifficulty, 1, -1400, 1400);
				if(inDifficulty > 4){
					System.out.println(tempNode.move + " with a value of " + moveScore);
				}
				if(moveScore >= curLow){
					bestMove = tempNode.move;
					curLow = moveScore;
				}
			}
			else{
				value = 1400;
				int moveScore = getBestMoveWhite(tempBoard, inDifficulty,1, -1400, 1400);
				if(inDifficulty > 4){
					System.out.println(tempNode.move + " with a value of " + moveScore);
				}
				if(moveScore < curHigh){
					bestMove = tempNode.move;
					curHigh = moveScore;
				}
			}
			tempNode = tempNode.getNext();
		}
		long endTime = System.nanoTime();
		float seconds = (endTime - startTime) / 1000000000;
		if( seconds < 60){
			System.out.println("Move took " + seconds + " seconds");
		}
		else{
			System.out.println("Move took " + seconds / 60 + " minutes");
		}
		System.out.println("Doing move " + bestMove);
		masterBoard.movePiece(bestMove);
		try{
			// Thread.sleep(1000);
		}
		catch(Exception e){

		}
		return masterBoard;
	}
	public int getBestMoveWhite(Board inBoard, int depthLimit, int deep, int alpha, int beta){
		///LOOKING FOR MAX
		int tempInt = -1400;
		int value = -1400;
		if(alpha > value){
			alpha = value;
		}
		if(inBoard.checkMate(inBoard.getWhite(), inBoard)){
			return -10000;
		}
		if(deep < depthLimit){
			MoveNode tempNode = inBoard.getWhite().getMoves(inBoard).start;
			while(tempNode != null){
				Board tempBoard = new Board(inBoard, tempNode.move);
				if(beta < alpha){
					break;
				}
				int boardScore = getBestMoveBlack(tempBoard, depthLimit, deep +1, alpha, beta);
				if(boardScore > tempInt){
					tempInt = boardScore;
				}
				if(boardScore > alpha){
					alpha = boardScore;
				}
				tempNode = tempNode.getNext();
			}
			return tempInt;
		}
		else{
			return inBoard.getScore();
		}
	}
	public int getBestMoveBlack(Board inBoard, int depthLimit, int deep, int alpha, int beta){
		//LOOKING FOR MIN
		int tempInt = 1400;
		int value = 1400;
		// System.out.println(inBoard);
		// if(inBoard.checkMate(inBoard.getBlack(), inBoard) == null){
			// return -10000;
		// }
		if(beta < value){
			beta = value;
		}
		if(inBoard.checkMate(inBoard.getBlack(), inBoard)){
			return 10000;
		}
		if(deep < depthLimit){
			MoveNode tempNode = inBoard.getBlack().getMoves(inBoard).start;
			while(tempNode != null){
				Board tempBoard = new Board(inBoard, tempNode.move);
				// System.out.println(tempBoard);
				if(beta < alpha){
					break;
				}
				int boardScore = getBestMoveWhite(tempBoard, depthLimit, deep +1, alpha, beta);
				if(boardScore < tempInt){
					tempInt = boardScore;
				}
				if(boardScore < beta){
					beta = boardScore;
				}

				tempNode = tempNode.getNext();
			}
			return tempInt;
		}
		else{
			return inBoard.getScore();
		}
	}

	public int getBoardScore(int value, char teamType){
		return value;
	}

}