package com.hiber;

import javax.persistence.Entity;

@Entity
public class Cricketer extends Player{
	private int crun;
	private String ctype;
	
	public Cricketer() {
		
	}

	public Cricketer(int pid,String pname,int crun, String ctype) {
		super(pid,pname);
		this.crun = crun;
		this.ctype = ctype;
	}
	

	public void setCrun(int crun) {
		this.crun = crun;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	

	public int getCrun() {
		return crun;
	}
	public String getCtype() {
		return ctype;
	}

	@Override
	public String toString() {
		return "Cricketer [crun=" + crun + ", ctype=" + ctype + "]";
	}
	
}
