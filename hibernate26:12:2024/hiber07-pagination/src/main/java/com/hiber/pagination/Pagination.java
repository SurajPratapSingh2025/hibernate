package com.hiber.pagination;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.NativeQuery;


public class Pagination {
	public List<Employee> readData(int start) {
		SessionFactory sf=getConnection();
		Session session=sf.openSession();
		NativeQuery namedNativeQuery = session.getNamedNativeQuery("read");
		namedNativeQuery.setFirstResult(start);
		namedNativeQuery.setMaxResults(4);
		namedNativeQuery.addEntity(Employee.class);
		return namedNativeQuery.getResultList();
		
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





