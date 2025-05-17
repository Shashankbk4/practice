package com.ty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class SaveBranchData {
	public static void main(String[] args) {
		EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("vikas");
		EntityManager entityManager = EntityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		Branch branch=new Branch();
		branch.setName("jspider ");
		branch.setAddress("near vasavi mall ");
		branch.setPhno(7259707314l);
		branch.setCourse(" java full stack");
		
		Branch branch1=new Branch();
		branch1.setName("jspider ");
		branch1.setAddress("near metro ");
		branch1.setPhno(7246378373l);
		branch1.setCourse(" java full stack");
		Branch branch2=new Branch();
		branch2.setName(" Qspider");
		branch2.setAddress("near bus stand ");
		branch2.setPhno(7259707514l);
		branch2.setCourse("testing ");
		
		Branch branch3=new Branch();
		branch3.setName("BTM ");
		branch3.setAddress("near railway station ");
		branch3.setPhno(7249707324l);
		branch3.setCourse(" python full stack");
		
		Jspider jspider=new Jspider();
		jspider.setName("jspider");
		jspider.setWebsite("www.jsp.com");
		jspider.setEmail("jsp@gmail.com");
		
		Jspider jspider1=new Jspider();
		jspider1.setName("Qspider");
		jspider1.setWebsite("www.Qsp.com");
		jspider1.setEmail("Qsp@gmail.com");
		
		branch.setJspiders(jspider);
		branch1.setJspiders(jspider);
		branch2.setJspiders(jspider1);
		branch3.setJspiders(jspider1);
		
		entityTransaction.begin();
		entityManager.persist(branch);
		entityManager.persist(branch1);
		entityManager.persist(branch2);
		entityManager.persist(branch3);
		entityManager.persist(jspider);
		entityManager.persist(jspider1);
		entityTransaction.commit();
	}

}
