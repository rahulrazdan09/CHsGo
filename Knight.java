import java.util.ArrayList;

/**
 * Knight is a class for the knight piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/

public class Knight extends Piece {
	

  /**
   * Constructor 1 for knight
   * @param side : team of knight
   * @param spot : location of knight
  **/
  public Knight(Side side, Square spot) {
	super(side, spot); // initialize fields
	this.pieceType="Knight"; // set string
	}

  /**
   * Constructor 2 for knight
   * @param team : team of knight
  **/
  public Knight(Side team) {
	super(team); // initialize fields
	this.pieceType="Knight"; // set string
	}

  /**
   * Constructor 3 for Knight. Used for duping
  **/
  public Knight()
  {
    super(); // initialize fields
    this.pieceType = "Knight"; // set string
  }

  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return new instance of Knight
  **/
  public Piece dupe()
  {
    Knight ret = new Knight(); // create new instance of knight
    ret.team = this.team; // Set the team for this new instance
    return ret; 
  }
  
  /**
   * get all moves for the knight
   * @return returns all the moves including those that threaten the king 
  **/
  public ArrayList<Move> getMoves(){
      ArrayList<Move> ret = new ArrayList<Move>(); // initialize the return array list
      Board gameBoard = this.location.getBoard(); // get game board
      int xLoc=this.location.getX(); // get x location
      int yLoc=this.location.getY(); // get y location
      Square targSq; // set target square
      // If knight can move up two and right one
      if (yLoc+1<=7) {
          targSq = gameBoard.getSquare(xLoc+2, yLoc+1);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq, this , true)); // add if it can move up two and right one and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move up two and right one
      }
      // If knight can move up two and left one
      if (yLoc-1>=0) {
          targSq = gameBoard.getSquare(xLoc+2, yLoc-1);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq, this , true)); // add if it can move up two and left one and captures
          }
          else 
              ret.add(new Move(targSq, this , false)); // add if it can move up two and left one 
      }
      // If it can move up one and right two
      if (yLoc+2<=7) {
          targSq = gameBoard.getSquare(xLoc+1, yLoc+2);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move up one and right two and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move up one and right two and captures
      }
      // If it can move up one and left two
      if (yLoc-2>=0) {
          targSq = gameBoard.getSquare(xLoc+1, yLoc-2);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move up one and left two and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move up one and left two
      }
      // If it can move down two and right one
      if (yLoc+1<=7) {
          targSq = gameBoard.getSquare(xLoc-2, yLoc+1);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move down two and right one and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move down two and right one 
      }
      // if it can move down two and left one
      if (yLoc-1>=0) {
          targSq = gameBoard.getSquare(xLoc-2, yLoc-1);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move down two and left one and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move down two and left one 
      }
      // if it can move down 1 and left two
      if (yLoc - 2 > -1) {
          targSq = gameBoard.getSquare(xLoc-1, yLoc-2);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move down one and left two and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move down one and left two
      }
      // If it can move down one and right two
      if (yLoc+2<=7) {
          targSq = gameBoard.getSquare(xLoc-1, yLoc+2);
          if (targSq.isOccupied()) {
              if (targSq.getPiece().team.isWhite!=this.team.isWhite)
                  ret.add(new Move(targSq , this , true)); // add if it can move down one and right two and captures
          }
          else 
              ret.add(new Move(targSq , this , false)); // add if it can move down one and right two 
      }

      return ret;
  }
  
  
  /**
   * Displays the knight
   * @return returns the string for the knight
  **/
  public String display() {
	if (this.team.isWhite) return "N"; // If white, return N
	return "n"; // If black, return n
  }

}
