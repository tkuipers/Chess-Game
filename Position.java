//Position class
//Usd to store rows and columns of the position
public class Position{
	private int row;
	private int column;
	public Position(int row, int column){
		this.row = row;
		this.column = column;
	}
	public Position(Position inPosition){
		this.row = inPosition.getRows();
		this.column = inPosition.getColumns();
	}
	public void setRow(int row){
		this.row=row;
	}
	public void setColumn(int column){
		this.column = column;
	}
	public int getRows(){
		return row;
	}
	public int getColumns(){
		return column;
	}
	public String toString(){
		return "" + ((char)((int)'A'+this.getColumns())) + (8-this.getRows() );
	}
	public boolean equals(Position inPosition){
		if(this.row == inPosition.getRows() && this.column == inPosition.getColumns()){
			return true;
		}
		return false;
	}
}