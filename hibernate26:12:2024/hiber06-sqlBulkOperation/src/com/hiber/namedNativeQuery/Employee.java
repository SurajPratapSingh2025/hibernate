package com.hiber.namedNativeQuery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@NamedNativeQuery(name="insert",query="insert into employee(eid,ename,eaddress,esalary) values(:eid,:ename,:eaddress,:esalary)")
@NamedNativeQuery(name="read", query="select * from employee")
@NamedNativeQuery(name="update", query="UPDATE employee SET ename = :ename, eaddress = :eaddress, esalary = :esalary WHERE eid = :eid")
@NamedNativeQuery(name="delete", query="DELETE FROM employee WHERE eid = :eid")
@NamedNativeQuery(name="checkExistence", query="SELECT * FROM employee WHERE eid = :eid", resultClass = Employee.class)
public class Employee {
	@Id
	private int eid;
	private String ename;
	private String eaddress;
	private int esalary;

	
	public Employee() {
//		System.out.println("Employee- Zero param constructor");
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
