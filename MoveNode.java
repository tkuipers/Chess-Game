//The node for the movelist linked list.
public class MoveNode{
	public Move move;
	private MoveNode next;
	public MoveNode(Move move){
		this.move = move;
		next = null;
	}
	public void setNext(MoveNode in){
		next = in;
	}
	public MoveNode getNext(){
		return next;
	}
}