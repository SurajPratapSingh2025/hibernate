package com.hiber;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


public class Launch {
	public static void main(String[] args) {
		
		SessionFactory sf=getConnection();
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("P1->Insert\nP2->ReadSQL\nP3->ReadHQL\nP4->Delete\nP5->Exit");
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
				readSQL(sf);
				break;
			case 3:
				readHQL(sf);
				break;
//			case 4:
//				delete(sf);
//				break;
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
	}
	
	
	
	
	
	private static void readHQL(SessionFactory sf) {
		Session session=sf.openSession();
		String hql="from Player";
		
		Query query = session.createQuery(hql);
		List<Player> list = query.list();
		System.out.println(list);
		
		for(Player p:list) {
			if(p instanceof Cricketer) {
				Cricketer c=(Cricketer)p;
				System.out.println(c);
			}else {
				Footballer f=(Footballer)p;
				System.out.println(f);
			}
		}
		
		
	}





	private static void readSQL(SessionFactory sf) {
		Session session = sf.openSession();
		String sql1="select p.pid,p.pname,c.crun,c.ctype from player p join cricketer c on p.pid=c.pid "
				+ "UNION ALL "
				+ "select p.pid,p.pname,f.fgoal,f.ftype from player p join footballer f on p.pid=f.pid";
		
		NativeQuery nativeQuery = session.createNativeQuery(sql1);
		List<Object[]> list = nativeQuery.getResultList();
		
		for(Object[] arr : list) {
			for(Object obj:arr) {
				System.out.println(obj+" ");
			}
			System.out.println();
		}
		
	}





	private static void insert(SessionFactory sf) {
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		Cricketer cr=new Cricketer(7,"dhoni",1000,"wk");
		Footballer fb=new Footballer(11,"ronaldo",80,"attack");
		
		session.save(cr);
		session.save(fb);
		
		t.commit();
		session.close();

	    System.out.println("Cricketer and Footballer inserted successfully.");
	}





	public static SessionFactory getConnection() {
		//Loads cfg file
		Configuration configuration=new Configuration();
		
		Properties properties=new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hqldata");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "rootsystem");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.HBM2DDL_AUTO, "update");//create->everytime delete & create new once, update->everytime update
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Player.class);
		configuration.addAnnotatedClass(Cricketer.class);
		configuration.addAnnotatedClass(Footballer.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		return sessionFactory;
	}
	
}



