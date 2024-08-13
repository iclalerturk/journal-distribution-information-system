package iclalErturk_21011037;

import java.io.Serializable;

public class PaymentInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double discountRatio;
	private double receivedPayment;
	public PaymentInfo(double discountRatio) {
		discountRatio=0.1;
		this.discountRatio = discountRatio;
		receivedPayment = 0;
	}
	public void increasePayment(double amount) {
		receivedPayment+=amount;
	}
	public double getReceivedPayment() {
		return receivedPayment;
	}
	public void setRecievedPayment(double recievedPayment) {
		this.receivedPayment = recievedPayment;
	}
	public double getDiscountRatio() {
		return discountRatio;
	}
	
	
}
