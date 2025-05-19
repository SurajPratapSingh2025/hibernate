package com.hiber.nativeQuery;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;

import com.hiber.namedNativeQuery.Employee;

public class Launch {
	public static void main(String[] args) {
		
		SessionFactory sf=getConnection();
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
		
	}
	
	
	private static void insertOrUpdate(SessionFactory sf) {
	    Scanner obj = new Scanner(System.in);
	    System.out.println("Enter eId: ");
	    int eid = obj.nextInt();
	    obj.nextLine(); // clear buffer

	    Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();

	    // Check if employee exists
	    String checkSql = "SELECT * FROM employee WHERE eid = :eid";
	    NativeQuery<Employee> checkQuery = session.createNativeQuery(checkSql, Employee.class);
	    checkQuery.setParameter("eid", eid);
	    List<Employee> result = checkQuery.getResultList();

	    if (result.isEmpty()) {
	        // Insert new employee
	        System.out.println("Enter eName: ");
	        String ename = obj.nextLine();
	        System.out.println("Enter eAddress: ");
	        String eaddress = obj.nextLine();
	        System.out.println("Enter eSalary: ");
	        int esalary = obj.nextInt();

	        String insertSql = "INSERT INTO employee(eid, ename, eaddress, esalary) VALUES(:eid, :ename, :eaddress, :esalary)";
	        NativeQuery<?> insertQuery = session.createNativeQuery(insertSql);
	        insertQuery.setParameter("eid", eid);
	        insertQuery.setParameter("ename", ename);
	        insertQuery.setParameter("eaddress", eaddress);
	        insertQuery.setParameter("esalary", esalary);
	        insertQuery.executeUpdate();
	        System.out.println("Inserted new employee.");
	    } else {
	        // Update existing employee
	        System.out.println("Enter new eName: ");
	        String ename = obj.nextLine();
	        System.out.println("Enter new eAddress: ");
	        String eaddress = obj.nextLine();
	        System.out.println("Enter new eSalary: ");
	        int esalary = obj.nextInt();

	        String updateSql = "UPDATE employee SET ename = :ename, eaddress = :eaddress, esalary = :esalary WHERE eid = :eid";
	        NativeQuery<?> updateQuery = session.createNativeQuery(updateSql);
	        updateQuery.setParameter("ename", ename);
	        updateQuery.setParameter("eaddress", eaddress);
	        updateQuery.setParameter("esalary", esalary);
	        updateQuery.setParameter("eid", eid);
	        updateQuery.executeUpdate();
	        System.out.println("Updated existing employee.");
	    }

	    tx.commit();
	    session.close();
	}


	private static void delete(SessionFactory sf) {
	    Scanner obj = new Scanner(System.in);
	    System.out.println("Enter eId of employee to delete: ");
	    int eid = obj.nextInt();

	    Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();

	    String sql = "DELETE FROM employee WHERE eid = :eid";
	    NativeQuery<?> query = session.createNativeQuery(sql);
	    query.setParameter("eid", eid);

	    int rows = query.executeUpdate();
	    tx.commit();
	    session.close();

	    System.out.println("Rows deleted: " + rows);
	}



	private static void update(SessionFactory sf) {
	    Scanner obj = new Scanner(System.in);
	    System.out.println("Enter eId of employee to update: ");
	    int eid = obj.nextInt();
	    obj.nextLine(); // clear buffer

	    System.out.println("Enter new eName: ");
	    String ename = obj.nextLine();
	    System.out.println("Enter new eAddress: ");
	    String eaddress = obj.nextLine();
	    System.out.println("Enter new eSalary: ");
	    int esalary = obj.nextInt();

	    Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();

	    String sql = "UPDATE employee SET ename = :ename, eaddress = :eaddress, esalary = :esalary WHERE eid = :eid";
	    NativeQuery<?> query = session.createNativeQuery(sql);
	    query.setParameter("ename", ename);
	    query.setParameter("eaddress", eaddress);
	    query.setParameter("esalary", esalary);
	    query.setParameter("eid", eid);

	    int rows = query.executeUpdate();
	    tx.commit();
	    session.close();

	    System.out.println("Rows updated: " + rows);
	}

	
	
	private static void read(SessionFactory sf) {
		Session session=sf.openSession();
		String sql="select * from employee";
		NativeQuery nq=session.createNativeQuery(sql);
		nq.addEntity(Employee.class);
		List<Employee> list=nq.getResultList();
		System.out.println(list);
		
		for(Employee e : list) {
			System.out.println(e);
		}
		
		
//		for(Object[] orr : list) {
//			for(Object o:orr) {
//				System.out.println(o);
//			}
//		}
	}


	public static void insert(SessionFactory sf) {
		Scanner obj=new Scanner(System.in);
		System.out.println("Enter eId: ");
		int eid=obj.nextInt();
		System.out.println("Enter eName: ");
		String ename=obj.next();
		System.out.println("Enter eAddress: ");
		String eaddress=obj.next();
		System.out.println("Enter eSalary: ");
		int esalary=obj.nextInt();
		
		
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		
		String sql="insert into employee(eid,ename,eaddress,esalary) values(:eid,:ename,:eaddress,:esalary)";
		NativeQuery nq = session.createNativeQuery(sql);
		nq.setParameter("eid", eid);
		nq.setParameter("ename", ename);
		nq.setParameter("eaddress", eaddress);
		nq.setParameter("esalary",esalary);
		
		int row=nq.executeUpdate();
		t.commit();
		System.out.println(row);
	}
	
	
	public static SessionFactory getConnection() {
		//Loads cfg file
		Configuration configuration=new Configuration();
		
		Properties properties=new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/testraju");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "rootsystem");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.HBM2DDL_AUTO, "update");//create->everytime delete & create new once, update->everytime update
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Employee.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		return sessionFactory;
	}
	
}



