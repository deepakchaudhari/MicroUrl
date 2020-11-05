package com.intuit.martech.microurl.service;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.intuit.martech.microurl.domain.MicroUrlRequest;
import com.intuit.martech.microurl.domain.URL;
import com.intuit.martech.microurl.domain.UrlResponse;

public interface URLService {
	
	String generateShortURL(String longURL);
	URL saveURL(MicroUrlRequest url) throws Exception;

	Collection<URL> findAllUrls() throws DataAccessException;
	
	public String createUrl(String host, String path);
	
	Optional<URL> getLongURLById(String id) throws DataAccessException;
	
	UrlResponse saveURLAndGetRespose(MicroUrlRequest microUrlRequest, HttpServletRequest request) throws Exception;
	String saveURLAndGetMicroUrl(MicroUrlRequest microUrlRequest, HttpServletRequest request) throws Exception;

}
