package com.intuit.martech.microurl.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.intuit.martech.microurl.domain.MicroUrlRequest;
import com.intuit.martech.microurl.domain.URL;
import com.intuit.martech.microurl.domain.UrlResponse;
import com.intuit.martech.microurl.exception.InvalidRequestException;
import com.intuit.martech.microurl.service.URLService;
import com.intuit.martech.microurl.service.UserService;
import com.intuit.martech.microurl.util.Validator;

@RestController
@RequestMapping("/api/v1/url")
public class URLResource {
	
    private final Logger LOGGER = LoggerFactory.getLogger(URLResource.class);
    
    @Autowired
    private URLService urlService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UrlResponse> microUrl(@RequestBody MicroUrlRequest microUrlRequest,HttpServletRequest request) throws Exception {
        
    	UrlResponse urlResponse = new UrlResponse();
    	
    	if (microUrlRequest == null) {
    	    return new ResponseEntity<UrlResponse>(urlResponse,HttpStatus.BAD_REQUEST);
        }
    	if (!Validator.INSTANCE.validateURL(microUrlRequest.getLongUrl())) {
    		return new ResponseEntity<UrlResponse>(urlResponse,HttpStatus.BAD_REQUEST);
        }
    	if(!userService.isUserPresent(microUrlRequest.getUserName())) {
    		return new ResponseEntity<UrlResponse>(urlResponse,HttpStatus.BAD_REQUEST);
    	}
    	if(userService.isBasicUser(microUrlRequest.getUserName())  && userService.isUserReachedLimit(microUrlRequest.getUserName())) {
    		return new ResponseEntity<UrlResponse>(urlResponse,HttpStatus.BAD_REQUEST);
    	}
    	
    	LOGGER.info("Received url to shorten: " + microUrlRequest.getLongUrl());
        
    	urlResponse = urlService.saveURLAndGetRespose(microUrlRequest,request);
        
        LOGGER.info("Shortened url to: " + urlResponse.getMicroUrl());
        
        return new ResponseEntity<UrlResponse>(urlResponse,HttpStatus.CREATED);
        
    }

    @RequestMapping(value = "/{shortUrl}", method=RequestMethod.GET)
    public RedirectView redirectUrl(@PathVariable String shortUrl, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException, Exception {
        LOGGER.info("Received shortened url to redirect: " + shortUrl);
        RedirectView redirectView = new RedirectView();
        Optional<URL> urlObj = urlService.getLongURLById(shortUrl);
        
        urlObj.ifPresent(url -> {
        	LOGGER.info("Original URL: " + url.getOriginalurl());
        	redirectView.setUrl(url.getOriginalurl());
        });
        
        
        return redirectView;
    }
}


