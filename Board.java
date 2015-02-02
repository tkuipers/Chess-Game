public class Board{
	private Piece[][] board;
	private Team white;
	private Team black;
	//Create the board and add pieces.
	public Board(){
		board =new Piece[8][8];
		white = new Team("White");
		black = new Team("Black");
		addPawns();
		addCastles();
		addKnights();
		addBishops();
		addQueens();
		addKings();
		System.out.println(this);
	}
	//Returns the board for use in other functions
	public Piece[][] getCurrentBoard(){
		return board;
	}
	//Adds the pawns to the board in their starting positions
	public void addPawns(){
		for(int i = 0; i < 8; i++){
			Pawn tempWhite = new Pawn('W', new Position(6,i));
			board[6][i] = tempWhite;
			white.addPiece(tempWhite);


			Pawn tempBlack = new Pawn('B', new Position(1,i));
			board[1][i] = tempBlack;
			black.addPiece(tempBlack);

		}
	}
	//Used for testing the heuristic and the pawn progression policy
	public void addSpecialPawns(){
		Pawn tempBlack = new Pawn('B', new Position(4,7));
		board[4][7] = tempBlack;
		black.addPiece(tempBlack);
		Pawn tempWhite = new Pawn('W', new Position(3,7));
		board[3][7] = tempWhite;
		white.addPiece(tempWhite);
	}
	//Moves the piece while updating everything pertinent
	public void movePiece(Move inMove){
		Piece movedPiece = inMove.getPiece();
		Position movedPosition = inMove.getPosition();
		Position oldPosition = inMove.getOldPosition();
		if(board[movedPosition.getRows()][movedPosition.getColumns()] == null){
			board[movedPosition.getRows()][movedPosition.getColumns()] = movedPiece;
			board[oldPosition.getRows()][oldPosition.getColumns()] = null;
		}
		else{
			deletePiece(board[movedPosition.getRows()][movedPosition.getColumns()]);
			board[movedPosition.getRows()][movedPosition.getColumns()] = movedPiece;
			board[oldPosition.getRows()][oldPosition.getColumns()] = null;
		}
		movedPiece.setPosition(movedPosition);

	}
	//removes the piece from the board and the team record
	public void deletePiece(Piece inPiece){
		if(inPiece.getTeam() == 'B'){
			black.deletePiece(inPiece);
		}
		else{
			white.deletePiece(inPiece);
		}
	}
	//returns the value of the board.  Uses the Linked list to grab the score.
	public int getWinner(){
		return white.getValue() + black.getValue();
	}

	//Add the castles to the board
	public void addCastles(){
		Castle tempWhite = new Castle('W', new Position(7,0));
		Castle otherTempWhite = new Castle('W', new Position(7,7));
		board[7][0] = tempWhite;
		board[7][7] = otherTempWhite;
		white.addPiece(tempWhite);
		white.addPiece(otherTempWhite);

		Castle tempBlack = new Castle('B', new Position(0,0));
		Castle otherTempBlack = new Castle('B', new Position(0,7));
		board[0][0] = tempBlack;
		board[0][7] = otherTempBlack;
		black.addPiece(tempBlack);
		black.addPiece(otherTempBlack);
	}

	//add the knights to the board
	public void addKnights(){
		Knight tempWhite = new Knight('W', new Position(7,1));
		Knight otherTempWhite = new Knight('W', new Position(7,6));
		board[7][1] = tempWhite;
		board[7][6] = otherTempWhite;
		white.addPiece(tempWhite);
		white.addPiece(otherTempWhite);

		Knight tempBlack = new Knight('B', new Position(0,1));
		Knight otherTempBlack = new Knight('B', new Position(0,6));
		board[0][1] = tempBlack;
		board[0][6] = otherTempBlack;
		black.addPiece(tempBlack);
		black.addPiece(otherTempBlack);
	}
	//add the bishops to the board
	public void addBishops(){
		Bishop tempWhite = new Bishop('W', new Position(7,2));
		Bishop otherTempWhite = new Bishop('W', new Position(7,5));
		board[7][2] = tempWhite;
		board[7][5] = otherTempWhite;
		white.addPiece(tempWhite);
		white.addPiece(otherTempWhite);

		Bishop tempBlack = new Bishop('B', new Position(0,2));
		Bishop otherTempBlack = new Bishop('B', new Position(0,5));
		board[0][2] = tempBlack;
		board[0][5] = otherTempBlack;
		black.addPiece(tempBlack);
		black.addPiece(otherTempBlack);
	}
	//add the queens to the board
	public void addQueens(){
		Queen tempWhite = new Queen('W', new Position(7,3));
		board[7][3] = tempWhite;
		white.addPiece(tempWhite);

		Queen tempBlack = new Queen('B', new Position(0,4));
		board[0][4] = tempBlack;
		black.addPiece(tempBlack);
	}
	//add the kings to the board
	public void addKings(){
		King tempWhite = new King('W', new Position(7,4));
		board[7][4] = tempWhite;
		white.addPiece(tempWhite);

		King tempBlack = new King('B', new Position(0,3));
		board[0][3] = tempBlack;
		black.addPiece(tempBlack);

	}
	//takes in a move, and then tells whether or not that move is possible
	public boolean validate(Move inMove){
		if(inMove.getPosition().getColumns() >= 8 || inMove.getPosition().getColumns() < 0){
			return false;
		}
		if(inMove.getPosition().getRows() >= 8 || inMove.getPosition().getRows() < 0){
			return false;
		}
		if(this.getCurrentBoard()[inMove.getPosition().getRows()][inMove.getPosition().getColumns()] != null && this.getCurrentBoard()[inMove.getPosition().getRows()][inMove.getPosition().getColumns()].getTeam() == inMove.getPiece().getTeam()){
			return false;
		}
		return true;
	}
	//returns the score of the board using an iterative method
	public int getScore(){
		int score = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j] != null){
					score += board[i][j].getValue();
				}
			}
		}
		return score;
	}
	//used for printing the board to the console.
	public String toString(){
		String out = " +----+----+----+----+----+----+----+----+\n";
		for(int i = 0; i < 8; i++){
			out += 8-i;
			for(int j = 0; j < 8; j++){
				if(board[i][j] != null){
					out += "| " + board[i][j] + " ";
				}
				else{
					out += "|    ";
				}
			}
			out += "|\n";
			out += " +----+----+----+----+----+----+----+----+\n";
		}
		out +="  A    B    C    D    E    F    G    H   \n";
		out += "White has: " + white.getValue() + "\nBlack has: " + black.getValue() + "\nThe value of this board is: " + getScore();
		// out += "\n" + board[6][2].getPossibleMoves(this);

		return out;
	}

}