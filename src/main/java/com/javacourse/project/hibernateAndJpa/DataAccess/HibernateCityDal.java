package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.Entities.City;

import jakarta.persistence.EntityManager;

@Repository
public class HibernateCityDal implements ICityDal {

	private EntityManager entityManager;

	@Autowired
	public HibernateCityDal(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<City> getAll() {
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		List<City> cities = session.createQuery("from City", City.class).getResultList();
				return cities;
		
	}

	@Override
	@Transactional
	public void add(City city) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		session.saveOrUpdate(city);

	}

	@Override
	@Transactional
	public void update(City city) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		session.saveOrUpdate(city);

	}

	@Override
	@Transactional
	public void delete(City city) {
		
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		City cityToDelete = session.get(City.class, city.getId());
		session.delete(cityToDelete);
	}

	@Override
	public City getById(int id) {
		org.hibernate.Session session =  entityManager.unwrap(org.hibernate.Session.class);
		City city = session.get(City.class, id);
		return city;
	}

}
