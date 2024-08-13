package iclalErturk_21011037;

import java.io.Serializable;

public class Individual extends Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String creditCardNr;
	private int expireMonth;
	private int expireYear;
	private int CCV;
	public Individual(String name, String address, String creditCardNr, int expireMonth, int expireYear, int cCV) {
		super(name, address);
		this.creditCardNr = creditCardNr;
		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
		CCV = cCV;
	}
	@Override
	public String getBillingInformation() {
		// TODO Auto-generated method stub
		return "Individual [creditCardNr=" + creditCardNr + ", expireMonth=" + expireMonth + ", expireYear="
				+ expireYear + ", CCV=" + CCV + ", getName()=" + getName() + ", getAddress()=" + getAddress() + "]";
	}
	
	//setterları kaldırdım değiştirilemez bunlar
	public String getCreditCardNr() {
		return creditCardNr;
	}

	public int getExpireMonth() {
		return expireMonth;
	}
	public int getExpireYear() {
		return expireYear;
	}
	public int getCCV() {
		return CCV;
	}
	
	
}
