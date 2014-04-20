// DO NOT ADD NEW METHODS OR DATA FIELDS!

package PJ3;

class Customer
{
  private int customerID;
  private int transactionTime;
  private int arrivalTime;

  Customer()
  {
    customerID = 0; // might want to set these to -1 or something later or do a try/catch
    transactionTime = 0; //by default, customer was wasting time
    arrivalTime = 0;
  }

  Customer(int customerid, int transactionduration, int arrivaltime)
  {
    //sets up the time in which they will need to talk with the teller
    customerID = customerid;
    transactionTime = transactionduration;
    arrivalTime = arrivaltime;
  }

  int getTransactionTime()
  {
    return transactionTime;
  }

  int getArrivalTime()
  {
    return arrivalTime;
  }

  int getCustomerID()
  {
    return customerID;
  }

  public String toString()
  {
    return ""+customerID+":"+transactionTime+":"+arrivalTime;

  }

  public static void main(String[] args) {
    // quick check!
    Customer mycustomer = new Customer(20,30,40);
    System.out.println("Customer Info:"+mycustomer);

  }
}
