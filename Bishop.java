import java.util.ArrayList;
/**
 * Bishop is a class for the bishop piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/
public class Bishop extends Piece {
 /**
  * Constructor 1 creates a bishop given the side and square.
  * Constructor sets the side and spot to its appropriate fields
  * @param side: Correct side the bishop represents
  * @param spot: Correct spot on which the Bihsop is on
  **/
  public Bishop(Side side, Square spot) {
	super(side, spot); // Calls super class Piece
	this.pieceType="Bishop"; // Sets pieceType to "Bishop"
	}
  /**
   * Constructor 2 creates a bishop class
   * @param team : team of bishop instance
   */
  public Bishop(Side team) {
	super(team); // Calls super class piece
	this.pieceType="Bishop"; // Sets pieceType to "Bishop"
	}

  /**
   *  Constructor 3 creates a bishop given nothing. This is for the duping method
  **/
  public Bishop()
  {
    super(); // Calls the default Piece constructor with no variables
    this.pieceType = "Bishop"; // Sets pieceType to Bishop
  }

  /** 
   * Creates a copy of the piece without adding it to the list of its team
   * @return Returns a new Bishop with the same team as the current bishop
  **/
  public Piece dupe()
  {
    Bishop ret = new Bishop(); // Creates a new instance of bishop
    ret.team = this.team;  // Sets the team equal to the current instance 
    return ret; // Returns this new instance of bishop
  }

  /**
   * Gets all moves (not only legal moves) for the current instance of bishop
   * @return ArrayList<Move> ret of all the possible moves it can do
  **/
  public ArrayList<Move> getMoves()
  {
    // Make list of moves to be returned
		ArrayList<Move> ret = new ArrayList<Move>();

    // Get starting coordinates and the game board
    int startX = this.location.getX();
    int startY = this.location.getY();
    Board gameBoard = this.location.getBoard();

    // Create loop parameters outside of loop due to continuous usage
    int i = 0;
    Square targSq;

    // Down-right direction
    while(startY + ++i < 8)
    {
      targSq = gameBoard.getSquare(startX + i, startY + i);
      if(!targSq.isOccupied())
      {
        ret.add(new Move(targSq, this, false));
      }
      else if(!targSq.getPiece().team.equals(this.team))
      {
        ret.add(new Move(targSq, this, true));
        break;
      }
      else
        break;
    }

    // Up-right direction
    i = 0;
    while(startY + ++i < 8)
    {
      targSq = gameBoard.getSquare(startX - i, startY + i);
      if(!targSq.isOccupied())
      {
        ret.add(new Move(targSq, this, false));
      }
      else if(!targSq.getPiece().team.equals(this.team))
      {
        ret.add(new Move(targSq, this, true));
        break;
      }
      else
        break;
    }

    // Down-left direction
    i = 0;
    while(startY - ++i > -1)
    {
      targSq = gameBoard.getSquare(startX + i, startY - i);
      if(!targSq.isOccupied())
      {
        ret.add(new Move(targSq, this, false));
      }
      else if(!targSq.getPiece().team.equals(this.team))
      {
        ret.add(new Move(targSq, this, true));
        break;
      }
      else
        break;
    }

    // Up-left direction
    i = 0;
    while(startY - ++i > -1)
    {
      targSq = gameBoard.getSquare(startX - i, startY - i);
      if(!targSq.isOccupied())
      {
        ret.add(new Move(targSq, this, false));
      }
      else if(!targSq.getPiece().team.equals(this.team))
      {
        ret.add(new Move(targSq, this, true));
        break;
      }
      else
        break;
    }

    return ret;
	}
	
	/**
	 * Display for the chess board
	 * @return Returns capital B for the white team and lower case B for the black team
	 **/
	
  public String display() {
	if (this.team.isWhite) return "B"; // Team is white so return B
	return "b"; // Team is black so return b
	}

}
