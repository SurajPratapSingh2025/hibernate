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
			System.out.println("P1->Insert\nP2->ReadFromPlayer\nP3->ReadFromAdhar\nP4->Delete\nP5->Exit");
			System.out.println("Enter choice: ");
			int choice=scan.nextInt();
			scan.nextLine();
			if(choice==5) {
				System.out.println("Thanks for Using");
				break;
			}
			switch(choice) {
			case 1:
				insert(sf);
				break;
			case 2:
				readFromPlayer(sf);
				break;
			case 3:
				readFromAdhar(sf);
				break;
//			case 4:
//				delete(sf);
//				break;
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
	}
	
	
	
	
	
	
	private static void readFromAdhar(SessionFactory sf) {
		Session session = sf.openSession();
		Adhar adhar = session.get(Adhar.class, 12121);
		
		System.out.println(adhar.getAdhaNo());
		System.out.println(adhar.getAdharName());
		System.out.println(adhar.getPlayer().getPid());
		System.out.println(adhar.getPlayer().getPname());
		
		
	}






	private static void readFromPlayer(SessionFactory sf) {
		Session session = sf.openSession();
		Player player = session.get(Player.class, 13);
		System.out.println(player);
		
		
	}






	private static void insert(SessionFactory sf) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Adhar adhar=new Adhar(12121,"xyzoo");
		Player p=new Player(13,"xyz",adhar);
		adhar.setPlayer(p);
		
		session.save(p);
		t.commit();
		System.out.println("......Inserted......");
	}





	public static SessionFactory getConnection() {
		//Loads cfg file
		Configuration configuration=new Configuration();
		
		Properties properties=new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mapping");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "rootsystem");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.HBM2DDL_AUTO, "update");//create->everytime delete & create new once, update->everytime update
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Adhar.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		return sessionFactory;
	}
	
}



