package com.hiber;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@MappedSuperclass
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Player {
	@Id
	private int pid;
	private String pname;
	
	public Player() {
		
	}

	public Player(int pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}
	
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	public int getPid() {
		return pid;
	}
	public String getPname() {
		return pname;
	}
	
	
	
	
}


