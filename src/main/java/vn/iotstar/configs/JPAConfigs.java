package vn.iotstar.configs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
//import vn.iotstar.entity.Category;

@PersistenceContext
public class JPAConfigs {
	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-sql");

		return factory.createEntityManager();

	}
}
