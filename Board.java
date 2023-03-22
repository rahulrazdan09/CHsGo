import java.util.*;

/**
 * Board is a class for the game board
 * @author Rahul Razdan and Richard Xiao
**/

public class Board {
	
  private Square grid[][]; // The chessboard made of many squares
	/**
	 * Constructor 1: The default constructor. This is used to set the chess board up.  
	 **/
	
  public Board() 
  {
	this.grid = initGrid(); // Calls initGrid to set the board up
  }
  /**
	* Constructor 2: The constructor used to create the duplicate gameBoard for threat detection
	* @param gameBoard: The current state of the main gameBoard
  **/
	
  public Board(Board gameBoard)
  {
    Square[][] original = gameBoard.grid; // Set the original gameBoard to original
    this.grid = initGrid(); // Initiate the grid
    // Set the pieces up in the correct place
    for(int i = 0; i < 14; i++) {
      for(int j = 0; j < 8; j++) {
        if(original[i][j].getPiece() != null) {
          grid[i][j].setPiece(original[i][j].getPiece().dupe()); //Create a new instance of the piece and set it
          grid[i][j].getPiece().location = grid[i][j]; // Set the location of this new piece
        }
      }
    }
  }
  
  
  
  /**
   * Gets all the pieces for the current state of the board
   * @return ret: returns all the pieces on the board
  **/
  
  public ArrayList<Piece> getPieces()
  {
    ArrayList<Piece> ret = new ArrayList<Piece>(); // Return array list 
    // Scan through the entire board and report on all of the pieces
    for(int i=0; i<14; i++)
		for(int j=0; j<8; j++) 
			if(grid[i][j].getPiece() != null) // If there is a piece on the current square
				ret.add(grid[i][j].getPiece()); // Add it to the return ArrayList

    return ret; // Return all the pieces
  }
  
  /** 
   * Initiates the grid and sets all Squares to have no pieces
   * @return returns the board after initiating it
  **/
  public Square[][] initGrid() 
  {
	Square newBoard[][] = new Square[14][8];
	for(int i=0; i<14; i++) {
		for(int j=0; j<8; j++) {
			newBoard[i][j] = new Square(i, j, null, this); // Set the square to have no piece
		}
	}
	
	return newBoard;
  }
  
  
  /**
   * Accessor Method for getting the square in a specific position
   * @param xLoc: The x location of the square we wish to get
   * @param yLoc: The y location of the square we wish to get
   * @return The square at the x and y coordinates
  **/
  public Square getSquare(int xLoc, int yLoc) 
  {
    int mxLoc = (xLoc + 14) % 14; // Add 14 to avoid negative and then mod 14 to get correct space
	return this.grid[mxLoc][yLoc]; // Get the square
	}
	
	public void setPiece(int xpos, int ypos, Piece piece)
	{
		if(piece.location != null)
    {
      System.out.println("setPiece is only meant for pieces that have not been placed on the board - please use move() instead!");
      return;
    }
		piece.move(grid[xpos][ypos]);
	}
	
  
  /**
   * Moves the piece to its correct location
   * @param Takes in a move to actually move the piece
  **/
  public void move(Move m)
  {
    Piece mover = m.getPiece(); // Get the piece to move
    Square iniSq = mover.location; // Get the current location of the piece
    Square finSq = m.toWhere(); // Get the new location of the piece

    int Xi = iniSq.getX(); // Get the x coordinate of current location
    int Yi = iniSq.getY(); // Get the y coordinate of current location
    int Xf = finSq.getX(); // Get the x coordinate of new location
    int Yf = finSq.getY(); // Get the y coordinate of new location

    Piece agent = grid[Xi][Yi].getPiece(); // Get piece for the square
    agent.move(grid[Xf][Yf]); // Move the piece from the square to the new square
  }
	
  /**
   * Displays the chess board in a 14 by 8 fashion
   * @return returns the display of a 14 by 8 chess board
  **/
  public String display()
  {
	String vertstr = "     a b c d e f g h\n"; // Header
	String spacer = "    +=+=+=+=+=+=+=+=+\n"; // Divider
    String newString = ""; //Initiating string
	newString += vertstr + spacer; // Add the header and divider
	for (int i=0; i<14; i++) 
    {
      String horzstr = ""; // Initiate string to add
      if(i < 10 && i > 3)
		    horzstr = "+" + Integer.toString(11 - i); // Add string to horizontal string
      else if(i > 10 || i < 3)
        horzstr = "-" + Integer.toString((i + 5) % 14); // Add string to horizontal string
      else
        horzstr = "=" + Integer.toString(-i + 11); // Add string to horizontal string
			newString += horzstr; // Add the horizontal string to the string we want to return 
			newString += "  ";  // Add a space
			for (int j=0; j<8; j++) {
				newString+="|"; // Add a divider
				newString+=this.grid[i][j].display(); // Display the piece
				if (j==7) {
					newString+="|\n"; // Add divider and get new line
				}
			}
			newString += spacer; // Add spacer to newString
		}
		newString += vertstr; // Add vertical string to newString
		
		return newString; // Return newString
	}

  /**
   * Displays the chess board in a new fashion
   * @return returns the display of a 8x8 and 6x8 chess board
  **/
  public String altdisplay()
  {
    String vertstr = "     a b c d e f g h\n"; // Header
	String spacer = "    +=+=+=+=+=+=+=+=+"; // Divider
    String horzstr = "", frontstr = "", backstr = ""; // Set new strings to empty strings
    String newString = ""; // set return string to empty string
    
	newString += vertstr + spacer + "\n";
	
	// Build the newString
	for (int i = 0; i < 8; i++) 
    {
      if(i < 7 && i > 0)
		    horzstr = "+" + Integer.toString(8 - i); // Set horizontal String
      else
        horzstr = "=" + Integer.toString(-i + 8); // Set horizontal String

			newString += horzstr; // Add horizontal String to newString
			newString += "  ";  // Add spacing to newString

      horzstr = ""; //Reset horizontal string to an empty string
      if(i < 7 && i > 0)
		    horzstr = "-" + Integer.toString(8 - i); // Set horizontal String

      frontstr = ""; // Reset front string
      backstr = ""; // Reset back string
	  for(int j=0; j<8; j++) 
      {
		frontstr += "|" + this.getSquare(3 + i, j).display(); // Add the piece
        backstr += "|" + this.getSquare(3 - i, j).display(); //Add the piece

        if(j == 7)
        {
          frontstr += "|"; // Add divider
          backstr += "|"; // Add divider
        }
			}

      if(i == 0 || i == 7)
        newString += frontstr + "    " + vertstr; // add front string and vertical string to newString
      else
        newString += frontstr + "    " + horzstr + "  " + backstr + "\n"; // add front string and horizontal string and back string to newString

			newString += spacer; // Add spacer

      if(i < 7)
      {
        newString += "    " + spacer; // Add spacer
      }

      newString += "\n"; // add new line
		}
		newString += vertstr; // Add vertical string
		
		return newString; // Return the new string
  }
}
