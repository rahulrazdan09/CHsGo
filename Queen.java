import java.util.ArrayList;
/**
 * Queen is a class for the queen piece.
 * It extends piece and inherits all of its methods.
 * @author Rahul Razdan and Richard Xiao
**/
public class Queen extends Piece
{
  /**
   * Constructor 1 for Queen
   * @param side : team of queen
   * @param spot : location of piece
  **/
  public Queen(Side side, Square spot) 
  {
    super(side, spot); // initialize fields
    this.pieceType="Queen"; // set string
  }
  
  /**
   * Constructor 2 for Queen
   * @param team : team of queen
  **/
  public Queen(Side team) {
    super(team); // initialize fields
    this.pieceType="Queen"; // set string
  }

  /**
   * Constructor 3 for Queen used for duping
  **/
  public Queen()
  {
    super(); // initialize fields
    this.pieceType = "Queen"; // set string
  }

  /**
   * Creates a copy of the piece without adding it to the list of its team
   * @return copy instance of current instance
  **/
  public Piece dupe()
  {
    Queen ret = new Queen(); // initialize new instance
    ret.team = this.team; // set team of new instance
    return ret; // return new instance
  }
  
  /**
   * Gets all the moves of the queen
   * @return returns all moves including moves that threaten king
  **/
  public ArrayList<Move> getMoves(){
      ArrayList<Move> ret = this.vertHorizontalMoves(); // get vertical and horizontal moves
      ArrayList<Move> allDiagMoves = this.diagMoves(); // get diagonal moves
      ret.addAll(allDiagMoves); // get all moves
      return ret;
  }
  
  /**
   * Get all horizontal and vertical moves
   * @return returns all horizontal and vertical moves (even those that threaten king)
  **/
  public ArrayList<Move> vertHorizontalMoves()
  {
    int upSetter=0; // set variable
    int downSetter=0; // set variable
    ArrayList<Move> ret=new ArrayList<Move>(); // initialize return array list
    Board gameBoard = this.location.getBoard(); // get game board
    // For vertical
    for (int i = 1; i < 14; i++) {
        int y_coord = this.location.getY();
        Square upwardSquare = gameBoard.getSquare(this.location.getX()+i, y_coord);
        Square downwardSquare = gameBoard.getSquare(this.location.getX()-i,y_coord);
        if (upSetter==0) {
            if (upwardSquare.isOccupied()) {
                if (upwardSquare.getPiece().team != this.team) 
                    ret.add(new Move(upwardSquare,this,true)); // add move as it captured a piece
                upSetter=1;
            }
            else {
                ret.add(new Move(upwardSquare,this,false)); // add move as it the square empty
            }
        }
        if (downSetter==0) {
            if (downwardSquare.isOccupied()) {
                if (downwardSquare.getPiece().team != this.team) 
                    ret.add(new Move(downwardSquare,this,true)); // add move as it captured a piece
                downSetter=1;
            }
            else {
                ret.add(new Move(downwardSquare,this,false)); // add move as the square was empty
                
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
                    ret.add(new Move(upwardSquare,this,true)); // add move as it captured a piece
                rightSetter=1;
            }
            else {
                ret.add(new Move(upwardSquare,this,false)); // add move as the space was empty
            }
        }
        if (leftSetter==0 && leftward >= 0) {
            Square downwardSquare = gameBoard.getSquare(x_coord,leftward);
            if (downwardSquare.isOccupied()) {
                if (downwardSquare.getPiece().team != this.team) 
                    ret.add(new Move(downwardSquare,this,true)); // add move as it captured a piece
                leftSetter=1;
            }
            else {
                ret.add(new Move(downwardSquare,this,false)); // add move as the space was empty 
            }
        }
    }
    return ret;
  }
    /**
     * gets all diagonal moves for queen
     * @return returns all diagonal moves including those that threaten the king
    **/
    public ArrayList<Move> diagMoves(){
      // Make list of moves to be returned
      ArrayList<Move> ret = new ArrayList<Move>(); 

      // Get starting coordinates and the game board
      int startX = this.location.getX();
      int startY = this.location.getY();
      Board gameBoard = this.location.getBoard();

      // Create loop parameters outside of loop due to continuous usage
      int i = 0;
      Square targSq;
      while(startY + ++i < 8)
      {
        targSq = gameBoard.getSquare(startX + i, startY + i);
        if(!targSq.isOccupied())
        {
          ret.add(new Move(targSq, this, false)); // add as space was empty
        }
        else if(!targSq.getPiece().team.equals(this.team))
        {
          ret.add(new Move(targSq, this, true)); // add as piece was captured
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
          ret.add(new Move(targSq, this, false)); // add as space was empty
        }
        else if(!targSq.getPiece().team.equals(this.team))
        {
          ret.add(new Move(targSq, this, true)); // add as piece was captured
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
          ret.add(new Move(targSq, this, false)); // add as space was empty
        }
        else if(!targSq.getPiece().team.equals(this.team))
        {
          ret.add(new Move(targSq, this, true)); // add as piece was captured
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
          ret.add(new Move(targSq, this, false)); // add as space was empty
        }
        else if(!targSq.getPiece().team.equals(this.team))
        {
          ret.add(new Move(targSq, this, true)); // add as piece was captured
          break;
        }
        else
          break;
      }

      return ret;
    }
  /**
   * Display string version of queen
   * @return String version of queen
  **/
  public String display() {
      if (this.team.isWhite) return "Q"; // if White team then return Q
      return "q"; // if Black team then return q
  }

}
