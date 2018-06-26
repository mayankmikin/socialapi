package com.capitalone.socialApiFb.services;

import java.util.List;

import org.springframework.social.twitter.api.Tweet;

public interface TwitterService {

	public List<Tweet> getTweets(String hashtag);
	
}
