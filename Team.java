//Team class, which is a doubly-linked list conatining all of the pieces on a team
public class Team{
	public String type;
	public PieceNode start;
	//Constructor.  Needs a team name.
	public Team(String teamname){
		type = teamname;
		start = null;
	}
	public String getType(){
		return type;
	}
	public Team(Team inTeam, Board inBoard){
		start = null;
		type = new String(inTeam.type);
		PieceNode tempNode = inTeam.start;
		PieceNode prevNode = null;
		PieceNode newNode = null;
		while(tempNode != null){
			newNode = new PieceNode(tempNode, inBoard);
			this.addNode(newNode);
			if(prevNode != null){
				prevNode.setNext(newNode);
			}
			prevNode = newNode;
			tempNode = tempNode.getNext();
		}
	}
	public void addNode(PieceNode newPieceNode){
		if(start == null){
			start = newPieceNode;
		}
		else{
			PieceNode curNode = start;
			while(curNode.getNext() != null){
				curNode = curNode.getNext();
			}
			curNode.setNext(newPieceNode);
		}
	}
	//add a piece to the team
	public void addPiece(Piece newPiece){
		if(start == null){
			start = new PieceNode(newPiece);
		}
		else{
			PieceNode curNode = start;
			while(curNode.getNext() != null){
				curNode = curNode.getNext();
			}
			PieceNode out = new PieceNode(newPiece);
			curNode.setNext(out);
		}
	}
	//remove a piece from the linked list.
	public void deletePiece(Piece deadPiece){
		// System.out.println("DELETING: " + deadPiece);
		PieceNode prevPiece = null;
		PieceNode temp = start;
		while(temp != null){
			if(deadPiece.equals(temp.piece)){
				// System.out.println("Deleting: \n" + deadPiece);
				if(prevPiece != null){
					prevPiece.setNext(temp.getNext());
				}
				else{
					start = temp.getNext();
				}
				break;
			}
			prevPiece = temp;
			temp = temp.getNext();
		}
	}
	//return the value of the team
	public int getValue(){
		PieceNode temp = start;
		int out = 0;
		while(temp != null){
			out += temp.piece.getValue();
			temp = temp.getNext();
		}
		return out;
	}


	//get all of the possible moves for the entire team. Uses the movelist linked list.
	public MoveList getMoves(Board currentBoard){
		PieceNode temp = start;
		MoveList possibleMoves = new MoveList();
		while(temp != null){
			// out += temp.piece + "\n";
			possibleMoves.addMove(temp.piece.getPossibleMoves(currentBoard));
			temp = temp.getNext();
		}
		// System.out.println(possibleMoves);
		return possibleMoves;

	}
	//Used for printing all of the pieces on a team.
	public String toString(){
		String out = "";
		PieceNode temp = start;
		while(temp != null){
			out += temp.piece +  " at " + temp.piece.getPosition() + "\n";
			temp = temp.getNext();
		}
		return out;
	}
}