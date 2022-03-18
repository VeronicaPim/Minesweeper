/*Veronica Pimenova
  Period 6
  This program will represent the Player class for Minesweeper and it will
  perform actions such as playing the game and getting input. It will use the
  Square and Board classes.*/

  //import for Scanner (user input)
  import java.util.Scanner;

  public class MinePlayer{
     //instance variable
     private String name;

     //constructor
     public MinePlayer(String name)
     {this.name = name;}

     //accessor method for name
     private String getName()
     {return name;}

     /*This method will simulate the game being played, it will use Square and Board classes.
     @param int length, int width, int mines*/
     public void playGame(int length, int width, int mines){
		 Scanner sc = new Scanner(System.in);

	    //create a board object with length, width, and mines
        Board b = new Board(length, width, mines);

		//print the initial board
        b.printBoard();

         //play the game until it is over
         while(!b.checkIfGameOver()){
			System.out.print("\n\n\tWould you like to f (flag) or o (open) a square? ");
			String opt = validateOpt(sc.nextLine());

			if(opt.equalsIgnoreCase("f"))
				flag(b);
			else
				askInput(b);

            if(!b.checkIfGameOver())
            	b.printBoard();
         }
            //print statements if the game was won
            if(b.checkIfGameWon()){
		       System.out.println("\n\t\tCONGRATS! The only squares left are mines.");
		       b.printBoard();
    	       System.out.println("\n\t\tYou win!");
	        }

	        //print statements if the game was lost
            else{
		       System.out.println("\n\t\tOOPS! You hit a mine.");
		       b.printBoard2();
    	       System.out.println("\n\t\tYou lost.");
	        }
         }

     /*This method will validate the user's choice of flagging or opening a space
     @param String s
     @return String s validated*/
     public String validateOpt(String s){
		 Scanner sc = new Scanner(System.in);
		 	while(!(s.equalsIgnoreCase("f")||s.equalsIgnoreCase("o"))){
				System.out.println("\t\t\tPlease enter f or o: ");
				s=sc.nextLine();
			}
		return s;
	 }

     /*This method will get input from the user for each turn (the user will enter the row and column
     they would like to pick from the board).
     @param Board b*/
     public void askInput(Board b){

		//set up scanner for user input
        Scanner sc = new Scanner(System.in);

        //user input for row and column
        System.out.print("\n\tPlease enter the row and col of the square you would like to pick (ex: 1 1 is row 1, col 1): ");
        String input = sc.nextLine();

           //validate input based on the validate method
    	   while(!validateInput(input, b)){
		 	  System.out.println("\t\t\tPlease enter valid input: ");
		 	  input = sc.nextLine();
		   }

		//cast the String input into ints and pass them into the open method in board
        String[] arr = input.split(" ");

        //user parseInt to convert String to int
        int first = Integer.parseInt(arr[0])-1;
        int second = Integer.parseInt(arr[1])-1;
        b.open(first, second);
     }

     /*This method will add a flag or multiple flags based on user input
     @param Board b*/
     public void flag(Board b){
		 Scanner sc = new Scanner(System.in);
		 //user input for number of flags to add
		 		System.out.print("\n\tHow many flags would you like to add (0 for none): ");
		 		int choice = sc.nextInt();
		 			while(choice<0){
		 				System.out.print("\t\t\tPlease enter a valid number: ");
		 				choice = sc.nextInt();
		 			}
		         sc.nextLine();

		 	    //user input for flagging
		 		for(int i=0; i<choice; i++){
					if(i==0)
						System.out.print("\n\tPlease enter the row and col of the first square you would like to flag: ");
					else if(i==1)
						System.out.print("\n\tPlease enter the row and col of the second square you would like to flag: ");
					else if(i==2)
						System.out.print("\n\tPlease enter the row and col of the third square you would like to flag: ");
					else
		 				System.out.print("\n\tPlease enter the row and col of "+(i+1)+"th square you would like to flag: ");
		 			String flag = sc.nextLine();

		 			    //validate the input for what square they want to flag
		 				while(!validateInput(flag, b)){
		 					System.out.print("\t\t\tPlease enter valid input: ");
		 					flag = sc.nextLine();
		 				}

		 			String[] flagArr = flag.split(" ");
		 			b.flag((Integer.parseInt(flagArr[0])-1), (Integer.parseInt(flagArr[1])-1));
		}
	 }

     /*This method will validate the input given for the user's row/col choice
     @param String sq, Board b
     @return true or false*/
     public boolean validateInput(String sq, Board b){

		//check if the input is empty
	    if(sq.isEmpty())
	  	   return false;

	  	//check that the user entered two "things"
        String[] arr = sq.split(" ");
        if(arr.length!=2)
          return false;

        //create temporary row & col variables
        int row, col;

        //use a try-catch to make sure the user entered numbers and not string
        try {
          row = Integer.parseInt(arr[0])-1;
          col = Integer.parseInt(arr[1])-1;
        }

        //if the exception is thrown, return false (that the input is invalid)
        catch (NumberFormatException ex) {
          return false;
        }

        //check that the actual numbers are valid based on board's isValid method
        if(!b.isValid(row,col))
          return false;

        //check that the user has not chosen an already open space
        if(b.getField(row,col).getOpen())
        	return false;

        //return true if all the conditions work
	    return true;
    }
}