package com.hiber;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;

public class Launch {
	public static void main(String[] args) {
		
		SessionFactory sf=getConnection();
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("P1->Copy\nP2->Read\nP3->Update\nP4->Delete");
			System.out.println("Enter choice: ");
			int choice=scan.nextInt();
			scan.nextLine();
			if(choice==6) {
				System.out.println("Thanks For Using");
				break;
			}
			switch(choice) {
			case 1:
				copy(sf);
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
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
	}


	private static void delete(SessionFactory sf) {
		
	}


	private static void update(SessionFactory sf) {
		
	}
	
	
	private static void read(SessionFactory sf) {
		
		String hql="from Employee";
		Session session=sf.openSession();
		Query query = session.createQuery(hql);
		List <Employee>  list= query.list();
		for(Employee e : list) {
			System.out.println(e);
		}
		
	}


	public static void copy(SessionFactory sf) {
		String hql="insert into Employee(eid,ename,eaddress,esalary) select eid,ename,eaddress,esalary from CopyEmployee";
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		Query query = session.createQuery(hql);
		int executeUpdate=query.executeUpdate();
		System.out.println(executeUpdate);
		t.commit();
		
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
		configuration.addAnnotatedClass(CopyEmployee.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
				
		return sessionFactory;
	}
	
}



