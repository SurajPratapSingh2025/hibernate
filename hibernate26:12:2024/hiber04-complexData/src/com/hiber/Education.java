package com.hiber;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Education {
	@Column(name="myrollno", unique=true, nullable=false)
	private int rollNo;
	@Column(name="myclgname")
	private String clgName;
	@Column(name="mypercentage")
	private int percentage;
	
	public Education() {
		System.out.println("****Education- Zero Param Constructor*****");

	}
	
	public Education(int rollNo, String clgName, int percentage) {
		super();
		this.rollNo = rollNo;
		this.clgName = clgName;
		this.percentage = percentage;
	}


	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public void setClgName(String clgName) {
		this.clgName = clgName;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}


	public int getRollNo() {
		return rollNo;
	}
	public String getClgName() {
		return clgName;
	}
	public int getPercentage() {
		return percentage;
	}
	
	
	
}
