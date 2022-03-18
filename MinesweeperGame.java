/*Veronica Pimenova
  Period 6
  This program will represent the Driver for the minesweeper game, where a user will enter
  a length and width for a board, and the number of mines. Then, the user will guess the location
  of squares WITHOUT mines. If they hit a mine, they lose, and if they get to a point where all the non-mine
  squares were chosen, they win. The game will continue until the user decides to stop playing.*/

  //import for scanner (user input)
  import java.util.Scanner;

  public class MinesweeperGame{
	  public static void main(String[] args){

		  //print the introduction
		  Scanner sc = new Scanner(System.in);
		  System.out.println("\n\t\t\tWELCOME TO THE MINESWEEPER GAME");

		  //user input for name
		  System.out.println("\n\tWhat is your name?");
		  String name = sc.nextLine();

		    //validate that something was entered
		  	while(name.isEmpty()){
				System.out.println("\n\t\tPlease enter a name: ");
				name = sc.nextLine();
			}

		  //create a player object
		  MinePlayer player = new MinePlayer(name);

		  //print the rules and ask the user to start the game
		  printRules();
		  System.out.println("\n\t\tHit enter or type something to start: ");
		  String start = sc.nextLine();

          //set up rerun for do-while so user can play again
		  String rerun = "";
		  do{

			 //user input for length of the board
             System.out.print("\n\tPlease enter the length of the board: ");
             int length = valid(sc.nextInt());

             //user input for width of the board
             System.out.print("\n\tPlease enter the width of the board: ");
             int width = valid(sc.nextInt());

             //user input for number of mines
             System.out.print("\n\tPlease enter the number of mines: ");
             int mines = sc.nextInt();

                //validate that the mines are positive and valid based on length and width
             	while(!(mines<=(length*width)&&mines>0)){
					System.out.println("\n\t\tPlease enter a valid number of mines: ");
					mines = sc.nextInt();
				}

             //play the game with a method from MinePlayer class
			 player.playGame(length, width, mines);

             //user input to play again
             sc.nextLine();
		  	 System.out.print("\n\tWould you like to play again? ");
		  	 rerun = sc.nextLine();

		  	    //validate that they entered y or n (for yes or no)
		  	 	while(!(rerun.equalsIgnoreCase("y")||rerun.equalsIgnoreCase("n"))){
					System.out.println("\n\t\tPlease enter y (yes) or n (no): ");
					rerun = sc.nextLine();
				}
		  }
		  //end of do-while
		  while(rerun.equalsIgnoreCase("y"));

          //print statement for end of game
		  System.out.println("\n\t\tThank you for playing, "+name+". Bye!");
	  }
	  /*This method will print the rules of the game.*/
	  public static void printRules(){
		  System.out.println("\n\t\t\tHERE ARE THE RULES: ");
		  System.out.println("\n\tThere will be a board of spaces, and you will be asked to enter a space on the board.");
		  System.out.println("\tThat space will reveal a number 1-8, which is the number of mines around it (to the left, right, above, below, or diagonally).");
		  System.out.println("\tKeep picking spaces until there are none left or you have chosen a mine.");
		  System.out.println("\tYou may also pick a space to \"flag\", which can still be opened later.\n\tAn already opened space will not be flagged.");
		  System.out.println("\n\tKEY:\n\t- : open\n\t1-8 : number of mines around it\n\t@ : a mine\n\tO : 0 mines around it\n\tX : a flag");
	  }

	  /*This method will make sure the input is greater than zero.
	  @param int x
	  @return int x validated*/
	  public static int valid(int x){
		  Scanner sc = new Scanner(System.in);
		  	while(x<=0){
				System.out.println("\n\t\tPlease enter a positive value: ");
			    x = sc.nextInt();
			}
		  return x;
	  }
  }
