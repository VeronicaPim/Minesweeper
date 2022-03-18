/*Veronica Pimenova
  Period 6
  This program will represent the board method, using input for length, width, and mines
  to actually create a "board" that is an array of Squares. It will set the mines and numbers and use
  other needed methods*/

  //import to use ArrayList
  import java.util.ArrayList;

  public class Board{
      //instance variables
	  private Square[][] field;
	  private int length, width, mines;

	  //constructor (accepts length, width, and number of mines)
	  public Board (int l, int w, int m){
		  length = l;
		  width = w;
		  mines = m;

		  //create an array of Squares based on given numbers
		  field = new Square[length][width];

		     //initialize values in the array
             for(int i=0; i<field.length; i++){
                for(int j=0; j<field[i].length; j++)
                   field[i][j] = new Square();
                }
                //set the mines and numbers in the board (array of Squares)
                setMines();
                passNums();
	         }

     /*This method will find whether row and col is in the board
     @param int row, int col
     @return boolean true or false*/
     public boolean isValid(int row, int col){

		 //check that the row input is less than length and greater than 0
         if(!(row<length && row>=0))
            return false;

         //check that the col input is less than width and greater than 0
         if(!(col<width  && col>=0))
            return false;

         //return true if the conditions have been met
         return true;
     }

     //accessor method for the Square object at a specific location on the field
	 public Square getField(int x, int y){
		return field[x][y];
	 }

    /*This method will set the mines on the board*/
	public void setMines(){

		//put the needed number of mines on the board (field)
		for(int i=0; i<mines; i++){
          int row, col;

        do{
		  //set the row and cols of the mine by random
          row = (int)(Math.random()*length);
          col = (int)(Math.random()*width);
        }while(field[row][col].getMine());
			field[row][col].setMine(true);
		}
	}

    //This method will set the numbers on the board (field).
	public void passNums(){

        //call the setNumbers method for all the individual Squares of array field
		for(int r=0; r<length; r++){
			for(int c=0; c<width; c++){
              setNumbers(r, c);
			}
		}
	}

	/*This method will set numbers directly next to, above, below, or diagonally
	from the given Square object in the field array.
	@param int r, int c*/
	public void setNumbers(int r, int c){

	   //create a list that will get the information of the Square's neighbors
	   ArrayList<Square> list = new ArrayList<Square>();

       //arrays for what values to check/get information from
	   int deltaRow[] = {-1,-1,-1,0,0,1,1,1};
	   int deltaCol[] = {0,-1,1,-1,1,-1,0,1};
	   int nextRow, nextCol;

       //go through the neighboring Square objects
	   for(int i=0; i<8; i++){
		   nextRow = r + deltaRow[i];
		   nextCol = c + deltaCol[i];

           //add the neighboring Square object's information to the list
		   if(isValid(nextRow, nextCol)){
			   list.add(field[nextRow][nextCol]);
		   }
	   }

      //set the Squares based on the list made above and then compute the numbers
      field[r][c].setSquares(list);
      field[r][c].computeNum();
	}

    //This method will print the current board and the nums/characters currently on it/
	public void printBoard(){

        //print the board
		System.out.println("\n\tThe current board is: \n");
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
                System.out.print("   "+field[i][j].getChar(false));
		    }
        System.out.println();
        }
	}

    /*This method will print the board in the case of losing, or when a player has
    chosen a mine. ALL the mines will be printed out.*/
	public void printBoard2(){

		//print the board with all the mines
		System.out.println("\n\tThe board with mines (@): ");
		for(int i=0; i<length; i++){
			for(int j=0; j<width; j++){
				System.out.print("    "+field[i][j].getChar(true));
			}
			System.out.println();
		}
	}

    /*this method will open a specific Square in the field
    @param int row, int col*/
    public void open(int row, int col){
        field[row][col].setOpen(true);
    }

	/*This method will check if game is won (if the only non opened Squares have mines)
	@return boolean true or false*/
	public boolean checkIfGameWon(){
       for(int i=0; i<field.length; i++){
		   for(int j=0; j<field[i].length; j++){

               //if there is an unopened number (non-mine), then the user has not won
			   if(!field[i][j].getOpen()&&!field[i][j].getMine())
			   		return false;
		   }
	   }
       return true;
	}

	/*This method will add a flag to a specific Square in the field
	@param int row, int col*/
	public void flag(int row, int col){
		field[row][col].setChar("X");
	}

	/*This method will check if the game has been lost (the user chose a mine)
	@return boolean true or false*/
	public boolean checkIfGameLost(){
	   for(int i=0; i<field.length; i++){
		   for(int j=0; j<field[i].length; j++){

			   //check if a mine has been opened by the user's choice
		       if(field[i][j].getOpen()&&field[i][j].getMine())
		           return true;
		   }
	   }
       return false;
	}

    /*This method will check if the game is over
    @return boolean true or false*/
	public boolean checkIfGameOver(){

		//returns true if the game has been won or lost (meaning it is over)
		return checkIfGameWon()||checkIfGameLost();
	}
}