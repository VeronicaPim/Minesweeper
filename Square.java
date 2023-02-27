/*Veronica Pimenova
  AP Computer Science A
  This program will represent the Square class, having useful methods such as printing
  the specific character on the Square at the moment or setting the square and its neighbors
  based on given information.*/

  //import to use ArrayList
  import java.util.ArrayList;

  public class Square{

	  //set up the instance variables
	  private boolean hasMine;
	  private boolean isOpen;
	  private int num;
	  private String character;
	  private ArrayList<Square> squares = new ArrayList<Square>();

  //constructor that initializes the instance variables
  public Square(){
	  hasMine = false;
	  isOpen = false;
	  num = 0;
	  character = "-";
  }

  //mutator method for the squares variable
  public void setSquares(ArrayList<Square> s)
  {squares = s;}

  /*This method will find the number on the square based on the array of Squares given.*/
  public void computeNum()
  {
    for(Square s: squares){
      if(s.getMine())
        num++;
    }
  }

  /*This method will find the character that should be printed based on what is in the Square.
  @param boolean x
  @return String based on if the Square has a mine, is open, has a number, etc.*/
  public String getChar(boolean x)
  {   if(x){
		 if(hasMine)
		   return "@";
	  }
	  if(character.equals("X")&&!isOpen)
	  	return "X";
	  if(isOpen){
         if(num==0)
           return "O";
      return Integer.toString(num);
      }
    return "-";
  }

  //mutator method for character variable
  public void setChar(String x)
  {character = x;}

  //accessor method for variable num
  public int getNum()
  {return num;}

  //mutator method for variable num
  public void setNum(int n)
  {num = n;}

  //accessor method for variable isOpen
  public boolean getOpen()
  {return isOpen;}

  /*This method is a mutator for variable isOpen.
  It also uses "flood-fill" to open any neighboring Squares that have 0 mines near them
  @param boolean m*/
  public void setOpen(boolean m)
  {
    isOpen = m;

      //check if the Square is 0 and it's not a mine, then open its neighbors (this is flood-fill)
      if(num==0&&!getMine()){
         for(Square s: squares){
           if(!s.getOpen())
              s.setOpen(true);
         }
      }
  }

  //accessor method for variable hasMine
  public boolean getMine()
  {return hasMine;}

  //mutator method for variable hasMine
  public void setMine(boolean m)
  {hasMine = m;}
}
