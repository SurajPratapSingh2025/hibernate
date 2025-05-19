package com.hiber;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mycomplexemployee")
public class Employee {
	@Id
	@Column(name="eid")
	private int eid;
	@Column(name="ename", length=30, unique=true)
	private String ename;
	@Column(name="eaddress", length=500)
	private String eaddress;
	@Column(name="esalary")
	private int esalary;
	
	@Embedded
	private Account account;
	@Embedded
	private Education education;
	
	public Employee() {
		System.out.println("****Employee- Zero Param Constructor*****");
	}
	
	
	public Employee(int eid, String ename, String eaddress, int esalary, Account account, Education education) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.eaddress = eaddress;
		this.esalary = esalary;
		this.account = account;
		this.education = education;
	}
	
	
	public void setEid(int eid) {
		this.eid = eid;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}
	public void setEsalary(int esalary) {
		this.esalary = esalary;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public void setEducation(Education education) {
		this.education = education;
	}


	public int getEid() {
		return eid;
	}
	public String getEname() {
		return ename;
	}
	public String getEaddress() {
		return eaddress;
	}
	public int getEsalary() {
		return esalary;
	}
	public Account getAccount() {
		return account;
	}
	public Education getEducation() {
		return education;
	}
	
	
	
}
