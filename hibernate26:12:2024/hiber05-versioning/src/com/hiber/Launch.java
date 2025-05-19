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
		try (Session session = sf.openSession()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Employee ID: ");
            int eid = scan.nextInt();
            scan.nextLine();
            
            Employee employee = session.get(Employee.class, eid);
            
            if(employee == null) {
                // Insert new employee
                System.out.print("Enter Name: ");
                String ename = scan.nextLine();
                
                System.out.print("Enter Address: ");
                String eaddress = scan.nextLine();
                
                System.out.print("Enter Salary: ");
                int esalary = scan.nextInt();
                scan.nextLine();
                
                employee = new Employee(eid, ename, eaddress, esalary);
            } else {
                // Update existing employee
                System.out.println("Current Details: " + employee);
                System.out.print("Enter New Name (leave blank to keep current): ");
                String ename = scan.nextLine();
                if(!ename.isEmpty()) employee.setEname(ename);
                
                System.out.print("Enter New Address (leave blank to keep current): ");
                String eaddress = scan.nextLine();
                if(!eaddress.isEmpty()) employee.setEaddress(eaddress);
                
                System.out.print("Enter New Salary (0 to keep current): ");
                int esalary = scan.nextInt();
                scan.nextLine();
                if(esalary > 0) employee.setEsalary(esalary);
            }
            
            Transaction t = session.beginTransaction();
            session.saveOrUpdate(employee);
            t.commit();
            System.out.println("Operation completed successfully. Version: " + employee.getVersion());
        }
	}

	private static void delete(SessionFactory sf) {
		try (Session session = sf.openSession()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Employee ID to delete: ");
            int eid = scan.nextInt();
            scan.nextLine();
            
            Employee employee = session.get(Employee.class, eid);
            if(employee != null) {
                System.out.println("Deleting: " + employee);
                Transaction t = session.beginTransaction();
                session.delete(employee);
                t.commit();
                System.out.println("Employee deleted successfully");
            } else {
                System.out.println("Employee not found");
            }
        }
	}


	private static void update(SessionFactory sf) {
		Session s = sf.openSession();
		Employee employee = s.get(Employee.class, 11);
		if(employee.getVersion()<=2) {
			System.out.println("Enter New Name: ");
			Scanner obj=new Scanner(System.in);
			String newnmae=obj.next();
			employee.setEname(newnmae);
			Transaction t=s.beginTransaction();
			s.update(employee);
			t.commit();
			System.out.println("........Updated..........");
		}else {
			System.out.println(".........Max Limit Reached.........");
		}
		
	}
	
	
	private static void read(SessionFactory sf) {
		try(Session session = sf.openSession()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Employee ID to view: ");
            int eid = scan.nextInt();
            scan.nextLine();
            
            Employee employee = session.get(Employee.class, eid);
            if(employee != null) {
                System.out.println("\nEmployee Details:");
                System.out.println("ID: " + employee.getEid());
                System.out.println("Name: " + employee.getEname());
                System.out.println("Address: " + employee.getEaddress());
                System.out.println("Salary: " + employee.getEsalary());
                System.out.println("Version: " + employee.getVersion());
            } else {
                System.out.println("Employee not found");
            }
        }
	}


	public static void insert(SessionFactory sf) {
		Session session=sf.openSession();
		Transaction t=session.beginTransaction();
		
		session.save(new Employee(11,"raju","csk",1000));
		t.commit();
		System.out.println(".........Inserted.........");
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



