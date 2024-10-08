package iclalErturk_21011037;

import java.io.Serializable;

public class Corporation extends Subscriber implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bankCode;
	private String bankName;
	private int issueDay;
	private int issueMonth;
	private int issueYear;
	private int accountNumber;
	public Corporation(String name, String address, int bankCode, String bankName, int issueDay, int issueMonth,
			int issueYear, int accountNumber) {
		super(name, address);
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.issueDay = issueDay;
		this.issueMonth = issueMonth;
		this.issueYear = issueYear;
		this.accountNumber = accountNumber;
	}
	@Override
	public String getBillingInformation() {// bura yazılacak fatura bilgileri
		// TODO Auto-generated method stub
		return "Corporation [bankCode=" + bankCode + ", bankName=" + bankName + ", issueDay=" + issueDay
				+ ", issueMonth=" + issueMonth + ", issueYear=" + issueYear + ", accountNumber=" + accountNumber
				+ ", getName()=" + getName() + ", getAddress()=" + getAddress() + "]";
	}
	
	//setterları kaldırdım
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getIssueDay() {
		return issueDay;
	}
	public int getIssueMonth() {
		return issueMonth;
	}
	public int getIssueYear() {
		return issueYear;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
}
