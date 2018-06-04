package com.capitalone.socialApiFb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capitalone.socialApiFb.model.ApiRequestMessage;
import com.capitalone.socialApiFb.model.ApiResponseMessage;
import com.fasterxml.jackson.databind.JsonNode;
@Service("fbservice")
public class FacebookApiserviceImpl implements FacebookApiservice {
	private static final Logger log = LoggerFactory.getLogger(FacebookApiserviceImpl.class);
	@Override
	public ApiResponseMessage getPosts(String appName, ApiRequestMessage body) {

		log.info("inside getPosts START");
		//log.info("validating ApiRequestMessage body");
		  RestTemplate restTemplate = new RestTemplate();
		  String url="";
		  if(body.getNativeurl()!=null && !body.getNativeurl().equals(""))
		  {
			 url=body.getNativeurl(); 
		  }
		  else
		  {
			 url=body.getUrl().trim()+"?access_token="+body.getAcesstoken()+"&debug=all"+"fields="+body.getFields().trim()+"&format=json&method=get&pretty=0&suppress_http_code=1";
		  }
		 
		  log.info("url is "+url);
	        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
	        log.info("output is "+node.toString());
	        ApiResponseMessage resp= new ApiResponseMessage(4, "success", node);
		log.info("inside getPosts END");
		return resp;
	}

}

//https://graph.facebook.com/v3.0/611765415846195?access_token=&debug=all&fields=likes%2Cabout%2Caccess_token%2Ccan_post%2Cposts&format=json&method=get&pretty=0&suppress_http_code=1