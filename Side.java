import java.util.ArrayList;
/**
 * Side is a class for the side (white or black).
 * @author Rahul Razdan and Richard Xiao
**/
public class Side
{
  private Game game; // game: Get the game instance for the side
  private ArrayList<Piece> pieces; // pieces: All the pieces for the side
  private boolean toMove; // toMove: boolean for whose turn it is to move
  private boolean inCheck; // inCheck: Determines whether current side is in check
  private Side enemy; // enemy: Gets the enemy side
  public final boolean isWhite; // isWhite: Determines if the side is white or black
  public final String name; // name: String for the name of the side

  /**
   * Constructor for Side class
   * @param isWhite: Takes input for whether side is white or black
   * @param game: Takes input for the game instance
  **/
  public Side(boolean isWhite, Game game)
  {
	
    this.game = game; // Sets the field to the input game
    this.pieces = new ArrayList<Piece>();  // Initializes pieces to a new arraylist
    this.toMove = isWhite; // Initializes whites turn to move
    this.isWhite = isWhite; // Sets isWhite to the input isWhite
    this.name = ((isWhite) ? "White" : "Black"); // Sets the name
    this.inCheck = false; // Sets inCheck to false
  }
  
  /**
   * Sets the enemy team
  **/
  public void clash()
  {
    this.enemy = game.getEnemy(this); // Sets the enemy team
  }
  
  /**
   * Adds a piece to all the pieces for the instance of side
   * @param addP: Piece to be added to arrayList
  **/
  public void addPiece(Piece addP) 
  {
    this.pieces.add(addP); // Adds the piece
  }
  
  /**
   * Accessor method for getting pieces for the side
   * @return returns all the pieces for the side
  **/
  public ArrayList<Piece> getPieces()
  {
    return pieces; // return all pieces for the current instance of side
  }
  
  /**
   * Accessor method for checking if its the current instance of sides turn
   * @return returns if its the current instance's turn to move
  **/
  public boolean toMove()
  {
    return toMove; // return if its the current instance's turn to move
  }
  
  /**
   * Display all of the pieces on the current side
  **/
  public void inventory()
  {
    for(int i = 0; i < pieces.size(); i++)
    {
      System.out.println("[" + Integer.toString(i + 1) + "]: " + pieces.get(i)); // Display the pieces
    }
  }
  
  /**
   * Displays the arrayList in a fashionable way
   * @param options: ArrayList to be displayed and formatted
  **/
  private void formatList(ArrayList options)
  {
    for(int i = 0; i < options.size(); i++)
      System.out.println("[" + Integer.toString(i + 1) + "]: " + options.get(i)); // Displays the contents of options
  }
  
  /**
   * Move the piece for the current side
  **/
  public void move()
  {
    System.out.println("<===> " + name + " to move. <===>"); // Formatting
    if(inCheck)
      System.out.println(" <=> YOU ARE IN CHECK <=>  "); // Display if you are in check

    // getLegalPieces(), list pieces
    ArrayList<Piece> options_P = this.getLegalPieces(); // Get all the legal pieces you can move
    System.out.println("--- PIECE SELECTION ---"); // Formatting
    formatList(options_P); // Formatting

    // Prompt user to choose a piece with legal moves
    String input = "-";
    do
    {
      System.out.println("Input a value located between the square brackets ([x]):"); // Formatting
      input = game.inputter.next();
    }while(input.isEmpty() || !(Integer.parseInt(input) < options_P.size() + 1 && Integer.parseInt(input) > 0));

    int dex = Integer.parseInt(input) - 1; // Parse the integer and subtract 1
    Piece selected = options_P.get(dex); // Get the piece that the user selected
    ArrayList<Move> unfiltered = selected.getMoves(); // Get all moves (not only legal moves)
    //formatList(unfiltered);
    ArrayList<Move> options_M = new ArrayList<Move>(); // Initialize a new array list
    // Get legal moves
    for(int i = 0; i < unfiltered.size(); i++)
      if(isLegal(unfiltered.get(i))) 
        options_M.add(unfiltered.get(i)); // If move is legal, add it

    System.out.println("--- MOVE SELECTION ---"); // Formatting
    formatList(options_M); // Formatting

    // Prompt user to choose a move for said piece
    input = "-";
    do
    {
      System.out.println("Input a value located between the square brackets ([x]):");
      input = game.inputter.next(); // User input
    }while(!(Integer.parseInt(input) < options_M.size() + 1 && Integer.parseInt(input) > 0));

    // Run move() for that piece
    dex = Integer.parseInt(input) - 1; // Get the move selected
    selected.move(options_M.get(dex).toWhere()); // move the piece

    System.out.println(options_M.get(dex).display() + " was played."); // Formatting
    System.out.println(game.getBoard().altdisplay()); // Display the board
    
    toMove = false; // Turn concluded so set toMove to false
    inCheck = false; // Initialize inCheck to false

    if(this.checks(game.getBoard()))
      enemy.inCheck = true; // If in check, set inCheck to true

    // Checks to see if the game ends due to the move
    if(enemy.getLegalPieces().size() == 0)
    {
      if(enemy.inCheck)
      {
        System.out.println("CHECKMATE: " + name + " has won!"); // If checkmate, terminate game
        game.terminate(); // terminate
      }
      else
      {
        System.out.println("STALEMATE: " + enemy.name + " cannot make any legal moves."); // If Stalemate
        game.terminate(); // Terminate game
      }
    }

    enemy.toMove = true; // Set the enemys turn to move
  }
  
  /**
   * Gets the legal pieces for the current side
   * @return ret: Returns all the legal pieces that can move without threatening the king
  **/
  private ArrayList<Piece> getLegalPieces()
  {
    ArrayList<Piece> ret = new ArrayList<Piece>();
    for(int i = 0; i < pieces.size(); i++)
    {
      if(!pieces.get(i).getMoves().isEmpty())
      {
        ArrayList<Move> moves = pieces.get(i).getMoves(); // Get moves for the current piece
        while(!moves.isEmpty())
        {
          Move m = moves.remove(0); // Select a move
          if(isLegal(m))
          {
            ret.add(pieces.get(i)); // If the move is legal, add it
            break;
          }
        }
      }
    }

    return ret; 
  }
  
  /**
   * Checks if the move causes check
   * @param gameBoard : Current state of the gameBoard
   * @return returns if the move causes a threat on the king or not
  **/
  private boolean checks(Board gameBoard)
  {
    ArrayList<Move> threats = enemy.anticipate(gameBoard.getPieces()); // Anticipate enemy's action
    Piece enemyKing = enemy.getKing(gameBoard); // Get enemy king

    return isLethal(enemyKing.location, threats); // Return if the move is lethal
  }
  
  /**
   * Checks if the move is legal or not
   * @param m: Takes as input a move m
   * @return returns whether the move is legal or not
  **/
  private boolean isLegal(Move m)
  {
    Board hypothet = new Board(game.getBoard()); // Create duplicate board
    
    hypothet.move(m); // Move the piece on the duplicate board

    return !enemy.checks(hypothet); // Check if the move causes check
  
    
  }
  
  /**
   * Only gathers the possible threats of the OPPOSING side
   * @param ghosts : all pieces on the board
   * @return Possible threats of the opposing side
   */
  private ArrayList<Move> anticipate(ArrayList<Piece> ghosts)
  {
    ArrayList<Move> ret = new ArrayList<Move>(); // ret array list
    Piece ghost = null; // null initializer to ghost
    for (int i = 0; i < ghosts.size(); i++) 
    {
    	ghost = ghosts.get(i); // Get the piece
    	if (this.isWhite != ghost.team.isWhite) { // If different teams
    		ArrayList<Move> threats = ghost.getMoves(); // Get moves of the piece
    		ret.addAll(threats); // Add all the threats
    	}
    }
    return ret;
  }
  /**
   * Sees whether all the moves check the king even once
   * @param kingSquare : Location of king square
   * @param moves: All the moves of opposing team
   * @return whether the king is threatened or not
  **/
  private boolean isLethal(Square kingSquare , ArrayList<Move> moves) 
  {
	  for (int i = 0; i < moves.size(); i++) 
		  if (moves.get(i).toWhere() == kingSquare) // If the move is to king square
			  return true; // King is in threatened
	  return false; //King is not threatened
  }
  /**
   * Find the King
   * @param gameBoard : Get the current state of the game board
   * @return return the king piece
  **/
  private Piece getKing(Board gameBoard) 
  {
    Piece retKing = null; // Initialize retking
    Square targSq = null; // Initialize targSq
    Piece occupant = null; // Initialize occupant
	  for (int i = 0; i < 14; i++) {
		  for (int j = 0; j < 8; j++) {
        targSq = gameBoard.getSquare(i, j); // Get the current square
        if (!targSq.isOccupied())
				  continue;
        occupant = targSq.getPiece(); // Get the piece on the square
			  if (occupant.pieceType.equals("King") && occupant.team == this) 
				  retKing = occupant; // If it is the king then set retking to this piece
		  }
	  }
	  return retKing;
  }
}
