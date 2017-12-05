package org.hibernate.bugs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh12119Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...

		final Query query = entityManager.createNativeQuery("select i.invoice_id as \"invoiceId\", i.booking_tag as \"bookingTag\", i.invoice_number as \"invoiceNumber\", i.created_by as \"createdBy\", i.invoice_api_groups as \"apiGroups\", i.invoice_date as \"invoiceDate\", r.recipient as \"recipient\" from invoice i inner join recipient r on (i.recipient_recipient_id = r.recipient_id) where i.invoice_state = 0 and i.invoice_api_groups similar to '%tlang%|%Seminare%' order by i.invoice_date desc");
		final List resultList = query.getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
