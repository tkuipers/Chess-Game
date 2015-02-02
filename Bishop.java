//Bishop Class.  Extends Piece.  A
public class Bishop extends Piece{
	public Bishop(char team, Position position){
		super(team, position, 'B', 30);
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
		while(temp < 8){
			if(curRow - temp >= 0 && curRow - temp < 8 && curColumn - temp >= 0 && curColumn - temp < 8){
				Position forward = new Position(curPosition.getRows() - temp, curPosition.getColumns() - temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow - temp][curColumn - temp] != null){
				break;
			}
			temp++;
		}
		temp = 1;
		while(temp < 8){
			if(curRow + temp >= 0 && curRow + temp < 8 && curColumn + temp >= 0 && curColumn + temp < 8){
				Position forward = new Position(curPosition.getRows() + temp, curPosition.getColumns() + temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow + temp][curColumn + temp] != null){
				break;
			}
			temp++;
		}

		//upleft
		temp = 1;
		while(temp < 8){
			if(curRow + temp >= 0 && curRow + temp < 8 && curColumn - temp >= 0 && curColumn - temp < 8){
				Position forward = new Position(curPosition.getRows() + temp, curPosition.getColumns() - temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow + temp][curColumn - temp] != null){
				break;
			}
			temp++;
		}
		temp = 1;
		while(temp < 8){
			if(curRow - temp >= 0 && curRow - temp < 8 && curColumn + temp >= 0 && curColumn + temp < 8){
				Position forward = new Position(curPosition.getRows() - temp, curPosition.getColumns() + temp);
				if(inBoard.validate(new Move(forward, this, curPosition))){
					outMove.addMove(new Move(forward, this, curPosition));
				}
			}
			else{
				break;
			}
			if(inBoard.getCurrentBoard()[curRow- temp][curColumn + temp] != null){
				break;
			}
			temp++;
		}
		return outMove;
	}

}