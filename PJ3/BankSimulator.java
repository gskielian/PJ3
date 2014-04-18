package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea
// to implement your simulator

class BankSimulator {

  // input parameters
  private int numTellers, customerQLimit;
  private int simulationTime, dataSource;
  private int chancesOfArrival, maxTransactionTime;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;

  // internal data
  private int customerIDCounter;
  private ServiceArea servicearea; // service area object
  private Scanner dataFile;	   // get customer data from file
  private Random dataRandom;	   // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int transactionTime;

  // initialize data fields
  private BankSimulator()
  {
	// add statements
  }

  private void setupParameters()
  {
  	// rehad input parameters
	// setup dataFile or dataRandom

    Scanner userParameter = new Scanner(System.in);

    System.out.println("Enter simulation time (positive integer)       : ");
    simulationTime = userParameter.nextInt();

    System.out.println("Enter maximum transaction time of customers    : ");
    maxTransactionTime = userParameter.nextInt();

    System.out.println("enter chances (0% < & <= 100%) of new customer : ");
    chancesOfArrival = userParameter.nextInt();

    System.out.println("Enter the number of tellers                    : ");
    numTellers = userParameter.nextInt();

    System.out.println("Enter customer queue limit                     : ");
    customerQLimit = userParameter.nextInt();

    System.out.println("Enter 1/0 to get data from file/Random         : ");
    dataSource = userParameter.nextInt();

    System.out.println("Enter filename                                 : ");
    
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

  private void getCustomerData()
  {
      if (dataFile.hasNextLine()){
        String line = dataFile.nextLine();
        String[] parts = String.split("-");
        transactionTime = parts[0];
        System.out.println(line);
        anyNewArrival = true;
      }
      else{
        anyNewArrival = false;
      }


      dataFile.close();
      
	// get next customer data : from file or random number generator
        // set anyNewArrival and transactionTime
	// add statements
  }

  private void doSimulation()
  {
	// add statements

	// Initialize ServiceArea

	// Time driver simulation loop
  	for (int currentTime = 0; currentTime < simulationTime; currentTime++) {

    		// Step 1: any new customer enters the bank?
    		getCustomerData();

    		if (anyNewArrival) {

      		    // Step 1.1: setup customer data
      		    // Step 1.2: check customer waiting queue too long?
    		} else {
      		    System.out.println("\tNo new customer!");
    		}

    		// Step 2: free busy tellers, add to free tellerQ

    		// Step 3: get free tellers to serve waiting customers 

  	} // end simulation loop

  	// clean-up
  }

  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in project statement
        // you need to display all free and busy tellers
  }

  // *** main method to run simulation ****

  public static void main(String[] args) {
   	BankSimulator runBankSimulator=new BankSimulator();
   	runBankSimulator.setupParameters();
   	runBankSimulator.doSimulation();
   	runBankSimulator.printStatistics();
  }

}
