package iclalErturk_21011037;

import java.util.Hashtable;
import java.util.Vector;

import junit.framework.*;

public class TestAll extends TestCase {
	//DateInfo sınıfına ait testler
	public void testGetStartMonth() {     
        DateInfo date = new DateInfo(1,2023);
        assertEquals(date.getStartMonth(), 1);
    }
	public void testGetStartYear() {     
        DateInfo date = new DateInfo(1,2023);
        assertEquals(date.getStartYear(), 2023);
    }
	public void testGetEndMonth() {     
        DateInfo date = new DateInfo(1,2023);
        assertEquals(date.getEndMonth(), 12);
    }
	//PaymentInfo sınıfına ait testler
	public void testIncreasePayment() {     
        PaymentInfo payment = new PaymentInfo(0.1);
        double receivedPayment=payment.getReceivedPayment();
        double amount=20;
        receivedPayment+=amount;
        payment.increasePayment(amount);
        assertEquals(payment.getReceivedPayment(), receivedPayment);
    }
	
	public void testGetReceivedPayment() {     
	    PaymentInfo payment = new PaymentInfo(0.1);    
	    assertEquals(payment.getReceivedPayment(), 0.0);	    
	}
	//Subscription sınıfına ait testler
	public void testAcceptPayment() {
		DateInfo date = new DateInfo(1,2023);
		Journal journal = new Journal("Kafa Okur","1120-2240",1,35);
		Subscriber subscriber1 = new Corporation("Corp 1", "Address 1", 1234, "Bank 1", 1, 2024, 123, 5678);
		Subscription stion = new Subscription(date,1,journal,subscriber1);
		stion.acceptPayment(20.0);		
		assertEquals(stion.getPayment().getReceivedPayment(), 20.0);	 
	}
	public void testCanSend() {
		DateInfo date = new DateInfo(1,2023);
		Journal journal = new Journal("Kafa Okur","1120-2240",1,35);
		Subscriber subscriber1 = new Corporation("Corp 1", "Address 1", 1234, "Bank 1", 1, 2024, 123, 5678);
		Subscription stion = new Subscription(date,1,journal,subscriber1);
		stion.acceptPayment(35.0);		
		assertTrue(stion.canSend(1));
		assertFalse(stion.canSend(3));
	}
	//journal sınıfına ait testler
	public void testaddSubscriptionjournal() {
		DateInfo date = new DateInfo(1,2023);
		Journal journal = new Journal("Kafa Okur","1120-2240",1,35);
		Subscriber subscriber1 = new Corporation("Corp 1", "Address 1", 1234, "Bank 1", 1, 2024, 123, 5678);
		Subscription stion = new Subscription(date,1,journal,subscriber1);
		
		journal.addSubscription(stion);
		assertTrue(journal.getSubscriptions().contains(stion));
	}
	 public void testGetNameJournal() {
		 String name = "dergi";
	     Journal journal = new Journal(name, "1120", 1, 50.0);	     
	     assertEquals(journal.getName(), name);
	 }
	//individual sınıfına ait testler
	 public void testGetCredisCardNr() {
		 String number = "1234 5678";
	     Subscriber s = new Individual("Individual 1", "Address 2", number, 12, 2023, 789);
	     assertEquals(((Individual)s).getCreditCardNr(), number);
	 }
	 public void testGetCCV() {
		 int ccv = 789;
	     Subscriber s = new Individual("Individual 1", "Address 2", "1234 5678", 12, 2023, ccv);
	     assertEquals(((Individual)s).getCCV(), ccv);
	 }
	 //corporation sınıfına ait testler
	 public void testGetBankCode() {
		 int number = 1234;
		 Subscriber s = new Corporation("Corp 1", "Address 1", number, "Bank 1", 1, 2024, 123, 5678);
	     assertEquals(((Corporation)s).getBankCode(), number);
	 }
	 public void testGetBankName() {
		 String bankName = "Bank 1";
		 Subscriber s = new Corporation("Corp 1", "Address 1", 1234, bankName, 1, 2024, 123, 5678);
	     assertEquals(((Corporation)s).getBankName(), bankName);
	 }
	 //subscriber sınıfına ait testler
	 public void testGetNameSubscriber() {
		 	String name = "Individual 1";
	        Subscriber subscriber = new Individual(name, "Address 2", "1234 5678", 12, 2023, 789);
	        assertEquals(subscriber.getName(), name);
	 }
	 public void testGetAddress() {
		 	String address = "Address 1";
	        Subscriber subscriber = new Corporation("Corp 1", address , 1234, "Bank 1", 1, 2024, 123, 5678);
	        assertEquals(subscriber.getAddress(), address);
	 }
	//distributor sınıfına ait testler
	 public void testSearchJournal() {
	        Hashtable<String, Journal> journals = new Hashtable<>();
	        Journal journal = new Journal("Kafa Okur","1120-2240",1,35);
	        journals.put(journal.getIssn(), journal);
	        Distributor distributor = new Distributor(journals, new Vector<>());
	        Journal journal2 = distributor.searchJournal("1120-2240");
	        assertEquals(journal, journal2);
	 }
	 public void testAddSubscriber_WhenSubscriberDoesNotExist() {
	        Vector<Subscriber> subscribers = new Vector<>();
	        Distributor distributor = new Distributor(new Hashtable<>(), subscribers);
	        Subscriber subscriber = new Individual("Individual 1", "Address 2", "1234 5678 9012 3456", 12, 2023, 789);
	        assertTrue(distributor.addSubscriber(subscriber));
	    }
	 
}