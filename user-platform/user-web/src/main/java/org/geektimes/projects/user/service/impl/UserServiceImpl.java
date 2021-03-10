package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
	private Connection connection;

	public UserServiceImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean register(User user) throws SQLException {
		return new DatabaseUserRepository(new DBConnectionManager(this.connection)).save(user);
	}

	@Override
	public boolean deregister(User user) {
		return false;
	}

	@Override
	public boolean update(User user) {
		return false;
	}

	@Override
	public User queryUserById(Long id) {
		return null;
	}

	@Override
	public User queryUserByNameAndPassword(String name, String password) {
		return null;
	}
}
