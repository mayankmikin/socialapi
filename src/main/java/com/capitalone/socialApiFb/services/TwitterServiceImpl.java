/*package com.capitalone.socialApiFb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service("tweetservice")
public class TwitterServiceImpl implements TwitterService{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Twitter twitter;
	   
	   public void tweet(Twitter twitter, String tweetText) {
	      try {
	         twitter.timelineOperations().updateStatus(tweetText);
	      } catch (RuntimeException ex) {
	         logger.error("Unable to tweet" + tweetText, ex);
	      }
	   }

	@Override
	public List<Tweet> getTweets(String hashtag) 
	{
		try {
			return twitter.searchOperations().search(hashtag,20).getTweets();
			
	      } catch (RuntimeException ex) {
	         logger.error("Unable to tweet", ex);
	      }
		
	return null;
	}
}
*/