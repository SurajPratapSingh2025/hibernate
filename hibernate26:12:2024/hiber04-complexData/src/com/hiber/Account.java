package com.hiber;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Account {
	@Column(name="myaccno", unique=true, nullable=false)
	private int accNo;
	@Column(name="mybankname", nullable=false)
	private String bankName;
	@Column(name="myamount", nullable=false)
	private int amount;
	
	
	public Account() {
		System.out.println("****Account- Zero Param Constructor*****");
	}
	
	public Account(int accNo, String bankName, int amount) {
		super();
		this.accNo = accNo;
		this.bankName = bankName;
		this.amount = amount;
	}


	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getAccNo() {
		return accNo;
	}
	public String getBankName() {
		return bankName;
	}
	public int getAmount() {
		return amount;
	}
	
	
}
