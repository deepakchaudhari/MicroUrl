package com.intuit.martech.microurl.web.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.intuit.martech.microurl.domain.MicroUrlRequest;
import com.intuit.martech.microurl.domain.URL;
import com.intuit.martech.microurl.domain.User;
import com.intuit.martech.microurl.domain.User.TypeEnum;
import com.intuit.martech.microurl.service.URLService;

@RunWith(SpringRunner.class)
@WebMvcTest(URLResource.class)
public class URLResourceTest {

   @Autowired
   private MockMvc mockmvc;

   @MockBean
   private URLResource urlResource;
   
   @MockBean
   private URLService urlSerivce;

   @Test
	public void createURL() throws Exception {
	   
		MicroUrlRequest mockURL = new MicroUrlRequest();
		mockURL.setLongUrl("testOriginalURL");
		mockURL.setUserName("testUser");
		
		User user = new User();
		user.setUsername("testUser");
		user.setRole(TypeEnum.BASIC);
		
		URL url = new URL();
		url.setHash("testHash");
		url.setOriginalurl("testUser");
		url.setUser(user);
		
		String resposeJson = "{\n" + 
				"  \"longUrl\": \"testOriginalURL\",\n" + 
				"  \"microUrl\": \"testHash\",\n" + 
				"  \"userName\": \"testUser\"\n" + 
				"}";

		Mockito.when(
				urlSerivce.saveURL(Mockito.any(MicroUrlRequest.class))).thenReturn(url);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1/url")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).content(resposeJson)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());


	}
  
}


