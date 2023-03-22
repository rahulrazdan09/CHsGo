import java.util.ArrayList;
/**
 * UpPawn is a class for the UpPawn piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/
public class UpPawn extends Piece{


  /**
   * Constructor 1 of UpPawn
   * @param team : team of UpPawn
   * @param spot : location of UpPawn
  **/
  public UpPawn(Side team, Square spot) {
	super(team, spot); // initialize fields
	if(team.isWhite)
	  this.pieceType = "Pawn"; // set string
    else
      this.pieceType = "Nwap"; // set string
	} 
  
  /**
   * Constructor 2 of UpPawn
   * @param team : team of UpPawn
   */
  public UpPawn(Side team) {
	super(team); // initialize fields
	if(team.isWhite)
	  this.pieceType = "Pawn"; // set string
    else
      this.pieceType = "Nwap"; // set string
	}
  
  /**
   * Constructor 3 of UpPawn used for Duping
   */
  public UpPawn()
  {
    super(); // initialize fields
  }

  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return returns a copied new instance of the current instance
   */
  public Piece dupe()
  {
    UpPawn ret = new UpPawn(); // initialize new instance
    ret.team = this.team; // set team of new instance
    if(team.isWhite) 
		  ret.pieceType = "Pawn"; // set string of new instance
    else
      ret.pieceType = "Nwap"; // set string of new instance
    return ret;
  }
  
  
  /**
   * Gets all the possible moves for the current instance
   * @return returns all moves including those that threaten the king
   */
  public ArrayList<Move> getMoves(){
	// Make list of moves to be returned
	ArrayList<Move> ret = new ArrayList<Move>();

    // Get starting coordinates and the game board
    int startX = this.location.getX();
    int startY = this.location.getY();
    Board gameBoard = this.location.getBoard();

    Square targSq; // Initialize target square
    
    // Check if the row below is valid
    targSq = gameBoard.getSquare(startX - 1, startY);
    if(!targSq.isOccupied())
    {
      ret.add(new Move(targSq, this, false));
      if((this.team.isWhite && startX == 9) || (!this.team.isWhite && startX == 2))
      {
        targSq = gameBoard.getSquare(startX - 2, startY);
        if(!targSq.isOccupied())
          ret.add(new Move(targSq, this, false)); // add to moves as space is empty
      }
    }
    
    // Check if an opposing piece can be captured
    if(startY > 0)
    {
      targSq = gameBoard.getSquare(startX - 1, startY - 1);
      if(targSq.isOccupied() && (targSq.getPiece().team != this.team))
        ret.add(new Move(targSq, this, true)); // add to moves as opposing piece can be captured
    }

    if(startY < 7)
    {
      targSq = gameBoard.getSquare(startX - 1, startY + 1);
      if(targSq.isOccupied() && (targSq.getPiece().team != this.team))
        ret.add(new Move(targSq, this, true)); // add to moves as opposing piece can be captured
    }

    return ret;
	}
	
  /**
   * Displays the UpPawn
   * @return Returns a string for the UpPawn
   */
  public String display() {
	if (this.team.isWhite) 
      return "P"; // if team is white then string is P
	return "i"; // else string is p
	}
}
