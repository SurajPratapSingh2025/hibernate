package com.hiber;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="adhar")
public class Account {
	@Id
	@Column(name="an")
	private int an;
	@Column(name="aname")
	private String aname;
	@Column(name="address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name="pid")
	private Player player;
	
	
	public Account() {
		
	}


	public Account(int an, String aname, String address) {
		super();
		this.an = an;
		this.aname = aname;
		this.address = address;
	}


	public void setAn(int an) {
		this.an = an;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public int getAn() {
		return an;
	}
	public String getAname() {
		return aname;
	}
	public String getAddress() {
		return address;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
