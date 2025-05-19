package com.hiber;

import java.util.ArrayList;
import java.util.List;
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
			System.out.println("P1->Insert\nP2->ReadFromPlayer\nP3->ReadFromAccount\nP4->Delete\nP5->Exit");
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
				readFromAccount(sf);
				break;
//			case 4:
//				delete(sf);
//				break;
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
	}
	
	
	
	
	
	
	private static void readFromAccount(SessionFactory sf) {
		Session session = sf.openSession();
		Account account = session.get(Account.class, 1);
		System.out.println("............ACCOUNT DETAILS...........");
		System.out.println(account.getAn());
		System.out.println(account.getAname());
		System.out.println(account.getAddress());
	
		Player player=account.getPlayer();
		System.out.println("............PLAYER DETAILS...........");
		System.out.println(player.getPid());
		System.out.println(player.getPname());
		
		
	}






	private static void readFromPlayer(SessionFactory sf) {
		Session session = sf.openSession();
		Player player = session.get(Player.class, 11);
		
		System.out.println("............PLAYER DETAILS...........");
		System.out.println(player.getPid());
		System.out.println(player.getPname());
		
		List<Account> accounts = player.getAccounts();
		
		for(Account account:accounts) {
			System.out.println("............ACCOUNT DETAILS...........");
			System.out.println(account.getAn());
			System.out.println(account.getAname());
			System.out.println(account.getAddress());
		}
		
		
	}






	private static void insert(SessionFactory sf) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Account account1=new Account(1,"a","add1");
		Account account2=new Account(2,"b","add2");
		Account account3=new Account(3,"c","add3");
		
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
		
		Player player=new Player(11,"xyz",accountList);
		
		account1.setPlayer(player);
		account2.setPlayer(player);
		account3.setPlayer(player);
		
		session.save(player);
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
		properties.put(Environment.HBM2DDL_AUTO, "create");//create->everytime delete & create new once, update->everytime update
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Account.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		return sessionFactory;
	}
	
}



