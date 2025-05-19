package com.hiber;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;


public class Launch {
	public static void main(String[] args) {
		
		SessionFactory sf=getConnection();
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			System.out.println("P1->Insert\nP2->Read\nP3->Update\nP4->Delete\nP5->Test\nP6->Exit");
			System.out.println("Enter choice: ");
			int choice=scan.nextInt();
			scan.nextLine();
			if(choice==6) {
				System.out.println("Thanks for Using");
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
				test(sf);
				break;
			default:
				System.out.println("Invalid Choice\n");
			}
		}
		
	}
	
	
	
	private static void test(SessionFactory sf) {
		Session session = sf.openSession();
		
		Criteria criteria=session.createCriteria(Player.class);
		
		ProjectionList projectionList=Projections.projectionList();
		projectionList.add(Projections.property("pid"));
		projectionList.add(Projections.property("pname"));
		
		SimpleExpression lt = Restrictions.lt("pid", 4);
		criteria.setProjection(projectionList);
		criteria.add(lt);
		
		List<Object[]> list = criteria.list();
		for(Object orr[]:list) {
			for(Object o:orr) {
				System.out.println(o);
			}
		}
	
	}
	
	private static void delete(SessionFactory sf) {
		Session session = sf.openSession();
		Transaction t=session.beginTransaction();
		
		//delete criteriaplayer set name='kaju' where id<5
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<Player> criteriaDelete = criteriaBuilder.createCriteriaDelete(Player.class);
		Root<Player> root=criteriaDelete.from(Player.class);
		
		criteriaDelete.where(criteriaBuilder.greaterThan(root.get("pid"),5));
		
		Query<Player> query = session.createQuery(criteriaDelete);
		query.executeUpdate();
		t.commit();
		
	}







	private static void update(SessionFactory sf) {
		Session session = sf.openSession();
		Transaction t=session.beginTransaction();
		
		//update criteriaplayer set name='kaju' where id<5
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaUpdate<Player> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Player.class);
		Root<Player> root=criteriaUpdate.from(Player.class);
		
		criteriaUpdate.set("pname", "kaju");
		criteriaUpdate.where(criteriaBuilder.lessThan(root.get("pid"),5));
		
		Query<Player> query = session.createQuery(criteriaUpdate);
		query.executeUpdate();
		t.commit();
	}







	private static void read(SessionFactory sf) {
		Session session = sf.openSession();
		
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Player> readCriteria = criteriaBuilder.createQuery(Player.class);
		
		Root<Player> root = readCriteria.from(Player.class);
		
		//sql conditional operation
		//select id,name from criteriaplayer where id<6 and name like '%2'
		readCriteria.multiselect(root.get("pid"),root.get("pname"));
		Predicate c1 = criteriaBuilder.lessThan(root.get("pid"), 6); //id<6
		Predicate c2 = criteriaBuilder.like(root.get("pname"), "%2"); //name like '%2'
		readCriteria.where(criteriaBuilder.and(c1,c2));
		
		
		
		Query<Player> query = session.createQuery(readCriteria);
		List<Player> list = query.list();
		System.out.println(list);
	}







	private static void insert(SessionFactory sf) {
		Session session = sf.openSession();
		
		for(int i=1;i<=10;i++) {
			Transaction t=session.beginTransaction();
			Player player=new Player("raju","CSK"+i);
			session.save(player);
			t.commit();
		}
		
		System.out.println("..........Inserted..........");
	}







	public static SessionFactory getConnection() {
		//Loads cfg file
		Configuration configuration=new Configuration();
		
		Properties properties=new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/criteriaAPI");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "rootsystem");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.HBM2DDL_AUTO, "update");//create->everytime delete & create new once, update->everytime update
		properties.put(Environment.SHOW_SQL, true);
		properties.put(Environment.FORMAT_SQL, true);
		
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Player.class);
		
		//setup Ready
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		
		return sessionFactory;
	}
	
}



