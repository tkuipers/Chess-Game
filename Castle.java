//Castle piece.  Extends piece
public class Castle extends Piece{
	public Castle(char team, Position position){
		super(team, position, 'R', 50);
	}
	public Castle(Piece inPiece, Board inBoard){
		super(inPiece, inBoard);
	}
	public boolean move(){
		return true;
	}
	//Returns all of the moves possible for this piece
	public MoveList getPossibleMoves(Board inBoard){
		Position curPosition = super.getPosition();
		MoveList outMove = new MoveList();
		int curRow = curPosition.getRows();
		int curColumn = curPosition.getColumns();
		int temp = 1;
		//downwards
		while(temp < 8){
			if(curRow - temp >= 0 && curRow - temp < 8){
				Position forward = new Position(curPosition.getRows() - temp, curPosition.getColumns() + 0);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow - temp][curPosition.getColumns()] != null){
				break;
			}
			temp++;
		}
		//updwards
		temp = 1;
		while(temp < 8){
			if(curRow + temp >= 0 && curRow + temp < 8){
				Position forward = new Position(curPosition.getRows() + temp, curPosition.getColumns() + 0);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow + temp][curPosition.getColumns()] != null){
				break;
			}
			temp++;
		}
		//left
		temp = 1;
		while(temp < 8){
			if(curColumn - temp >= 0 && curColumn - temp < 8){
				Position forward = new Position(curPosition.getRows(), curPosition.getColumns() - temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow][curColumn - temp] != null){
				break;
			}
			temp++;
		}
		//right
		temp = 1;
		while(temp < 8){
			if(curColumn + temp >= 0 && curColumn + temp < 8){
				Position forward = new Position(curPosition.getRows(), curPosition.getColumns() + temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow][curColumn + temp] != null){
				break;
			}
			temp++;
		}
		return outMove;
	}

}