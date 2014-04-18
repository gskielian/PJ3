import java.util.*;
import java.io.*;

class TestInput
{
  public static void main (String[] args){
  	// rehad input parameters
	// setup dataFile or dataRandom
        Scanner fileOrRandom = new Scanner(System.in);
        int decision = fileOrRandom.nextInt();
        Scanner dataFile;
        switch(decision) {
          case 0:
            System.out.println("Getting Random Data");
            break;
          case 1:
            Scanner nameOfFile = new Scanner(System.in);
            String fileName = nameOfFile.nextLine().trim();
        
            try{
              File file = new File(fileName);
              dataFile = new Scanner(file);
              System.out.println(dataFile.nextLine());
            }
            catch (FileNotFoundException e ) {
              e.printStackTrace();
            }

          default:
            break;
        }
	// add statements

  }
}




