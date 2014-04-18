// DO NOT ADD NEW METHODS OR DATA FIELDS!

package PJ3;

class Customer
{
    private int customerID;
    private int transactionTime;
    private int arrivalTime;

    Customer()
    {
        // add statements
    }

    Customer(int customerid, int transactionduration, int arrivaltime)
    {
        // add statements
    }

    int getTransactionTime() 
    {
        // add statements
  	return 0;
    }

    int getArrivalTime() 
    {
        // add statements
  	return 0;
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
