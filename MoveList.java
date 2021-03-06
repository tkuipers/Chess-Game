import java.util.*;
//Used for storing all of the moves possible for a piece
public class MoveList{
	public MoveNode start;
	public MoveList(){
		start = null;
	}
	//add a move to a list
	public void addMove(Move inMove){
		if(start == null){
			start = new MoveNode(inMove);
		}
		else{
			MoveNode temp = start;
			while(temp.getNext() != null){
				temp = temp.getNext();
				if(temp.move == inMove){
					break;
				}
			}
			temp.setNext(new MoveNode(inMove));
		}
		// System.out.println("Finished Adding Move to List");
	}
	//add an entire movelist to the list
	public void addMove(MoveList inMoveList){
		if(inMoveList == null){
			return;
		}
		if(start != null){
			MoveNode tempNode = start;
			while(tempNode.getNext() != null){
				tempNode = tempNode.getNext();
			}
			tempNode.setNext(inMoveList.start);
		}
		else{
			start = inMoveList.start;
		}
	}
	public boolean validate(Move inMove){
		MoveNode tempNode = start;
		while(tempNode != null){
			// System.out.println("Checking move: "+ tempNode.move);
			if(tempNode.move.equals(inMove)){
				// System.out.println("Returning true");
				return true;
			}
			tempNode = tempNode.getNext();
		}
		return false;
	}
	//Print all of the moves in the list
	public String toString(){
		MoveNode moveTemp = start;
		String out = "";
		// if(start != null){
			// out = "Starting to print movelist for " + moveTemp.move.getPiece() + "\n";
		// }
		// else{
			// out = "The Movelist is Empty";
		// }
		while(moveTemp != null){
			out += moveTemp.move + "\n";
			moveTemp =moveTemp.getNext();
		}
		// out += "Hit the null case in MoveList";
		return out;
	}
	public Move getRandom(){
		Random randomClass = new Random();
		MoveNode tempMoveNode = start;
		for(int i = 0; i < randomClass.nextInt(6); i++){
			if(tempMoveNode.getNext() == null){
				break;
			}
			tempMoveNode = tempMoveNode.getNext();
		}
		return tempMoveNode.move;
	}
}