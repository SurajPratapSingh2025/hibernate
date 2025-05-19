package com.hiber;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="adhar")
public class Adhar {
	@Id
	@Column(name="adharno")
	private int adhaNo;
	@Column(name="adharname")
	private String adharName;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pid")
	private Player player;
	
	public Adhar() {
		
	}
	public Adhar(int adhaNo, String adharName) {
		super();
		this.adhaNo = adhaNo;
		this.adharName = adharName;
	}
	public void setAdhaNo(int adhaNo) {
		this.adhaNo = adhaNo;
	}
	public void setAdharName(String adharName) {
		this.adharName = adharName;
	}
	public int getAdhaNo() {
		return adhaNo;
	}
	public String getAdharName() {
		return adharName;
	}
	
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
//	@Override
//	public String toString() {
//		return "Adhar [adhaNo=" + adhaNo + ", adharName=" + adharName + "]";
//	}
	
	
}
