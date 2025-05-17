package com.ty;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GetAllBranch {
	public static void main(String[] args) {
		
	
	EntityManagerFactory EntityManagerFactory = Persistence.createEntityManagerFactory("vikas");
	EntityManager entityManager = EntityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	Query query=entityManager.createQuery("select b from Branch b");
	List<Branch> br = query.getResultList();
	for(Branch branch:br) {
		System.out.println(branch.getId());
		System.out.println(branch.getName());
		System.out.println(branch.getAddress());
		System.out.println(branch.getPhno());
		System.out.println(branch.getCourse());

		System.out.println("==============");
	}
}
}