package com.intuit.martech.microurl.web.rest;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.martech.microurl.domain.User;
import com.intuit.martech.microurl.service.UserService;


@RestController
@RequestMapping("/api/v1/user")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> addUser(@RequestBody  User user) throws Exception {
        if (user == null) {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }

        this.userService.saveUser(user);
        log.debug("User created :"+user.getUsername());
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
    

   @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> users = this.userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

   
}
