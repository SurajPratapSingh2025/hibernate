package com.hiber;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
	@Id
	@Column(name="pid")
	private int pid;
	@Column(name="pname")
	private String pname;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="adharno")
	private Adhar adhar;
	
	public Player() {
		
	}
	
	public Player(int pid, String pname, Adhar adhar) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.adhar = adhar;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setAdhar(Adhar adhar) {
		this.adhar = adhar;
	}
	
	public int getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public Adhar getAdhar() {
		return adhar;
	}

	@Override
	public String toString() {
		return "Player [pid=" + pid + ", pname=" + pname + ", adhar=" + adhar + "]";
	}
	

	
	
}


