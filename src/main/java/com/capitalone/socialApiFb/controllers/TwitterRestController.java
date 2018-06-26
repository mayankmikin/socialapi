package com.capitalone.socialApiFb.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/socialapi/twitter/rest")
public class TwitterRestController {
	private static final Logger log = LoggerFactory.getLogger(FacebookApiController.class);
	ObjectMapper mapper= new ObjectMapper();
	/*@Autowired
	TwitterService tweetservice;*/
	
	@Autowired
	private TwitterTemplateCreator twitterTemplateCreator;
	
	 @RequestMapping(value = "/getTweetsBasedonhashtag",method = RequestMethod.GET)
	   public ResponseEntity<JsonNode> getTweetsBasedonhashtag(@RequestParam String hashtag) 
	   {	log.info("inside getStatus method");
		ObjectNode result = mapper.createObjectNode();
		try {
			//JsonNode data = mapper.convertValue(tweetservice.getTweets(hashtag), JsonNode.class);
			JsonNode data = mapper.convertValue(twitterTemplateCreator.getTwitterTemplate().searchOperations().search(hashtag,20).getTweets(), JsonNode.class);;
			result.put("data", data);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JsonNode>(result,HttpStatus.OK);
	 
	   }
}
