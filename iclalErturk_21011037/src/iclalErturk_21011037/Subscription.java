package iclalErturk_21011037;

import java.io.Serializable;

public class Subscription implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DateInfo dates;
	private PaymentInfo payment;
	private int copies;
	private final Journal journal;
	private final Subscriber subscriber;
		
	public Subscription(DateInfo dates, int copies, Journal journal, Subscriber subscriber) {
		this.dates = dates;
		copies=1;
		this.copies = copies;
		this.journal = journal;
		this.subscriber = subscriber;
		this.payment = new PaymentInfo(0);
		
	}
	public void acceptPayment(double amount) {
		if(payment!=null) {
			payment.increasePayment(amount);
		}
		
	}

	/*
	 * public int getCurrentYear() { return LocalDate.now().getYear(); } public int
	 * getMonthValue() { return LocalDate.now().getMonthValue(); }
	 */
	public boolean canSend(int issueMonth) {

		int aylar[] = new int[journal.getFrequency()];
		for(int i=0;i<journal.getFrequency();i++) {
			aylar[i]=(12/journal.getFrequency())*i+1;
			if(aylar[i]==issueMonth) {
				if(payment.getReceivedPayment()>=journal.getIssuePrice()*copies) {
					return true;
				}			
			}
		}
		return false;
		
	}
	public PaymentInfo getPayment() {
		return payment;
	}
	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public DateInfo getDates() {
		return dates;
	}
	public Journal getJournal() {
		return journal;
	}
	public Subscriber getSubscriber() {
		return subscriber;
	}

	
	
}
