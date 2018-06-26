package com.capitalone.socialApiFb.controllers;

import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;


@Component
public class TwitterTemplateCreator {
   @Autowired
   private Environment env;
   public Twitter getTwitterTemplate() {
      String consumerKey = env.getProperty("twitter.oauth.consumerKey");
      String consumerSecret = env.getProperty("twitter.oauth.consumerSecret");
      String accessToken = env.getProperty("twitter.oauth.accessToken");
      String accessTokenSecret = env.getProperty("twitter.oauth.accessTokenSecret");
    /*  Preconditions.checkNotNull(consumerKey);
      Preconditions.checkNotNull(consumerSecret);
      Preconditions.checkNotNull(accessToken);
      Preconditions.checkNotNull(accessTokenSecret);*/
 
      TwitterTemplate twitterTemplate = 
         new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
      return twitterTemplate;
   }
}
