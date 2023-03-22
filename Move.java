
/**
 * Move is a class for the move.
 * @author Rahul Razdan and Richard Xiao
**/

public class Move {
	
	private Square destination; // destination square
	private boolean captures; // boolean to determine if move results in a capture
	private Piece piece; // piece to be moved
	
  /**
   * Constructor for Move class
   * @param destination : destination square
   * @param piece : piece to be moved
   * @param captures : boolean for whether a capture occurs
  **/
  public Move (Square destination, Piece piece, boolean captures) 
  {
	// initialize variables
	this.destination=destination; 
	this.captures=captures;
	this.piece=piece;
  }

  /**
   * Accessor method for piece
   * @return returns the piece to be moved
  **/
  public Piece getPiece() {
	return this.piece;
  }
	
  /**
   * toString method for the move class
   * @return String representing the move instance
  **/
  public String toString() 
  {
	if(!captures)
      return piece.pieceType + " to " + destination; // if a capture does not occur
    else
      return piece.pieceType + " takes " + destination; // if a capture does occur
	}
  
  /**
   * Displays the location of move result
   * @return returns a string of move result
  **/
  public String display()
  {
    if(!captures)
      return piece.display() + destination; // if not captured
    else
      return piece.display() + "x" + destination; // else if captured
  }
  /**
   * Accessor method for destination
   * @return returns the destination
  **/
  public Square toWhere()
  {
    return destination;
  }

}
