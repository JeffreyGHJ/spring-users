package com.jeffreyghj.springusers.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jeffreyghj.springusers.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// JPA implementation using entity manager; instead of Hibernate impl using sessionFactory
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<User> getUsers() {		
		Query theQuery = entityManager.createQuery("from User");
		List<User> users = theQuery.getResultList();
		return users;
	}

	@Override
	public void saveUser(User theUser) {
		User dbUser = entityManager.merge(theUser);
		theUser.setId(dbUser.getId());
	}

	@Override
	public User getUser(int theId) {
		User theUser = entityManager.find(User.class, theId);
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {		
		Query theQuery = entityManager.createQuery("delete from User where id=:userId");
		theQuery.setParameter("userId", theId);
		theQuery.executeUpdate();	
	}

}
