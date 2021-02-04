package com.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.actions.AdminController;
import com.model.Admin;
import com.model.Hangar;
import com.model.Manager;
import com.model.Pilot;
import com.model.Plane;

@Repository("adminDao")
@Transactional
@SuppressWarnings("unchecked")
public class AdminDaoImpl implements AdminDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ManagerDao managerDao;

	private static Logger logger = (Logger) LogManager.getLogger(AdminDaoImpl.class);

	@Override
	public List<Admin> getAllAdmins() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Fetching all admins");
		return (List<Admin>) session.createNamedQuery("getAllAdmins").getResultList();
	}

	@Override
	public boolean addPilot(Pilot pilot) {
		Session session = sessionFactory.getCurrentSession();
		session.save(pilot);
		logger.info(pilot.getPilotName() + " added");
		return true;
	}

	@Override
	public boolean addPlane(Plane plane) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(plane);
		logger.info(plane.getPlaneName() + " added");
		return true;
	}

	@Override
	public boolean addHangar(Hangar hangar) {
		Session session = sessionFactory.getCurrentSession();
		session.save(hangar);
		logger.info(hangar.getHangarLocation() + " added");
		return true;
	}

	@Override
	public List<Pilot> getAllPilots() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Fetching all pilots");
		return (List<Pilot>) session.createNamedQuery("getAllPilots").getResultList();
	}

	@Override
	public List<Plane> getAllPlanes() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Fetching all planes");
		return (List<Plane>) session.createNamedQuery("getAllPlanes").getResultList();
	}

	@Override
	public List<Hangar> getAllHangars() {
		Session session = sessionFactory.getCurrentSession();
		logger.debug("Fetching all hangars");
		return (List<Hangar>) session.createNamedQuery("getAllHangars").getResultList();
	}

	@Override
	public Pilot getPilotById(int pilotId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("getPilotById");
		logger.info("Data of " + pilotId + " fetched");
		return (Pilot) query.setParameter("id", pilotId).getSingleResult();

	}

	@Override
	public Plane getPlaneById(int planeId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("getPlaneById");
		logger.info("Data of " + planeId + " fetched");
		return (Plane) query.setParameter("id", planeId).getSingleResult();
	}

	@Override
	public Hangar getHangerById(int hangarId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNamedQuery("getHangarById");
		logger.info("Data of " + hangarId + " fetched");
		return (Hangar) query.setParameter("id", hangarId).getSingleResult();

	}

	@Override
	public boolean addAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.save(admin);
		logger.info(admin.getAdminFirstName() + " added as an admin");
		return true;
	}

	@Override
	public boolean managerApproval(int managerId) {
		Session session = sessionFactory.getCurrentSession();
		Manager manager = session.load(Manager.class, managerId);
		manager.setApprovalStatus("Approved");
		session.update(manager);
		logger.info(manager.getManagerFirstName() + " has been approved");
		return true;
	}

}
