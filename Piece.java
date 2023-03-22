import java.util.ArrayList;
/**
 * Piece is an abstract class.
 * @author Rahul Razdan and Richard Xiao
**/

public abstract class Piece

{
  public Side team; // team of the piece
  public Square location; // location of the piece
  public String pieceType; // String formatting of the piece
  
  /**
   * Constructor 1 for piece
  **/
  public Piece()
  {
    this.team = null; // Set team to null
    this.pieceType = "glitched"; // Set pieceType to arbitrary string
  }
  
  /**
   * Constructor 2 for piece
   * @param team : team of the piece
  **/
  
  public Piece(Side team)
  {
    this.team = team; // Sets the team of the piece 
    team.addPiece(this); // Adds the piece to the pieces array in side
  }
  
  
  /**
   * Constructor 3 for piece
   * @param team : team of the piece
   * @param loc : location of the piece
  **/
  public Piece(Side team, Square loc)
  {
    this.team = team; // Sets the team of the piece
    team.addPiece(this); // Adds the piece to the pieces array in side
    this.location = loc; // Sets the location of the piece
    loc.setPiece(this); // Places the piece on the square
  }
  
  /**
   * creates a new instance of the piece
   * @return returns new instance of the piece
  **/
  abstract Piece dupe();
  
  /**
   * Removes piece after bieng captures
  **/
  public void captured()
  {
    this.team.getPieces().remove(this); // removes captured piece
  }
  
  
  /**
   * Moves the piece to new square
   * @param destination : destination square
   * @return return the destination square
  **/
  public Square move(Square destination)
  {
    if(this.location != null)
      this.location.setPiece(null); // set the previous square to null
    this.location = destination; // Sets the location of the piece to the destination 
    destination.setPiece(this); // Sets the piece on the square
    return this.location; 
  }
  
  /**
   * Get all possible moves for the piece
   * @return all moves for the piece
  **/
  abstract ArrayList<Move> getMoves();
  
  
  /**
   * displays the piece
   * @return String that displays the piece
   */
  abstract String display();

  /**
   * toString for a piece
   * @return displays the string formatting of the piece
  **/
  public String toString()  {
    return this.pieceType + " on " + this.location;
  }
 
}
