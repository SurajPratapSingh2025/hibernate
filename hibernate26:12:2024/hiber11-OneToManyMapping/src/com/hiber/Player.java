package com.hiber;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
	@Id
	@Column(name="pid")
	private int pid;
	@Column(name="pname")
	private String pname;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="playeraccount",
			joinColumns=@JoinColumn(name="pid"),
			inverseJoinColumns=@JoinColumn(name="an")
			)
	
	private List<Account> accounts;
	
	public Player() {
		
	}
	
	public Player(int pid, String pname, List<Account> accounts) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.accounts = accounts;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public String toString() {
		return "Player [pid=" + pid + ", pname=" + pname + ", accounts=" + accounts + "]";
	}
	

	
	
}


