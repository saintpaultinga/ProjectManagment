package edu.ea.hbn.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import edu.ea.hbn.domain.Project;
import edu.ea.hbn.domain.Task;

public class TaskDAO {
	
	
	//possible nullpointerException
	public static boolean createTask(Project project, Task task) {
		boolean isCreated = false;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
      
		try {
			
			tx = em.getTransaction();
			tx.begin();
			
			project.addTask(task);
			em.merge(project);
			tx.commit();
			return isCreated = true;
		} catch (PersistenceException e) {
			if (tx != null && !tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		
		return isCreated;

	}
	
	//possible nullpointerException
	public static void upDateTask(Project project, Task task) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;

		try {
			project.updateTask(task);
			tx = em.getTransaction();
			tx.begin();
			em.persist(project);
			tx.commit();
		} catch (PersistenceException e) {
			if (tx != null && !tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}

	}
	
	public static Task getTask(int taskID) {

		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		Task task = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			task = em.find(Task.class, Long.parseLong(""+taskID));
			tx.commit();
			return task;
		} catch (PersistenceException e) {
			if (tx != null && !tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return task;
	}
   
	public static boolean DeleteTask(Project project, Task task) {
		
		boolean isRemoved = false;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;

		try {
			project.removeTask(task);
			tx = em.getTransaction();
			tx.begin();
			em.merge(project);
			tx.commit();
			isRemoved = true;
		} catch (PersistenceException e) {
			if (tx != null && !tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
          return isRemoved;
	}



}
