package com.hiber;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Launch {
	public static void main(String[] args) {
		//Loads cfg file
		Configuration configuration=new Configuration();
		configuration.configure();
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		//Now we perform CRUD
		Session session=sessionFactory.openSession();
		
		//transaction
		Transaction trs = session.beginTransaction();
		
		
		//data insert
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid=scan.nextInt();
		System.out.println("Enter eName: ");
		String ename=scan.next();
		System.out.println("Enter eAddress: ");
		String eaddress=scan.next();
		System.out.println("Enter eSalary: ");
		int esalary=scan.nextInt();
		
		Employee employee = new Employee(eid,ename,eaddress,esalary);
		
		int pk=(int) session.save(employee);
		trs.commit();
		System.out.println(pk);
		session.close();
	}
}






