package com.hiber;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class CopyEmployee {
	@Id
	private int eid;
	private String ename;
	private String eaddress;
	private int esalary;
	
	public CopyEmployee() {
		
	}

	public CopyEmployee(int eid, String ename, String eaddress, int esalary) {
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
	
	
}
