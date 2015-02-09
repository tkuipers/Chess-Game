public class Board{
	private Piece[][] board;
	private Team white;
	private Team black;
	private boolean moved;
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
		moved = false;
		// addSpecialPawns();
		// System.out.println(this);

		// System.out.println(checkMate(white, this));
	}
	//Copy Constructor
	public Board(Board inBoard){
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);
		// moved = inBoard.getMoved();

	}
	public Board(Board inBoard, Move inMove){
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);
		try{
			if(inMove.getPiece().getTeam() == 'W'){
				Move usableMove = new Move(inMove, this);
				this.movePiece(usableMove);	
			}
			else{
				Move usableMove = new Move(inMove, this);
				this.movePiece(usableMove);	
			}
		}
		catch(java.lang.NullPointerException e){
			System.out.println("Error in Board constructor: Board(Board inBoard, Move inMove)");
			System.exit(0);
		}

	}
	public Board(Board inBoard, Move inMove, Team curTeam){
		inBoard.movePiece(new Move(inMove, this));
		this.board = new Piece[8][8];
		this.white = new Team(inBoard.getWhite(), this);
		this.black = new Team(inBoard.getBlack(), this);
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
	public boolean movePiece(Move inMove){
		Move usableMove = new Move(inMove, this);
		if(validateMove(usableMove)){
			if(validate (usableMove)){
				if(board[usableMove.getPosition().getRows()][usableMove.getPosition().getColumns()] != null){
					// System.out.println("GOT HERE");
					if(board[usableMove.getPosition().getRows()][usableMove.getPosition().getColumns()].getTeam() == 'W'){
						white.deletePiece(board[usableMove.getPosition().getRows()][usableMove.getPosition().getColumns()]);
					}
					else{
						black.deletePiece(board[usableMove.getPosition().getRows()][usableMove.getPosition().getColumns()]);
					}
				}
				usableMove.getPiece().setPosition(usableMove.getPosition());
				board[usableMove.getOldPosition().getRows()][usableMove.getOldPosition().getColumns()] = null;
				board[usableMove.getPosition().getRows()][usableMove.getPosition().getColumns()] = usableMove.getPiece();
				return true;
			}
		}
		return false;
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
		if(this.getCurrentBoard()[inMove.getPosition().getRows()][inMove.getPosition().getColumns()] != null){
			if(this.getCurrentBoard()[inMove.getPosition().getRows()][inMove.getPosition().getColumns()].getTeam() == inMove.getPiece().getTeam()){
				// System.out.println("Got here");
				return false;
			}
			// return false;
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
		// out += "Possible Moves for white: \n" + white.getMoves(this);
		// out += "Possible Moves for black: \n" +  black.getMoves(this);
		out += "White has: " + white.getValue() + "\nBlack has: " + black.getValue() + "\nThe value of this board is: " + getScore();
		// out += "\nWhite Teamt\n" + white;
		// out += "\n" + "White is in check: " + check(white) + "\nBlack is in Check: " + check(black);
		// boolean whiteCheck = false;
		// boolean blackCheck = false;
		// if(checkMate(white, this).start == null){
			// whiteCheck = true;
		// }
		// if(checkMate(black, this).start == null){
			// blackCheck = true;
		// }

		// out += "\n" + "White is in checkmate: " + whiteCheck + "\nBlack is in Checkmate: " + blackCheck;
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
	public MoveList checkMate(Team inTeam, Board curBoard){
		Board masterBoard = new Board(curBoard);
		MoveList saveMoves = new MoveList();
		boolean checkMate=true;
		Team curTeam = null;
		char teamType = '\0';
		if(inTeam.getType().equals("White")){
			curTeam = black;
			teamType = 'W';
		}
		else{
			curTeam = white;
			teamType = 'B';
		}
		if(!check(curTeam)){
			return curTeam.getMoves(this);
		}
		MoveNode tempNode = curTeam.getMoves(this).start;
		while(tempNode != null){
			Board newBoard = new Board(this, tempNode.move);
			if(teamType == 'W'){
				//check if the new board has that king in check
				if(!(newBoard.check(newBoard.getWhite()))){
					saveMoves.addMove(tempNode.move);
				}

			}
			else{
				//check if the new board has the black king in chack
				if(!(newBoard.check(newBoard.getBlack()))){
					saveMoves.addMove(tempNode.move);
				}
			}
			tempNode = tempNode.getNext();
		}
		return saveMoves;

		
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
		if (checkMate(white, this) == null){
			System.out.println("White is in checkmate");
			return black;
		}
		else if(checkMate(black, this) == null){
			System.out.println("Black is in checkmate");
			// System.out.println(black);
			System.out.println(black.getMoves(this));
			return white;
		}
		return null;
	}
	public boolean getMoved(){
		return moved;
	}





}