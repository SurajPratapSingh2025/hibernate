package com.hiber;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity Class(in hibernate) but it is also refereed as POJO/DTO class

@Entity
@Table(name="mydata")
public class Employee {
	
	@Id
	@Column(name="eid")
	private int eid;
	@Column(name="ename", length=30, unique=true, nullable=false, columnDefinition="DATE")
	private String ename;
	@Column(name="eaddress", length=500)
	private String eaddress;
	@Column(name="esalary")
	private int esalary;
	
	public Employee() {
		System.out.println("******************");
	}
	
	
	public Employee(int eid, String ename, String eaddress, int esalary) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.eaddress = eaddress;
		this.esalary = esalary;
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


	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", eaddress=" + eaddress + ", esalary=" + esalary + "]";
	}
	
}
