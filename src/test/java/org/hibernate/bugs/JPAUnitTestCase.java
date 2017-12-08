package org.hibernate.bugs;

import org.hibernate.bugs.db.Invoice;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import java.util.List;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");

		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();


	}

	@After
	public void destroy() {

		entityManager.getTransaction().commit();
		entityManager.close();

		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-
	// path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh12119Test() throws Exception {
		// Do stuff...

		Invoice invoice = new Invoice();
		invoice.setBookingTag("a booking tag");

		entityManager.persist(invoice);

		final Query query = entityManager.createNativeQuery("SELECT invoiceid AS \"idWithUpperCase\", bookingtag AS \"bookingTagWithUpperCase\" FROM invoice", Tuple.class);
		final List<Tuple> resultList = query.getResultList();

		Assert.assertEquals(1, resultList.size());
		Tuple tuple = resultList.get(0);
		List<TupleElement<?>> elements = tuple.getElements();
		Assert.assertEquals(2, elements.size());

		for (TupleElement<?> element : elements) {
			Assert.assertTrue(element.getAlias(), element.getAlias().contains("WithUpperCase"));
			;
		}


	}

	@Test
	public void doNothing() throws Exception {

		Invoice invoice = new Invoice();
		invoice.setBookingTag("a booking tag");

		entityManager.persist(invoice);
	}
}
