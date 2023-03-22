import java.util.ArrayList;
/**
 * Rook is a class for the rook piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/
public class Rook extends Piece {

  /**
   * Constructor 1 of Rook
   * @param side : team of rook
   * @param spot : location of rook
  **/
  public Rook(Side side, Square spot) {
	super(side, spot); // initialize variables
	this.pieceType="Rook"; // set string
  }
  /**
   * Constructor 2 of Rook
   * @param team : team of rook
  **/
  public Rook(Side team) {
	super(team); // initialize variable
	this.pieceType="Rook"; // set string
  }

  /**
   * Constructor 3 of Rook used for duping
   */
  public Rook()
  {
    super(); // initialize variable
    this.pieceType = "Rook"; // set string
  }

  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return returns the new initialized rook
   */
  public Piece dupe()
  {
    Rook ret = new Rook(); // initialize new rook
    ret.team = this.team; // set team of new rook
    return ret;
  }
  
  /**
   * Gets all the moves of the Rook 
   * @return returns all the moves including moves that threaten the king
  **/
  public ArrayList<Move> getMoves()
  {
    int upSetter=0; // set variable
    int downSetter=0; // set variable
    ArrayList<Move> allMoves=new ArrayList<Move>(); // intialize return array
    Board gameBoard = this.location.getBoard(); // get game board
    // for vertical moves
    for (int i = 1; i < 14; i++) {
        int y_coord = this.location.getY();
        Square upwardSquare = gameBoard.getSquare(this.location.getX()+i, y_coord);
        Square downwardSquare = gameBoard.getSquare(this.location.getX()-i,y_coord);
        if (upSetter==0) {
            if (upwardSquare.isOccupied()) {
                if (upwardSquare.getPiece().team != this.team) 
                    allMoves.add(new Move(upwardSquare,this,true)); // add move as we can capture piece
                upSetter=1;
            }
            else {
                allMoves.add(new Move(upwardSquare,this,false)); // add move as space is empty
            }
        }
        if (downSetter==0) {
            if (downwardSquare.isOccupied()) {
                if (downwardSquare.getPiece().team != this.team) 
                    allMoves.add(new Move(downwardSquare,this,true)); // add move as we can capture piece
                downSetter=1;
            }
            else {
                allMoves.add(new Move(downwardSquare,this,false)); // add move as space is empty
                
            }
        }
    }
    int rightSetter=0; // set variable
    int leftSetter=0; // set variable
    // for horizontal moves
    for (int i = 1; i < 8; i++) {
        int x_coord = this.location.getX();
        int rightward= i + this.location.getY();
        int leftward= this.location.getY() - i;
    
        if (rightSetter==0 && rightward < 8) {
            Square upwardSquare = gameBoard.getSquare(x_coord, rightward);
            if (upwardSquare.isOccupied()) {
                if (upwardSquare.getPiece().team != this.team) 
                    allMoves.add(new Move(upwardSquare,this,true)); // add move as we can capture piece
                rightSetter=1;
            }
            else {
                allMoves.add(new Move(upwardSquare,this,false)); // add move as space is empty
            }
        }
        if (leftSetter==0 && leftward >= 0) {
            Square downwardSquare = gameBoard.getSquare(x_coord,leftward);
            if (downwardSquare.isOccupied()) {
                if (downwardSquare.getPiece().team != this.team) 
                    allMoves.add(new Move(downwardSquare,this,true)); // add move as we can capture piece
                leftSetter=1;
            }
            else {
                allMoves.add(new Move(downwardSquare,this,false)); // add move as space is empty   
            }
        }
    }
    
    return allMoves;
  }

  /**
   * Displays string of rook
   * @return returns the string for rook
  **/
  public String display() {
	if (this.team.isWhite) return "R"; // if white team return R
	return "r"; // if black team return r
  }

}
