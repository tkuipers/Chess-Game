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
		// addSpecialPawns();
		// System.out.println(this);

		// System.out.println(checkMate(white, this));
	}
	//Copy Constructor
	public Board(Board inBoard){
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);

	}
	public Board(Board inBoard, Move inMove){
		inBoard.movePiece(inMove, null);
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);
		// System.out.println(inBoard);
		// System.out.println("Cloning this board");

	}
	public Board(Board inBoard, Move inMove, Team curTeam){
		inBoard.movePiece(inMove, curTeam);
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);
		// System.out.println(inBoard);
		// System.out.println("Cloning this board");

	}
	public boolean validateMove(Move inMove){
		//grab team
		//check whether it is in the available moves
		//return true or false based on that.
		Team curTeam;
		if(inMove.getPiece().getTeam() == 'W'){
			curTeam = white;
		}
		else{
			curTeam = black;
		}
		if(curTeam.getMoves(this).validate(inMove)){
			return true;
		}
		else{
			return false;
		}

	}
	public Team getWhite(){
		return white;
	}
	public Team getBlack(){
		return black;
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
		Pawn tempBlack = new Pawn('B', new Position(6,3));
		board[6][3] = tempBlack;
		black.addPiece(tempBlack);
		// System.out.println(tempBlack.getPossibleMoves(this));
		tempBlack = new Pawn('B', new Position(6,5));
		board[6][5] = tempBlack;
		black.addPiece(tempBlack);
		tempBlack = new Pawn('B', new Position(5,4));
		board[5][4] = tempBlack;
		black.addPiece(tempBlack);
		tempBlack = new Pawn('B', new Position(5,3));
		board[5][3] = tempBlack;
		black.addPiece(tempBlack);
		// Pawn tempWhite = new Pawn('W', new Position(3,7));
		// board[3][7] = tempWhite;
		// white.addPiece(tempWhite);
	}
	//Moves the piece while updating everything pertinent
	public void movePiece(Move inMove, Team curTeam){
		if(validateMove(inMove)){
			if(validate(inMove)){
				if(curTeam.getMoves(this).validate(inMove)){
					if(board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()] != null){
						if(board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()].getTeam() == 'W'){
							white.deletePiece(board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()]);
						}
						else{
							black.deletePiece(board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()]);
						}
					}
					board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()] = inMove.getPiece();
					board[inMove.getOldPosition().getRows()][inMove.getOldPosition().getColumns()] = null;
					board[inMove.getPosition().getRows()][inMove.getPosition().getColumns()].setPosition(inMove.getPosition());
				}
			}
		}

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
	//returns the king for this board.
	public Piece getKing(Team team){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i][j] != null){
					if(board[i][j].getType() == 'K'){
						if(board[i][j].getTeam() == team.type.toCharArray()[0]){
							return board[i][j];
						}
					}
				}
			}
		}
		return null;
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
		// out += "White has: " + white.getValue() + "\nBlack has: " + black.getValue() + "\nThe value of this board is: " + getScore();
		// out += "\nWhite Team\n" + white;
		// out += "\n" + "White is in check: " + check(white) + "\nBlack is in Check: " + check(black);
		// out += "\n" + "White is in checkmate: " + checkMate(white, this) + "\nBlack is in Checkmate: " + checkMate(black, this);
		// out += black.getMoves(this);

		return out;
	}
	public boolean check(Team inTeam){
		Team checkTeam = null;
		if(inTeam == black){
			checkTeam = white;
		}
		else{
			checkTeam = black;
		}
		MoveList checkList = checkTeam.getMoves(this);
		Position kingPosition = getKing(inTeam).getPosition();
		// checkPosition(kingPosition, checkList);
		return checkPosition(kingPosition, checkList);
	}
	public boolean checkMate(Team inTeam, Board curBoard){
		// System.out.println("Currently printing out the Currentest of boards.");
		// System.out.println(curBoard);
		if(!check(inTeam)){
			return false;
		}
		boolean checkMate = true;
		Team checkTeam = null;
		if(inTeam == black){
			checkTeam = white;
		}
		else{
			checkTeam = black;
		}
		// System.out.println("the current team we are checking is "+ checkTeam.type);
		MoveList checkList = checkTeam.getMoves(curBoard);
		// Position kingPosition = getKing(inTeam).getPosition();
		// MoveList kingMoves = getKing(inTeam).getPossibleMoves(curBoard);
		MoveNode tempNode = inTeam.getMoves(curBoard).start;
		while(tempNode != null){
			Board tempBoard = new Board(curBoard, tempNode.move);
			if(inTeam.getType().equals("white")){
				if(!(tempBoard.check(tempBoard.getWhite()))){
					checkMate = false;
					break;
				}
			}
			else{
				if(!(tempBoard.check(tempBoard.getBlack()))){
					checkMate = false;
					break;
				}
			}
			tempNode = tempNode.getNext();
		}
		// System.out.println("Currently Printing The CurBoard");
		// System.out.println(curBoard);
		return checkMate;
	}
	private boolean checkPosition(Position kingPosition, MoveList possibleOtherTeamMoves){
		MoveNode tempNode = possibleOtherTeamMoves.start;
		while(tempNode != null){
			if(kingPosition.getColumns() == tempNode.move.getPosition().getColumns() && kingPosition.getRows() == tempNode.move.getPosition().getRows()){
				// System.out.println("This move is causing the check to trip: " + tempNode.move);
				return true;
			}
			tempNode = tempNode.getNext();
		}
		return false;
	}
	public Team gameOver(){
		if (checkMate(white, this)){
			return black;
		}
		else if(checkMate(black, this)){
			return white;
		}
		return null;
	}

}