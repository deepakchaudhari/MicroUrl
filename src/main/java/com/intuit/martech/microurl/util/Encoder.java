package com.intuit.martech.microurl.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

@Service
public class Encoder {
	
	
	public String hashing(String inputString) {
		
		if(Validator.isStingNullOrEmpty(inputString)) {
			return Constants.EMPTY;
		}
			
		return Hashing.murmur3_32().hashString(inputString, StandardCharsets.UTF_8).toString();	
	}
	
	
	
	public String encryptToBase64(String message) {
		if(message.length() == 0) {
			return Constants.EMPTY;
		}
			return Base64.getEncoder().encodeToString(message.getBytes());
		
	}
	
	
	public String decryptFromBase64(String message) {
		if(message.length() == 0) {
			return Constants.EMPTY;
		}

		byte[] decodedMessage =  Base64.getDecoder().decode(message);
		if (decodedMessage != null) {
			return new String(decodedMessage);
		}
		else {
			return null;
		}
	}

}
