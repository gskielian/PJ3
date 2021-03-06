package PJ3;

import java.util.*;

//--------------------------------------------------------------------------
//
// Define simulation queues in a service area. Queues hold references to Customer
// and Teller objects
//
// Customer (FIFO) queue is used to hold waiting customers. If the queue is too long
// (i.e. >  customerQLimit), customer goes away without entering customer queue
//
// There are several tellers in a service area. Use PriorityQueue to
// hold BUSY tellers and FIFO queue to hold FREE tellers,
// i.e. a teller that is FREE for the longest time should start be used first.
//
// To handle teller in PriorityQueue, we need to define comparator
// for comparing 2 teller objects. Here is a constructor from Java API:
//
// 	PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
//
// For priority queue, the default compare function is "natural ordering"
// i.e. for numbers, minimum value is returned first
//
// User can define own comparator class for PriorityQueue.
// For teller objects, we like to have smallest end busy interval time first.
//
// The following class define compare() for two tellers :

class CompareTeller implements Comparator<Teller>{
	// overide compare() method
 	public int compare(Teller o1, Teller o2) {
		return o1.getEndBusyIntervalTime() - o2.getEndBusyIntervalTime();
	}
}

// DO NOT ADD NEW METHODS OR DATA FIELDS
class ServiceArea {


  // Private data fields:

  // define one priority queue
  private PriorityQueue <Teller> busyTellerQ;

  // define two FIFO queues
  private ArrayDeque<Customer> customerQ;
  private ArrayDeque<Teller> freeTellerQ;

  // define customer queue limit
  private int customerQLimit;


  // Constructor
  public ServiceArea()
  {

  }

  // Constructor
  public ServiceArea(int numTellers, int customerQlimit, int startTellerID)
  {
	// use ArrayDeque to construct FIFO queue objects
        this.customerQLimit = customerQlimit;

        customerQ = new ArrayDeque<Customer>(customerQLimit);
        freeTellerQ = new ArrayDeque<Teller>(numTellers);

        // Construct Teller objects and insert into FreeTellerQ
        int i;
        for ( i = 0; i < numTellers;  i++) {
          freeTellerQ.add(new Teller(startTellerID + i));
        }
	// construct PriorityQueue object
        // override compare() in Comparator to compare Teller objects
	busyTellerQ = new PriorityQueue<Teller>( numTellers, new CompareTeller());
        // initialize customerQLimit
	// add statements
  }

  public Teller removeFreeTellerQ()
  {
	// remove and return a free teller
        return freeTellerQ.pollFirst(); // removes teller from front and returns said teller
  }

  public Teller removeBusyTellerQ()
  {
	// remove and return a busy teller
	return busyTellerQ.poll(); //this returns a busyteller or null if nothing is there
  }

  public Customer removeCustomerQ()
  {
	// remove and return a customer
	return customerQ.pollFirst(); // first come first out-of-line
  }

  public void insertFreeTellerQ(Teller teller)
  {
	// insert a free teller
        freeTellerQ.addLast(teller);
  }

  public void insertBusyTellerQ(Teller teller)
  {
	// insert a busy teller
        busyTellerQ.add(teller);
  }

  public void insertCustomerQ(Customer customer)
  {
	// insert a customer
        customerQ.addLast(customer);
  }

  public boolean emptyFreeTellerQ()
  {
	// is freeTellerQ empty?
	return freeTellerQ.isEmpty();
  }

  public boolean emptyBusyTellerQ()
  {
	// is busyTellerQ empty?
	return busyTellerQ.isEmpty();
  }

  public boolean emptyCustomerQ()
  {
	// is customerQ empty?
	return customerQ.isEmpty();
  }

  public int numFreeTellers()
  {
	// get number of free tellers
	return freeTellerQ.size();
  }

  public int numBusyTellers()
  {
	// get number of busy tellers
	return busyTellerQ.size();
  }

  public int numWaitingCustomers()
  {
	// get number of customers
	return customerQ.size();
  }

  public Teller getFrontBusyTellerQ()
  {
	// get front of busy tellers
	// "retrieve" but not "remove"
	return busyTellerQ.peek();
  }

  public boolean isCustomerQTooLong()
  {
	// is customerQ too long?
	return (customerQ.size() == customerQLimit); //tests if line is maxed
  }

  public void printStatistics()
  {
  	System.out.println("\t# waiting customers : "+numWaitingCustomers());
  	System.out.println("\t# busy tellers      : "+numBusyTellers());
  	System.out.println("\t# free tellers      : "+numFreeTellers());
  }

  public static void main(String[] args) {

        // quick check

        ServiceArea sc = new ServiceArea(4, 5, 1001);
        Customer c1 = new Customer(1,18,10);
        Customer c2 = new Customer(2,33,10);
        Customer c3 = new Customer(3,21,10);
        Customer c4 = new Customer(3,37,10);
  	sc.insertCustomerQ(c1);
  	sc.insertCustomerQ(c2);
  	sc.insertCustomerQ(c3);
	System.out.println(""+sc.customerQ);
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("Remove customer:"+sc.removeCustomerQ());
	System.out.println("Remove customer:"+sc.removeCustomerQ());

	System.out.println(""+sc.freeTellerQ);
	Teller p1=sc.removeFreeTellerQ();
	Teller p2=sc.removeFreeTellerQ();
	Teller p3=sc.removeFreeTellerQ();
	Teller p4=sc.removeFreeTellerQ();
	System.out.println("Remove free teller:"+p1);
	System.out.println("Remove free teller:"+p2);
	System.out.println("Remove free teller:"+p3);
	System.out.println("Remove free teller:"+p4);

        p1.freeToBusy (c1, 13);
        p2.freeToBusy (c2, 13);
        p3.freeToBusy (c3, 13);
        p4.freeToBusy (c4, 13);
	sc.insertBusyTellerQ(p1);
	sc.insertBusyTellerQ(p2);
	sc.insertBusyTellerQ(p3);
	sc.insertBusyTellerQ(p4);
	System.out.println(""+sc.busyTellerQ);
	p1=sc.removeBusyTellerQ();
	p2=sc.removeBusyTellerQ();
	p3=sc.removeBusyTellerQ();
	p4=sc.removeBusyTellerQ();
	System.out.println("Remove busy teller:"+p1);
	System.out.println("Remove busy teller:"+p2);
	System.out.println("Remove busy teller:"+p3);
	System.out.println("Remove busy teller:"+p4);

   }


};

