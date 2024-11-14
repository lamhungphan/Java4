package com.fpoly.java4_youtube.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManager() {
		if (factory == null || factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("java4_asm");
		}
		return factory.createEntityManager();
	}

	public static void shutDown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
