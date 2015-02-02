//Used for the team class linked list.
public class PieceNode{
	public Piece piece;
	private PieceNode next;
	private PieceNode prev;
	public PieceNode(Piece piece){
		this.next = null;
		this.piece = piece;
		this.prev = null;
	}
	public void setNext(PieceNode next){
		this.next = next;
	}
	public void setPrev(PieceNode prev){
		this.prev = prev;
	}
	public PieceNode getNext(){
		return next;
	}
	public PieceNode getPrev(){
		return prev;
	}
}