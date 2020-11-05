package com.intuit.martech.microurl.service;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.intuit.martech.microurl.domain.User;


public interface UserService {

    void saveUser(User user) throws Exception;

	Collection<User> findAllUsers() throws DataAccessException;

	boolean isUserPresent(String userName);

	boolean isUserReachedLimit(String userName);

	boolean isBasicUser(String userName);
}
