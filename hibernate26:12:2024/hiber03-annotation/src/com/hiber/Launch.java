package com.hiber;

import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Launch {
	public static void main(String[] args) {
		
		SessionFactory sf=getConnection();
		System.out.println(sf.openSession());
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("P1->Insert\nP2->Read\nP3->Update\nP4->Delete\nP5->InsertOrUpdate\nP6->Exit");
			System.out.println("Enter choice: ");
			int choice=scan.nextInt();
			scan.nextLine();
			if(choice==6) {
				System.out.println("Thanks For Using");
				break;
			}
			switch(choice) {
			case 1:
				insert(sf);
				break;
			case 2:
				read(sf);
				break;
			case 3:
				update(sf);
				break;
			case 4:
				delete(sf);
				break;
			case 5:
				insertOrUpdate(sf);
				break;
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
//		insert(session);
//		read(session);
//		update(sf);
		
	}
	
	



	private static void insertOrUpdate(SessionFactory sf) {
		Session session = sf.openSession();
		// transaction
		Transaction trs = session.beginTransaction();

		// data insert
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid = scan.nextInt();
		System.out.println("Enter eName: ");
		String ename = scan.next();
		System.out.println("Enter eAddress: ");
		String eaddress = scan.next();
		System.out.println("Enter eSalary: ");
		int esalary = scan.nextInt();

		Employee employee = new Employee(eid, ename, eaddress, esalary);
		
		session.saveOrUpdate(employee);
		trs.commit();
		System.out.println("........Data insertOrUpdate.......");
	}





	private static void delete(SessionFactory sf) {
		Session session = sf.openSession();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid=scan.nextInt();
		
		Employee employee=session.get(Employee.class, eid);
		if(employee!=null) {
			Transaction trs=session.beginTransaction();
			session.delete(employee);
			trs.commit();
			System.out.println("......Data Deleted......");
		}
		
	}





	private static void update(SessionFactory sf) {
		Session session = sf.openSession();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid=scan.nextInt();
		
		System.out.println("Enter New Salary: ");
		int newsalary=scan.nextInt();
		
		Employee employee=session.get(Employee.class, eid);
		if(employee!=null) {
			
			employee.setEsalary(newsalary);
			Transaction trs=session.beginTransaction();
			session.update(employee);
			trs.commit();
			System.out.println(".......UPDATED.......");
		}else {
			System.out.println("Not Exist");
		}
	}
	
	
	private static void read(SessionFactory sf) {
		Session session = sf.openSession();
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid=scan.nextInt();
		
		Employee employee = session.get(Employee.class, eid);
		if(employee!=null) {
			System.out.println(employee);
		}else {
			System.out.println("Not Exist");
		}
		
		//first level cache
//		employee=session.get(Employee.class, eid);
//		System.out.println(employee);
		
		sf.close();
	}


	public static void insert(SessionFactory sf) {
		Session session = sf.openSession();
		// transaction
		Transaction trs = session.beginTransaction();

		// data insert
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid = scan.nextInt();
		System.out.println("Enter eName: ");
		String ename = scan.next();
		System.out.println("Enter eAddress: ");
		String eaddress = scan.next();
		System.out.println("Enter eSalary: ");
		int esalary = scan.nextInt();

		Employee employee = new Employee(eid, ename, eaddress, esalary);

		int pk = (int) session.save(employee);
		trs.commit();
		System.out.println(pk);
		sf.close();
	}
	
	
	public static SessionFactory getConnection() {
		//Loads cfg file
		Configuration configuration=new Configuration();
		
		Properties properties=new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/testraju");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "rootsystem");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.HBM2DDL_AUTO, "update");
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Employee.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
				
		
		return sessionFactory;
	}
	
}



