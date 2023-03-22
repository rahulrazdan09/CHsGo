import java.util.Scanner;
/**
 * Game is a class for the actual game.
 * @author Rahul Razdan and Richard Xiao
**/

public class Game
{
  private Side white; // white: Side for white pieces
  private Side black; // black: Side for black pieces
  private Board board;// board: Game board
  private boolean isActive; // isActive: boolean for if the game is actively running
  public Scanner inputter; // inputter: Input for the user
	
  /**
   * Constructor: Sets up the game of chess
  **/
  public Game()
  {
    white = new Side(true, this); // set white to a new side 
    black = new Side(false, this); // set black to a new side
    board = new Board(); // set board to a new board
    isActive = true; // set isActive to true
    inputter = new Scanner(System.in); // Create the scanner
    setup(); // set up the game
    System.out.println(board.altdisplay()); // Display the current state of the board
    int moveNum = 0; // Set number of moves to 0
    while(isActive && moveNum++ < 200) 
      turn(); // Initiate the turn of who has to move
    System.out.println("The game lasted " + moveNum + " moves."); // Upon termination, display number of moves
    if(moveNum >= 200)
      System.out.println("(Turn limit exceeded.)"); // If number of moves is greater than 200, display termination reasoning
    inputter.close(); // Close the scanner
  }
  
  /**
   * Method for determining whose move it is and initiating the move
  **/
  private void turn()
  {
    if(white.toMove())
    {
      white.move(); // If white turn, white moves
      return;
    }
    else
      black.move();  // if Black turn, black moves
  }
  
  /**
   * Setter method for isActive
  **/
  public void terminate()
  {
    isActive = false; // Terminates the game by setting isActive to false
  }
  
  /**
   * Sets up the game by placing pieces on the correct spot
  **/
  private void setup()
  {
	white.clash(); // Sets whites enemy
    black.clash(); // Sets blacks enemy
    for(int j = 0; j < 8; j++)
    	// Sets all the pieces in their respective spots
		{
		board.setPiece(2,  j, new UpPawn(black));
		if (j==0 || j==7) 
			board.setPiece(3,  j, new Rook(black));
		else if (j==1 || j==6)
			board.setPiece(3,  j, new Knight(black));
		else if (j==2 || j==5)
			board.setPiece(3,  j, new Bishop(black));
		else if (j==3)
			board.setPiece(3,  j, new Queen(black));
		else
			board.setPiece(3,  j, new King(black));
		board.setPiece(4,  j, new DownPawn(black));
		board.setPiece(9,  j, new UpPawn(white));
		if (j==0 || j==7) 
			board.setPiece(10,  j, new Rook(white));
		else if (j==1 || j==6)
			board.setPiece(10,  j, new Knight(white));
		else if (j==2 || j==5)
			board.setPiece(10,  j, new Bishop(white));
		else if (j==3)
			board.setPiece(10,  j, new Queen(white));
		else
			board.setPiece(10,  j, new King(white));
		board.setPiece(11,  j, new DownPawn(white));
	  }
    System.out.println("Board initialized."); // Display board initialized
  }
  
  /**
   * Gets the enemy of the side
   * @param side: Gets current side
   * @return returns opposing side
  **/
  public Side getEnemy(Side side)
  {
    if(side.isWhite)
      return black; // If side is white, return black
    return white; // If side is black, return white
  }
  
  /**
   * Accessor method for board
   * @return returns the current state of the board
  **/
  public Board getBoard()
  {
    return board;
  }
}