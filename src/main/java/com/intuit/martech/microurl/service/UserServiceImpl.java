package com.intuit.martech.microurl.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.martech.microurl.domain.User;
import com.intuit.martech.microurl.domain.User.TypeEnum;
import com.intuit.martech.microurl.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(User user) throws Exception {
      user.setRequestCounter(0l);
      userRepository.save(user);
    }

   



	@Override
	@Transactional(readOnly = true)
	public Collection<User> findAllUsers() throws DataAccessException{
		return userRepository.findAll();
	}



	@Override
	public boolean isUserPresent(String userName) {
		return userRepository.existsById(userName);
	}



	@Override
	public boolean isUserReachedLimit(String userName) {
		Optional<User> optionalUserObj = userRepository.findById(userName);
		User user = optionalUserObj.get();
		if(user.getRequestCounter()>=10) {
			return true;
		}
		return false;
	}



	@Override
	public boolean isBasicUser(String userName) {
		Optional<User> optionalUserObj = userRepository.findById(userName);
		User user = optionalUserObj.get();
		if(user.getRole().name().equalsIgnoreCase((TypeEnum.BASIC.name()))) {
			return true;
		}
		return false;
	}
    
	
}
