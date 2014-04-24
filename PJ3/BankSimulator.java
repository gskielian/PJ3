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
    numGoaway=0;
    numServed=0;
    totalWaitingTime=0;
  }

  private void setupParameters()
  {
    // rehad input parameters
    // setup dataFile or dataRandom

    Scanner userParameter = new Scanner(System.in);

    System.out.print("Enter simulation time (positive integer)       : ");
    simulationTime = userParameter.nextInt();

    System.out.print("Enter maximum transaction time of customers    : ");
    maxTransactionTime = userParameter.nextInt();

    System.out.print("enter chances (0% < & <= 100%) of new customer : ");
    chancesOfArrival = userParameter.nextInt();

    System.out.print("Enter the number of tellers                    : ");
    numTellers = userParameter.nextInt();

    System.out.print("Enter customer queue limit                     : ");
    customerQLimit = userParameter.nextInt();

    System.out.print("Enter 1/0 to get data from file/Random         : ");
    dataSource = userParameter.nextInt();

    String fileName = userParameter.nextLine(); // a hack to get rid of the lingering newline

    switch(dataSource) {
      case 0:
        System.out.println("Getting Random File Data");
        break;
      case 1:

        System.out.print("Enter filename                                 : ");

        fileName = userParameter.nextLine().trim(); // here the user will type the file name

        //in case the file ain't there, we catch the error
        try{
          File file = new File(fileName);
          dataFile = new Scanner(file);
        }
        catch (FileNotFoundException e ) {
          e.printStackTrace();
        }

        break;
      default:
        break;
    }



  }

  private void getCustomerData()
  {

    if (dataFile.hasNextLine()){
      String line = dataFile.nextLine();
      String[] parts = line.split(" ");
      transactionTime = Integer.parseInt(parts[0]);
      System.out.println(line); // just for debugging purposes
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

    // Initialize ServiceArea
    servicearea = new ServiceArea(numTellers, customerQLimit, 1001);


    // Time driver simulation loop
    for (int currentTime = 0; currentTime < simulationTime; currentTime++) {

      // Step 1: any new customer enters the bank?
      getCustomerData(); // sets anyNewArrival to true or false

      if (anyNewArrival) {

        // Step 1.1: setup customer data
        Customer customer = new Customer(customerIDCounter, transactionTime, currentTime); //customer id, transactionTime, currentTime
        customerIDCounter++; // increment the customerIDCounter

        // Step 1.2: check customer waiting queue too long?
        if (!servicearea.isCustomerQTooLong()) {
          servicearea.insertCustomerQ(customer);
        } else {
          System.out.println("\tLine is too long");
        }
      } else {
        System.out.println("\tNo new arrivals");
      }

      // Step 2: free busy tellers, add to free tellerQ
      
      if (servicearea.getFrontBusyTellerQ().getEndBusyIntervalTime() == currentTime) {
       servicearea.insertFreeTellerQ( servicearea.removeBusyTellerQ() );
        

      

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
    runBankSimulator.setupParameters(); // done
    runBankSimulator.doSimulation();
    runBankSimulator.printStatistics();
  }

}
