//Pawn Class.  Extends Piece
public class Pawn extends Piece{
	public Pawn(char team, Position position){
		super(team, position, 'P', 10);
	}
	public Pawn(Piece inPiece, Board inBoard){
		super(inPiece, inBoard);
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
		Position doubleFront = null;
		if(super.getTeam() == 'W'){
			forward = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 0);
			frontRight = new Position(curPosition.getRows() - 1, curPosition.getColumns() + 1);
			frontLeft = new Position(curPosition.getRows() - 1, curPosition.getColumns() - 1);
			if(!super.getMoved()){
				doubleFront = new Position(curPosition.getRows() - 2, curPosition.getColumns() + 0);
			}
		}
		else{
			forward = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 0);
			frontRight = new Position(curPosition.getRows() + 1, curPosition.getColumns() + 1);
			frontLeft = new Position(curPosition.getRows() + 1, curPosition.getColumns() - 1);
			if(!super.getMoved()){
				doubleFront = new Position(curPosition.getRows() + 2, curPosition.getColumns() + 0);
			}
		}
		// System.out.println("Debating: " + (new Move(forward, this, curPosition)) + ": " + (inBoard.validate(new Move(forward, this, curPosition))));
		if(inBoard.validate(new Move(forward, this, curPosition))){
			if(inBoard.getCurrentBoard()[forward.getRows()][forward.getColumns()] == null){
				outMove.addMove(new Move(forward, this, curPosition));
			}
		}
		if(doubleFront != null){
			if(inBoard.validate(new Move(doubleFront, this, curPosition))){
				if(inBoard.getCurrentBoard()[doubleFront.getRows()][doubleFront.getColumns()] == null){
					outMove.addMove(new Move(doubleFront, this, curPosition));
				}
			}
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