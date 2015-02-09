//Move Class
//Stores the new position, the old position, and the piece you are moving.
public class Move{
	private Position position;
	private Piece piece;
	private Position oldPosition;
	public Move(Position position, Piece piece, Position oldPosition){
		this.position = position;
		this.piece = piece;
		this.oldPosition = oldPosition;
	}
	public Move(Move inMove, Board curBoard){
		this.position = new Position(inMove.getPosition());
		this.oldPosition = new Position(inMove.getOldPosition());
		this.piece = curBoard.getCurrentBoard()[this.oldPosition.getRows()][this.oldPosition.getColumns()];
	}
	public Position getPosition(){
		return position;
	}
	public Piece getPiece(){
		return piece;
	}
	public Position getOldPosition(){
		return oldPosition;
	}
	public String toString(){
		return oldPosition + " to " + position + " with piece " + piece ;
		// return null;
	}
	public boolean equals(Move inMove){
		if(this.position.getRows() == inMove.getPosition().getRows() && this.position.getColumns() == inMove.getPosition().getColumns() && this.oldPosition.getRows() == inMove.getOldPosition().getRows() && this.oldPosition.getColumns() == inMove.getOldPosition().getColumns()){
			return true;
		}
		return false;
	}
}