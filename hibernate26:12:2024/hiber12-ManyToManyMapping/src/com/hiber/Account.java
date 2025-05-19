package com.hiber;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@Column(name="an")
	private int an;
	@Column(name="aname")
	private String aname;
	@Column(name="address")
	private String address;
	
	@ManyToMany
	@JoinTable(
			name="playeraccount",
			joinColumns=@JoinColumn(name="an"),
			inverseJoinColumns=@JoinColumn(name="pid")
			)
	private List<Player> players;
	
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


	public List<Player> getPlayers() {
		return players;
	}


	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	

	
	
	
}
