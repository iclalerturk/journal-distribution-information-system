package iclalErturk_21011037;

import java.io.Serializable;

public abstract class Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private  String address;
	private Subscription subscription;
	public Subscriber(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public abstract String getBillingInformation();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
}
