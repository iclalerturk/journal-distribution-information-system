package iclalErturk_21011037;

import java.io.Serializable;
import java.util.Vector;

public class Journal implements Serializable{
	@Override
	public String toString() {
		return "Journal [name=" + name + ", issn=" + issn + ", frequency=" + frequency + ", issuePrice=" + issuePrice
				+ "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String issn;
	private int frequency;
	private double issuePrice;
	private Vector<Subscription> subscriptions;// bunu ben ekledim
	public Journal(String name, String issn, int frequency, double issuePrice) {
		this.name = name;
		this.issn = issn;
		this.frequency = frequency;
		this.issuePrice = issuePrice;
		subscriptions= new Vector<>();	
	}
	
	
	public Vector<Subscription> getSubscriptions() {
		return subscriptions;
	}


	public void setSubscriptions(Vector<Subscription> subsriptions) {
		this.subscriptions = subsriptions;
	}

	public void addSubscription(Subscription s) {
		int copy;
		if(s.getSubscriber()!=null && s.getJournal()!=null) {
			subscriptions.add(s);
			copy= s.getCopies()+1;
			s.setCopies(copy);
		}
		else {
			System.out.println("abonelik olusturulamaz");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIssn() {
		return issn;
	}
	public int getFrequency() {
		return frequency;
	}

	public double getIssuePrice() {
		return issuePrice;
	}

	public void setIssuePrice(double issuePrice) {
		this.issuePrice = issuePrice;
	}
	
	
}
