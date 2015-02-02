//Team class, which is a doubly-linked list conatining all of the pieces on a team
public class Team{
	public String type;
	public PieceNode start;
	//Constructor.  Needs a team name.
	public Team(String teamname){
		type = teamname;
		start = null;
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
			out.setPrev(curNode);
		}
	}
	//remove a piece from the linked list.
	public void deletePiece(Piece deadPiece){
		PieceNode temp = start;
		if(deadPiece == temp.piece){
			if(temp.getNext() == null){
				temp = null;
			}
			else{
				temp = temp.getNext();
			}
		}
		while(temp.getNext() != null){
			temp = temp.getNext();
			if(temp.piece == deadPiece){
				if(temp.getNext() != null){
					temp.getPrev().setNext(temp.getNext());
					temp.getNext().setPrev(temp.getPrev());
				}
				else{
					temp.getPrev().setNext(null);
				}
				break;
			}
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
		System.out.println("Printing moves for " + type + " team");
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
			out += temp.piece + "\n";
			temp = temp.getNext();
		}
		return out;
	}
}