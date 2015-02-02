//Pawn Class.  Extends Piece
public class Pawn extends Piece{
	public Pawn(char team, Position position){
		super(team, position, 'P', 10);
	}
	public boolean move(){
		return true;
	}
	//Return all of the possible moves for this piece
	public MoveList getPossibleMoves(Board inBoard){
		Position curPosition = super.getPosition();
		MoveList outMove = new MoveList();
		Position forward;
		Position frontRight;
		Position frontLeft;
		if(super.getTeam() == 'W'){
			forward = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 0);
			frontRight = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 1);
			frontLeft = new Position(curPosition.getRows() - 1, curPosition.getColumns() - 1);
		}
		else{
			forward = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 0);
				frontRight = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 1);
			frontLeft = new Position(curPosition.getRows() + 1, curPosition.getColumns() - 1);
		}
		// System.out.println("Debating: " + (new Move(forward, this, curPosition)) + ": " + (inBoard.validate(new Move(forward, this, curPosition))));
		if(inBoard.validate(new Move(forward, this, curPosition))){
			outMove.addMove(new Move(forward, this, curPosition));
		}
		// System.out.println("Debating: " + (new Move(frontRight, this, curPosition)) + ": " + (inBoard.validate(new Move(frontRight, this, curPosition))));
		if(inBoard.validate(new Move(frontRight, this, curPosition)) && inBoard.getCurrentBoard()[frontRight.getRows()][frontRight.getColumns()] != null){
			outMove.addMove(new Move(frontRight, this, curPosition));
		}
		// System.out.println("Debating: " + (new Move(frontLeft, this, curPosition)) + ": " + (inBoard.validate(new Move(frontLeft, this, curPosition))));
		if(inBoard.validate(new Move(frontLeft, this, curPosition)) && inBoard.getCurrentBoard()[frontLeft.getRows()][frontLeft.getColumns()] != null){
			outMove.addMove(new Move(frontLeft, this, curPosition));
		}
		
		return outMove;
	}
	//Over write the getvalue for pawns because of the pawn progression policy
	@Override
	public int getValue(){
		if(super.getTeam() == 'B'){
			return (super.getValue()-(super.getPosition().getRows()));
		}
		else{
			return super.getValue()+(7-super.getPosition().getRows());

		}
	}

}