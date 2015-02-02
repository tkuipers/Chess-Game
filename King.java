//King piece class.  Extends Piece
public class King extends Piece{
	public King(char team, Position position){
		super(team, position, 'K', 1000);
	}
	public boolean move(){
		return true;
	}
	//return all of the moves possible for the king
	public MoveList getPossibleMoves(Board inBoard){
		Position curPosition = super.getPosition();
		MoveList outMove = new MoveList();
		Position forward = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 0);
		Position back = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 0);
		Position right = new Position(curPosition.getRows() + 0, curPosition.getColumns() + 1);
		Position left = new Position(curPosition.getRows() + 0, curPosition.getColumns() - 1);
		Position frontRight = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 1);
		Position backRight = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 1);
		Position frontLeft = new Position(curPosition.getRows() + 1, curPosition.getColumns() - 1);
		Position backLeft = new Position(curPosition.getRows() - 1, curPosition.getColumns() - 1);
		if(inBoard.validate(new Move(forward, this, curPosition))){
			outMove.addMove(new Move(forward, this, curPosition));
		}
		if(inBoard.validate(new Move(back, this, curPosition))){
			outMove.addMove(new Move(back, this, curPosition));
		}
		if(inBoard.validate(new Move(right, this, curPosition))){
			outMove.addMove(new Move(right, this, curPosition));
		}
		if(inBoard.validate(new Move(left, this, curPosition))){
			outMove.addMove(new Move(left, this, curPosition));
		}
		if(inBoard.validate(new Move(frontRight, this, curPosition))){
			outMove.addMove(new Move(frontRight, this, curPosition));
		}
		if(inBoard.validate(new Move(frontLeft, this, curPosition))){
			outMove.addMove(new Move(frontLeft, this, curPosition));
		}
		if(inBoard.validate(new Move(backRight, this, curPosition))){
			outMove.addMove(new Move(backRight, this, curPosition));
		}
		if(inBoard.validate(new Move(backLeft, this, curPosition))){
			outMove.addMove(new Move(backLeft, this, curPosition));
		}
		return outMove;
	}

}