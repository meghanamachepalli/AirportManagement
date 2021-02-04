package com.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.actions.ManagerController;
import com.dao.AdminDao;
import com.dao.ManagerDao;
import com.model.Admin;
import com.model.Manager;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	ManagerDao managerDao;

	@Autowired
	AdminDao adminDao;

	private static Logger logger = (Logger) LogManager.getLogger(SecurityService.class);

	@Override
	public Admin adminAuthenticate(Admin admin) {
		logger.debug("Processing Admin Data");
		return adminDao.getAllAdmins().stream()
				.filter(check -> check.getAdminFirstName().equals(admin.getAdminFirstName()))
				.filter(check -> check.getAdminPassword().equals(admin.getAdminPassword())).findFirst().get();

	}

	@Override
	public Manager managerAuthenticate(Manager manager) {
		logger.debug("Processing Manager Data");
		return managerDao.getAllManagers().stream()
				.filter(check -> check.getManagerFirstName().equals(manager.getManagerFirstName()))
				.filter(check -> check.getManagerPassword().equals(manager.getManagerPassword())).findFirst().get();

	}

}
