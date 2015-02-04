//Knight class.  Extends Piece
public class Knight extends Piece{
	public Knight(char team, Position position){
		super(team, position, 'N', 30);
	}
	public Knight(Piece inPiece, Board inBoard){
		super(inPiece, inBoard);
	}
	public boolean move(){
		return true;
	}
	//Returns all of the moves possible for this piece
	public MoveList getPossibleMoves(Board inBoard){
		Position curPosition = super.getPosition();
		MoveList outMove = new MoveList();
		Position frontRight = new Position(curPosition.getRows() - 2, curPosition.getColumns() + 1);
		Position frontLeft = new Position(curPosition.getRows() - 2, curPosition.getColumns() - 1);
		Position backRight = new Position(curPosition.getRows() + 2, curPosition.getColumns() + 1);
		Position backLeft = new Position(curPosition.getRows() + 2, curPosition.getColumns() - 1);
		Position rightFront = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 2);
		Position leftFront = new Position(curPosition.getRows() - 1, curPosition.getColumns() - 2);
		Position rightBack = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 2);
		Position leftBack = new Position(curPosition.getRows() + 1, curPosition.getColumns() - 2);
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
		if(inBoard.validate(new Move(rightBack, this, curPosition))){
			outMove.addMove(new Move(rightBack, this, curPosition));
		}
		if(inBoard.validate(new Move(rightFront, this, curPosition))){
			outMove.addMove(new Move(rightFront, this, curPosition));
		}
		if(inBoard.validate(new Move(leftBack, this, curPosition))){
			outMove.addMove(new Move(leftBack, this, curPosition));
		}
		if(inBoard.validate(new Move(leftFront, this, curPosition))){
			outMove.addMove(new Move(leftFront, this, curPosition));
		}
		return outMove;

	}

}