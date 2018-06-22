package com.capitalone.socialApiFb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.TwitterException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/socialapi/twitter")
public class TwitterApiController 
{
	@Autowired
	TwitterConfig twitterservice ;
	 private static final Logger log = LoggerFactory.getLogger(FacebookApiController.class);
	 ObjectMapper mapper= new ObjectMapper();
	 @RequestMapping(value = "/getStatus",method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getStatus() 
	{
	log.info("inside getStatus method");
	ObjectNode result = mapper.createObjectNode();
	try {
		JsonNode data = mapper.convertValue(twitterservice.getTwitterinstance().getHomeTimeline().toString(), JsonNode.class);
		result.put("data", data);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ResponseEntity<JsonNode>(result,HttpStatus.OK);
	}
}
