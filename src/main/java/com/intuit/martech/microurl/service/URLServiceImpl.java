package com.intuit.martech.microurl.service;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.martech.microurl.domain.MicroUrlRequest;
import com.intuit.martech.microurl.domain.URL;
import com.intuit.martech.microurl.domain.UrlResponse;
import com.intuit.martech.microurl.domain.User;
import com.intuit.martech.microurl.repository.URLRepository;
import com.intuit.martech.microurl.repository.UserRepository;
import com.intuit.martech.microurl.util.Constants;
import com.intuit.martech.microurl.util.Encoder;
import com.intuit.martech.microurl.util.Validator;




@Service
public class URLServiceImpl implements URLService{
    private static final Logger LOGGER = LoggerFactory.getLogger(URLServiceImpl.class);
   
    @Autowired
    private URLRepository urlRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Encoder encoder;
    
    
    
    
    public String generateShortURL(String longURL) {
    	if(Validator.isStingNullOrEmpty(longURL)) {
    		return Constants.EMPTY;
    	}
    	
    	return encoder.hashing(longURL);
    }
    
    @Override
    @Transactional
	public URL saveURL(MicroUrlRequest urlBto) throws Exception {
		URL url = createUrl(urlBto);
		return urlRepository.save(url);
	}
	
	
    private URL createUrl(MicroUrlRequest urlBto) {
		URL url = new URL();
		User user = new User();
		url.setHash(generateShortURL(urlBto.getLongUrl()));
		url.setOriginalurl(urlBto.getLongUrl());
		if(userService.isUserPresent(urlBto.getUserName())) {
			updateUser(urlBto.getUserName());
		}
		user.setUsername(urlBto.getUserName());
		url.setUser(user);
		return url;
	}
    
   
    
    private void updateUser(String username) {
    	Optional<User> optionalUserBo = userRepository.findById(username);
		User user = optionalUserBo.get();
		user.setRequestCounter(user.getRequestCounter()+1);
		userRepository.save(user);
	}


	@Override
	@Transactional(readOnly = true)
	public Collection<URL> findAllUrls() throws DataAccessException {
		return urlRepository.findAll();
	}
	
	
	public String createUrl(String host, String path) {
		
		return host + Constants.DIR_SEPARATOR + path;
      
	}
	
	public UrlResponse saveURLAndGetRespose(MicroUrlRequest microUrlRequest, HttpServletRequest request) throws Exception {
		
		UrlResponse urlResponse = new UrlResponse();
		saveURL(microUrlRequest);
		String path = generateShortURL(microUrlRequest.getLongUrl());
        String host = request.getRequestURL().toString();
        
        urlResponse.setLongUrl(microUrlRequest.getLongUrl());
        urlResponse.setUserName(microUrlRequest.getUserName());
        urlResponse.setMicroUrl(createUrl(host, path));
        
		return urlResponse;
		
	}
	
		
	@Override
	@Transactional(readOnly = true)
	public Optional<URL> getLongURLById(String id) throws DataAccessException {
		Optional<URL> url = null;
		try {
			url = urlRepository.findById(id);
		} catch (ObjectRetrievalFailureException|EmptyResultDataAccessException e) {
			return null;
		}
		return url;
	}

	@Override
	public String saveURLAndGetMicroUrl(MicroUrlRequest microUrlRequest, HttpServletRequest request) throws Exception {
		saveURL(microUrlRequest);
		String path = generateShortURL(microUrlRequest.getLongUrl());
        String host = request.getRequestURL().toString();
        
        return createUrl(host, path);
        
	}


}
