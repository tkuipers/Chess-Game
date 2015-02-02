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
		return (((char)((int)'A'+oldPosition.getColumns())) + (8-oldPosition.getRows() + " to " + ((char)((int)'A'+position.getColumns())) + (8-position.getRows())));
		// return null;
	}
}