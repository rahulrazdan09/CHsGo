import java.util.ArrayList;

/**
 * DownPawn is a class for the downPawn piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/

public class DownPawn extends Piece {
  /**
   * Constructor 1 for downPawn
   * @param team : team of the instance
   * @param spot : location of the instance
  **/
	
  public DownPawn(Side team, Square spot) {
	super(team, spot); // initialize fields
    if(team.isWhite)
		  this.pieceType = "Nwap"; // set string
    else
      this.pieceType = "Pawn"; // set string
	}

  /**
   * Constructor 2 of DownPawn
   * @param team : team of the downPawn
  **/
  public DownPawn(Side team) {
	super(team); // initialize fields
	if(team.isWhite)
	  this.pieceType = "Nwap"; // set string
    else
      this.pieceType = "Pawn"; // set string
	}

  /**
   * Constructor 3 : Used for duping method
  **/
  public DownPawn()
  {
    super(); // initialize fields
  }
  
  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return returns the new instance
   */
  public Piece dupe()
  {
    DownPawn ret = new DownPawn(); // creates new instance
    ret.team = this.team; // sets the team
    if(team.isWhite)
		  ret.pieceType = "Nwap"; // sets string
    else
      ret.pieceType = "Pawn"; // sets string
    return ret;
  }
  
  /**
   * Gets all the moves for downPawn
   * @return returns all the moves (including illegal ones that threaten the king)
  **/
  public ArrayList<Move> getMoves()
  {
	// Make list of moves to be returned
	ArrayList<Move> ret = new ArrayList<Move>();

    // Get starting coordinates and the game board
    int startX = this.location.getX();
    int startY = this.location.getY();
    Board gameBoard = this.location.getBoard();

    Square targSq;
    // Get target square
    targSq = gameBoard.getSquare(startX + 1, startY);
    if(!targSq.isOccupied())
    {
      ret.add(new Move(targSq, this, false)); // if it isnt occupied, add this move
      if((this.team.isWhite && startX == 11) || (!this.team.isWhite && startX == 4))
      {
        targSq = gameBoard.getSquare(startX + 2, startY); // If this move isnt occupied, add this move
        if(!targSq.isOccupied())
          ret.add(new Move(targSq, this, false));
      }
    }
    
    if(startY > 0)
    {
      targSq = gameBoard.getSquare(startX + 1, startY - 1);
      if(targSq.isOccupied() && (targSq.getPiece().team != this.team)) 
        ret.add(new Move(targSq, this, true)); // Add move for capturing opposing piece
    }

    if(startY < 7)
    {
      targSq = gameBoard.getSquare(startX + 1, startY + 1); // Add move for capturing opposing piece
      if(targSq.isOccupied() && (targSq.getPiece().team != this.team))
        ret.add(new Move(targSq, this, true));
    }

    return ret;
	}
	
  /**
   * Displays the string formatting for the piece
   * @return Return string formatting
  **/
	public String display() {
		if (this.team.isWhite) return "I"; // If white team, it is inverted pawn
		return "p"; // Else it is a regular pawn
	}

}
