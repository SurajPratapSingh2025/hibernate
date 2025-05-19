package com.hiber;

import javax.persistence.Entity;

@Entity
public class Footballer extends Player{
	private int fgoal;
	private String ftype;
	public Footballer() {
		
	}
	public Footballer(int pid,String pname,int fgoal, String ftype) {
		super(pid,pname);
		this.fgoal = fgoal;
		this.ftype = ftype;
	}
	public void setFgoal(int fgoal) {
		this.fgoal = fgoal;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public int getFgoal() {
		return fgoal;
	}
	public String getFtype() {
		return ftype;
	}
	@Override
	public String toString() {
		return "Footballer [fgoal=" + fgoal + ", ftype=" + ftype + "]";
	}
	
	
}


