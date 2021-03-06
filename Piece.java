// Piece Class.  
abstract class Piece{
	private char team;
	private Position position;
	private char type;
	private int value;
	private boolean movedOnce;

	abstract boolean move();
	abstract MoveList getPossibleMoves(Board inBoard);//Constructor
	public Piece(char team, Position position, char type, int value){
		this.team = team;
		this.position = position;
		this.type = type;
		this.value = value;
		movedOnce = false;
	}
	public Piece(Piece inPiece, Board inBoard){
		this.team = inPiece.getTeam();
		this.position = new Position(inPiece.getPosition());
		this.type = inPiece.getType();
		this.value = inPiece.getBaseValue();
		inBoard.getCurrentBoard()[position.getRows()][position.getColumns()] = this;
		this.movedOnce = inPiece.getMoved();
	}
	public boolean equals(Piece inPiece){
		if(this.team == inPiece.getTeam()){
			// System.out.println("The teams are equal");
			if(this.position.equals(inPiece.getPosition())){ 
				// System.out.println("The positions are equals");
				if(this.type == inPiece.getType()){
					// System.out.println("The types are equal");
					// if(this.value  == inPiece.getValue()){
						// System.out.println("I dont know why I am returning false");
						return true;
					// }
				}
			}
		}
		return false;

	}
	public boolean getMoved(){
		return movedOnce;
	}
	public int getBaseValue(){
		return value;
	}
	//Return teams
	public char getTeam(){
		return team;
	}
	//return a string with the team and type.  White Pawn = WP
	public String toString(){
		return "" + team + type;
	}
	public char getType(){
		return type;
	}
	//Return the value of the piece
	public int getValue(){
		if(team == 'B'){
			return 0-value;
		}
		else{
			return value;
		}
	}
	//return the current position of the piece
	public Position getPosition(){
		return position;
	}
	//Set the new position
	public void setPosition(Position inPos){
		position = new Position(inPos);
		movedOnce = true;
	}
}