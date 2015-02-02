
//Queen Class.  Extends piece class
public class Queen extends Piece{
	public Queen(char team, Position position){
		super(team, position, 'Q', 90);
	}
	public boolean move(){
		return true;
	}
	//return all of the moves possible for the queen
	public MoveList getPossibleMoves(Board inBoard){
		Position curPosition = super.getPosition();
		MoveList outMove = new MoveList();
		int curRow = curPosition.getRows();
		int curColumn = curPosition.getColumns();
		int temp = 1;
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
		temp = 1;
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