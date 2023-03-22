
/**
 * Square is a class for the square on a game board.
 * @author Rahul Razdan and Richard Xiao
**/
public class Square {
	private Board board; // board: board on which square is on
	private int xloc; // xloc : x position of square
	private int yloc; // yloc : y position of square
	private boolean isOccupied; // isOccupied : boolean on whether square has a piece
	private Piece piece; // piece : The piece which is on the square
	
	/**
	 * Constructor for Square class
	 * @param xloc : x location of square
	 * @param yloc : y location of square
	 * @param piece : piece on the square
	 * @param board : board of which the square is on
	**/
	public Square(int xloc, int yloc, Piece piece, Board board) {
		this.xloc = xloc; // Set xloc to input
		this.yloc = yloc; // Set yloc to input
		this.piece = piece; // Set piece to input
		this.board = board; // Set board to input
		if(piece != null)
			this.isOccupied = true; // If piece is not null, then it is occupied
		else
			this.isOccupied = false; // If piece is null, then it is not occupied
	}
	
	/**
	 * Accessor method for x location
	 * @return returns x location
	**/
	
	public int getX() {
		return this.xloc;
	}
	
	/**
	 * Accessor method for y location
	 * @return returns y location
	**/
	
	public int getY() {
		return this.yloc;
	}
	
	/**
	 * Accessor method for board
	 * @return returns the board
	**/
	public Board getBoard()
	{
    return board;
	}
	
	/**
	 * Accessor method for isOccupied
	 * @return returns isOccupied
	**/
	
	public boolean isOccupied() {
		return this.isOccupied;
	}
	
	/**
	 * Accessor method for piece
	 * @return returns piece
	**/
	
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * Sets a piece on a square
	 * @param incoming : Piece to be set on square
	 */
	public void setPiece(Piece incoming) 
	{
		if(this.isOccupied && incoming != null)
			this.piece.captured(); // If square is already occupied, then a capture has occurred
		
		this.isOccupied = false; // Initialize isOccupied to false
		this.piece=incoming; // Set piece to the incoming piece
		if(incoming != null)
			this.isOccupied = true; // If incoming is not null, set isOccupied to true
	}
	
	/**
	 * toString method 
	 * @return returns the string formatting for the square
	**/
	public String toString() 
	{
    char c = (char) (yloc + 97); // cast int to character
    if(xloc < 10 && xloc > 3)
		  return "+" + c + Integer.toString(11 - xloc); // Return string
    else if(xloc > 10 || xloc < 3)
      return "-" + c + Integer.toString((xloc + 5) % 14); // Return string
    else
      return "=" + c + Integer.toString(-xloc + 11); // Return string
	}
	
	
	/**
	 * Displays the square
	 * @return returns the piece if occupied otherwise returns a space string
	**/
	public String display() 
	{
		if(isOccupied)
      return this.piece.display(); // If piece on square, then display that piece
    return " "; // else display a space string
	}
}
