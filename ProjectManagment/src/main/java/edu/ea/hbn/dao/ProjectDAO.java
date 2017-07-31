package edu.ea.hbn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import edu.ea.hbn.domain.Project;
import edu.ea.hbn.domain.Status;

public class ProjectDAO {

	public static boolean createProject(Project project) {

		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		boolean isCreated = false;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(project);
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

	public static Project getProject(int projectID) {

		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		Project project = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			project = em.find(Project.class, Long.parseLong("" + projectID));
			tx.commit();
			return project;
		} catch (PersistenceException e) {
			if (tx != null && !tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			if (em != null)
				em.close();
		}
		return project;
	}

	public boolean removeProject(Project project) {

		boolean isRemoved = false;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(project);
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

	public Project updateProject(Project project) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		Project p = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			p = em.merge(project);
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

		return p;

	}

	public static List<Project> findAll() {

		TypedQuery<Project> query = JpaUtil.getEntityManager().createNamedQuery("Project.findAll", Project.class);

		return query.getResultList();
	}

	public static List<Project> findProjectByStatus(Status status) {

		TypedQuery<Project> query = JpaUtil.getEntityManager().createNamedQuery("Project.findByStatus", Project.class);
		query.setParameter("status", status);
		return query.getResultList();
	}

	public static List<Project> findProjectWithVolunteerNotNull() {

		TypedQuery<Project> query = JpaUtil.getEntityManager()
				.createNamedQuery("Project.findProjectWithVolunteerNotNull", Project.class);

		return query.getResultList();
	}

	public static List<Project> findProjectByKeyWordAnLocation(String searhkeyword) {
		TypedQuery<Project> query = JpaUtil.getEntityManager()
				.createNamedQuery("Project.findProjectByKeyWordAnLocation", Project.class);
		query.setParameter("searchkeyword", "%" + searhkeyword + "%");
		return query.getResultList();
	}

}
