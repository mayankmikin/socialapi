package com.capitalone.socialApiFb.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.socialApiFb.model.ApiPageRequest;
import com.capitalone.socialApiFb.model.ApiRequestMessage;
import com.capitalone.socialApiFb.model.ApiResponseMessage;
import com.capitalone.socialApiFb.services.FacebookApiservice;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/socialapi")
public class FacebookApiController {
	 private static final Logger log = LoggerFactory.getLogger(FacebookApiController.class);
	 
	 	@Autowired
	 	FacebookApiservice fbservice;
	 	
	    private final ObjectMapper objectMapper;
	    private final HttpServletRequest request;
		public FacebookApiController(ObjectMapper objectMapper,
				HttpServletRequest request) {
			super();
			this.objectMapper = objectMapper;
			this.request = request;
		}
/*		   @RequestMapping(value = "/getPosts",
		            method = RequestMethod.POST)
		    public ResponseEntity<ApiResponseMessage> getPosts(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body) {
		       log.info("inside getPosts method");
			   String accept = request.getHeader("Accept");
		        ApiResponseMessage fbserviceResponse=fbservice.getPosts(appName, body);
		        return new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.OK);
		    }*/
	    
		@RequestMapping(value = "/perday",
	            method = RequestMethod.POST)
	    public ResponseEntity<ApiResponseMessage> getPerDayBasis(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body) {
	       log.info("inside getPerDayBasis method");
		   String accept = request.getHeader("Accept");
		   if(!body.getPeriod().equalsIgnoreCase("day"))
		   {
			   ApiResponseMessage fbserviceResponse=new ApiResponseMessage(1, "period allowed :{day}", null);
			   new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.BAD_REQUEST);
		   }
		   if(body.isInsights())
		   {
			   body.setMetric("["+body.getMetric()+"]"+"&period=day"); 
			   log.info("metrics are: "+body.getMetric());
			   ApiResponseMessage fbserviceResponse=fbservice.getPerDay(appName, body);
			   return new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.OK);
		   }
	        ApiResponseMessage fbserviceResponse=fbservice.getPerDay(appName, body);
	        return new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.OK);
	    }
		
/*		@RequestMapping(value = "/perweek",
	            method = RequestMethod.POST)
	    public ResponseEntity<ApiResponseMessage> getPerWeekBasis(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body) {
	       log.info("inside getPerDayBasis method");
		   String accept = request.getHeader("Accept");
	        ApiResponseMessage fbserviceResponse=fbservice.getPerWeek(appName, body);
	        return new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.OK);
	    }*/
		@RequestMapping(value = "/postimpressions",
	            method = RequestMethod.POST)
	    public ResponseEntity<ApiResponseMessage> savepostimpressions(@RequestBody ApiPageRequest req) {
	       log.info("inside getPerDayBasis method");
		   String accept = request.getHeader("Accept");
	        ApiResponseMessage fbserviceResponse=fbservice.savepostimpressions(req);
	        return new ResponseEntity<ApiResponseMessage>(fbserviceResponse,HttpStatus.OK);
	    }
}
