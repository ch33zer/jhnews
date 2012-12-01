package com.jhnews.server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Used to fetch SessionFactory object
 * @author Group 8
 *
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new AnnotationConfiguration()
            .configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Gets the SessionFactory singleton
	 * @return The instance of the SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}