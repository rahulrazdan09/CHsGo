import java.util.ArrayList;
/**
 * King is a class for the king piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/

public class King extends Piece {
	
  /**
   * Constructor 1 for King
   * @param side : team of the piece
   * @param spot : location of the piece
   */
  public King(Side side, Square spot) {
	super(side, spot); // Initialize fields
	this.pieceType="King"; // set string
  }
  
  /**
   * Constructor 2 for King
   * @param team : team of the piece
   */
  public King(Side team) {
	super(team); // initialize fields
	this.pieceType="King"; // set string
  }

  /**
   * Constructor 3 for King. Used for duping
  **/
  public King()
  {
    super(); // initialize fields
    this.pieceType = "King"; // set string
  }

  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return returns the new instance
   */
  public Piece dupe()
  {
    King ret = new King(); // create new instance
    ret.team = this.team; // set team of new instance
    return ret; 
  }
  
  /**
   * Gets all moves for the king
   * @return returns all the moves
   */
  public ArrayList<Move> getMoves(){
    ArrayList<Move> ret = new ArrayList<Move>(); // return array list initialized
    Board gameBoard = this.location.getBoard(); // get gameBoard
    int xLoc=this.location.getX(); // get x location of king
    int yLoc=this.location.getY(); // get y location of king
    Square targSq;
    // If it can move up one and right one
    if (yLoc + 1 <= 7) { 
        targSq = gameBoard.getSquare(xLoc+1, yLoc+1);
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq, this , true)); // add if it can move up one and right one and captures
        }
        else
            ret.add(new Move(targSq , this , false));  // add if it can move up one and right one 
    }
    // If it can move up one and left one
    if (yLoc - 1 >= 0) {
        targSq = gameBoard.getSquare(xLoc+1, yLoc-1);
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq, this , true)); // add if you can move up one left one and captures
        }
        else
            ret.add(new Move(targSq, this , false)); // add if you can move up one left one
    }
    // if it can move down one right one
    if (yLoc + 1 <= 7) {
        targSq = gameBoard.getSquare(xLoc-1, yLoc+1); 
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq , this , true)); // add if you can move down one right one and captures
        }
        else
            ret.add(new Move(targSq , this , false)); // add if you can move down one right one
    }
    
    // if it can move down one left one
    if (yLoc - 1 >= 0) {
        targSq = gameBoard.getSquare(xLoc-1, yLoc-1);
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq , this , true)); // add if it can move down one left one and captures
        }
        else
            ret.add(new Move(targSq , this , false)); // add if it can move down one left one
    }
    
    targSq = gameBoard.getSquare(xLoc-1, yLoc);
    if (targSq.isOccupied()) {
        if (targSq.getPiece().team.isWhite!=this.team.isWhite)
            ret.add(new Move(targSq , this , true));  // add if it can move down one and captures
    }
    else
        ret.add(new Move(targSq , this , false)); // add if it can move down one
    
    targSq = gameBoard.getSquare(xLoc+1, yLoc);
    if (targSq.isOccupied()) {
        if (targSq.getPiece().team.isWhite!=this.team.isWhite)
            ret.add(new Move(targSq , this , true)); // add if it can move up one and captures
    }
    else
        ret.add(new Move(targSq , this , false)); // add if it can move up one
    // if it can move left one
    if (yLoc - 1 >= 0) {
        targSq = gameBoard.getSquare(xLoc, yLoc-1);
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq , this , true)); // add if it can move left one and captures
        }
        else
            ret.add(new Move(targSq , this , false)); // add if it can move left one
    }
    // if it can move right one
    if (yLoc + 1 <= 7) {
        targSq = gameBoard.getSquare(xLoc, yLoc+1);
        if (targSq.isOccupied()) {
            if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                ret.add(new Move(targSq , this , true)); // add if it can move right one and captures
        }
        else
            ret.add(new Move(targSq , this , false)); // add if it can move right one
    }

    return ret;
  }

  /**
   * Displays the King
   * @return return the string to be displayed
   */
  public String display() {
	if (this.team.isWhite) return "K"; // If white, then do upper case K
		return "k"; // if black then do lower case k.
  }
}
