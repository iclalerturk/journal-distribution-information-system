package iclalErturk_21011037;

import java.io.Serializable;

public class DateInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startMonth;
	private int endMonth;
	private int startYear;
	public DateInfo(int startMonth, int startYear) {
		this.startMonth = startMonth;
		if((startMonth + 11) % 12!=0) {
			this.endMonth = (startMonth + 11) % 12; //+1?
		}
		else {
			this.endMonth =12;
		}
		this.startYear = startYear;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	
	
}
