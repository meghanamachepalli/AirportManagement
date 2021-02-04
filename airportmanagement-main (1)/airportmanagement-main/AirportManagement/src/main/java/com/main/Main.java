package com.main;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.model.Admin;
import com.model.Hangar;
import com.model.Manager;
import com.model.Pilot;
import com.model.Plane;

@Transactional
public class Main {

	public static void main(String[] args) {

		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("com/config/hibernate.cfg.xml")
				.build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Admin admin = new Admin();
		admin.setAdminFirstName("Lakshmi");
		admin.setAdminLastName("Meghana");
		admin.setAdminAge(20);
		admin.setAdminContactNumber(123);
		admin.setAdminGender("female");
		admin.setAdminPassword("mayurima");
		session.save(admin);
		Manager manager = new Manager();
		manager.setManagerFirstName("sireesha");
		manager.setManagerLastName("jammu");
		manager.setManagerGender("female");
		manager.setManagerAge(5);
		manager.setManagerContactNumber(456);
		manager.setManagerPassword("mayuri@413");
		session.save(manager);
		Plane plane = new Plane();
		plane.setPlaneName("spyjet");
		Hangar hangar = new Hangar();
		hangar.setPlane(plane);
		hangar.setHangarLocation("chennai");
		Pilot pilot = new Pilot();
		pilot.setPilotName("vinod");
		pilot.setPilotLocation("kerala");
		pilot.setPlane(plane);
		session.save(plane);
		session.save(pilot);
		session.save(hangar);
	}

}
