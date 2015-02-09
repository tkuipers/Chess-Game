//Used for the team class linked list.
public class PieceNode{
	public Piece piece;
	private PieceNode next;
	public PieceNode(Piece piece){
		this.next = null;
		this.piece = piece;
	}
	public PieceNode(PieceNode pieceNode, Board inBoard){
		char type = pieceNode.piece.getType();
		if(type == 'B'){
			piece = new Bishop(pieceNode.piece, inBoard);
		}
		else if(type == 'R'){
			piece = new Castle(pieceNode.piece, inBoard);
		}
		else if(type == 'K'){
			piece = new King(pieceNode.piece, inBoard);
		}
		else if(type == 'Q'){
			piece = new Queen(pieceNode.piece, inBoard);
		}
		else if(type == 'P'){
			piece = new Pawn(pieceNode.piece, inBoard);
		}
		else if(type == 'N'){
			piece = new Knight(pieceNode.piece, inBoard);
		}
	}
	public void setNext(PieceNode next){
		this.next = next;
	}
	public PieceNode getNext(){
		return next;
	}
	public boolean equals(PieceNode input){
		return input.equals(this.piece);
	}
}