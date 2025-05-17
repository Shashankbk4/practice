package com.ty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaveCompany {
	public static void main(String[] args) {
		EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = EntityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Company company = new Company();
		company.setName("TCS");
		company.setWebsite("WWW.TCS.COM");
		company.setAddress("Bangalore");
		company.setPhno(7259707314l);

		Company company1 = new Company();
		company1.setName("WIPRO");
		company1.setWebsite("WWW.WIPRO.COM");
		company1.setAddress("DELHI");
		company1.setPhno(7259997314l);

		Company company2 = new Company();
		company2.setName("GENC");
		company2.setWebsite("WWW.GENC.COM");
		company2.setAddress("MUMBAI");
		company2.setPhno(72597045314l);

		Gst gst = new Gst();

		gst.setGstno("TCS123");
		gst.setState("karnataka");
		gst.setCountry("india");
		Gst gst1 = new Gst();

		gst1.setGstno("TCS123");
		gst1.setState("karnataka");
		gst1.setCountry("india");
		Gst gst2 = new Gst();

		gst2.setGstno("TCS123");
		gst2.setState("karnataka");
		gst2.setCountry("india");

		company.setGsts(gst);
		company1.setGsts(gst1);
		company2.setGsts(gst2);

		entityTransaction.begin();
		entityManager.persist(company);
		entityManager.persist(company1);
		entityManager.persist(company2);
		entityManager.persist(gst);
		entityManager.persist(gst1);
		
		entityTransaction.commit();

	}
}
