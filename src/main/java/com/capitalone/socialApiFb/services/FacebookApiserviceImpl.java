package com.capitalone.socialApiFb.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capitalone.socialApiFb.model.ApiPageRequest;
import com.capitalone.socialApiFb.model.ApiRequestMessage;
import com.capitalone.socialApiFb.model.ApiResponseMessage;
import com.capitalone.socialApiFb.model.Comments;
import com.capitalone.socialApiFb.model.FBSaveJsonModel;
import com.capitalone.socialApiFb.model.PagePostImpression;
import com.capitalone.socialApiFb.model.PagePostImpressions;
import com.capitalone.socialApiFb.model.Post;
import com.capitalone.socialApiFb.model.PostAllData;
import com.capitalone.socialApiFb.model.Posts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
@Service("fbservice")
public class FacebookApiserviceImpl implements FacebookApiservice {
	private static final Logger log = LoggerFactory.getLogger(FacebookApiserviceImpl.class);
	@Autowired
	private Environment env;
	
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
			 url=body.getNativeurl().trim()+"?access_token="+body.getAcesstoken()+"&debug=all"+"fields="+body.getFields().trim()+"&format=json&method=get&pretty=0&suppress_http_code=1";
		  }
		 
		  log.info("url is "+url);
	        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
	        log.info("output is "+node.toString());
	        ApiResponseMessage resp= new ApiResponseMessage(4, "success", node);
		log.info("inside getPosts END");
		return resp;
	}
	@Override
	public ApiResponseMessage getPerDay(String appName, ApiRequestMessage body) {
		  RestTemplate restTemplate = new RestTemplate();
		  String url="";
		log.info("inside getPerDay START");
		log.info(env.getProperty("base.url"));
		String pageid="";
		if(!body.getPageid().equalsIgnoreCase(""))
		{
			pageid=body.getPageid();
		}
		else
		{
			pageid=env.getProperty("pageid");
		}
		
		url=env.getProperty("base.url")+pageid+env.getProperty("fbapitype")
				+env.getProperty("accesstoken")+body.getAcesstoken()+env.getProperty("fields")+env.getProperty("fbmetrics")+body.getMetric()
				+env.getProperty("suffixparams");
		log.info("url is "+url);
        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
      //Get file from resources folder

    	ObjectMapper mapper = new ObjectMapper();
    	try {
    	Path newFilePath = Paths.get("src/main/resources/perday.json");
    	Files.createFile(newFilePath);
    	//Object to JSON in file
    	//ClassLoader classLoader = getClass().getClassLoader();
    	File file = new File("src/main/resources/perday.json");
    	//JsonNode savenode=mapper.convertValue(, JsonNode.class);
			mapper.writerWithDefaultPrettyPrinter().writeValue(file,node);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//mapper.writeValue(new File("c:\\file.json"), );
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
        log.info("output is "+node.toString());
        ApiResponseMessage resp= new ApiResponseMessage(4, "success", node);
	log.info("inside getPerDay END");
		return resp;
	}
	@Override
	public ApiResponseMessage getPerWeek(String appName, ApiRequestMessage body) {
		  RestTemplate restTemplate = new RestTemplate();
		  String url="";
		log.info("inside getPerWeek START");
		log.info("url is "+url);
      JsonNode node = restTemplate.getForObject(url, JsonNode.class);
      log.info("output is "+node.toString());
      ApiResponseMessage resp= new ApiResponseMessage(4, "success", node);
	log.info("inside getPerWeek END");
		return resp;
	}

	public List<FBSaveJsonModel> getDataIntoRelevantFormat(JsonNode node)
	{
		ArrayList<FBSaveJsonModel> list=new ArrayList<>();
		
		ArrayNode nodearr=(ArrayNode) node.get("data");
		
		int count=1;
		for(JsonNode n: nodearr)
		{
/*			log.info("inside array node "+ count);
			log.info(n.get("name").asText().toString());
			if(page_fan_adds)
			{
				
			}
			ArrayNode valuearr=(ArrayNode) n.get("values");
			for()
			{
			}*/
		}
		return list;
	}



@Override
public ApiResponseMessage savepostimpressions(ApiPageRequest req) {
	  RestTemplate restTemplate = new RestTemplate();
	  String url="";
	log.info("inside savepostimpressions START");
	log.info(env.getProperty("base.url"));
	String pageId="";
	if(!req.getPageid().equalsIgnoreCase(""))
	{
		pageId=req.getPageid();
	}
	else
	{
		pageId=env.getProperty("pageid");
	}
	
	url=env.getProperty("base.url")+pageId+"/posts?"
			+env.getProperty("accesstoken")+req.getAccesstoken()
			+env.getProperty("suffixparams");
	log.info("url is "+url);
    JsonNode node = restTemplate.getForObject(url, JsonNode.class);
  //Get file from resources folder

    log.info("posts are: "+node);
	ObjectMapper mapper = new ObjectMapper();
	Posts posts =mapper.convertValue(node, Posts.class);
	log.info("post data is : "+posts.toString());
/*	ArrayList<PagePostImpression> allimpressions=new ArrayList<>();*/
	PostAllData alldata= new PostAllData();
	
	for(Post p: posts.getData())
	{
		url=env.getProperty("base.url")+p.getId()+"/insights?"
				+env.getProperty("accesstoken")+req.getAccesstoken()
				+env.getProperty("methodname")
				+env.getProperty("fbmetrics")+"["+req.getMetrics()+"]"
				+"&period=lifetime"
				+env.getProperty("suffixparams");
				log.info("url is "+url);
			node = restTemplate.getForObject(url, JsonNode.class);
			PagePostImpressions impressions=mapper.convertValue(node, PagePostImpressions.class);
			alldata.getAllimpressionsData().addAll(impressions.getData());
			//allimpressions.addAll(impressions.getData());
			// getting comments for each posts
			log.info("getting commens data ");
			url=env.getProperty("base.url")+p.getId()+"/comments?"
					+env.getProperty("accesstoken")+req.getAccesstoken()
					+env.getProperty("suffixparams");
			log.info("url is "+url);
			node = restTemplate.getForObject(url, JsonNode.class);
			Comments comments=mapper.convertValue(node, Comments.class);
			alldata.getAllcommentsData().addAll(comments.getData());
	}
	
	log.info("impressions are: "+alldata);
	
			try {
				log.info("saving data in a file: postimpressions_lifetime");
				String filename="src/main/resources/postimpressions_lifetime_"+pageId+".json";
	Path newFilePath = Paths.get(filename);
	File file = new File(filename);
	if (file.exists() && file.isFile())
	  {
		log.info("filealready exists by name "+filename);
		log.info("deleteing file");
	  file.delete();
	  }
	Files.createFile(newFilePath);
	//Object to JSON in file
	//ClassLoader classLoader = getClass().getClassLoader();
	
	//JsonNode savenode=mapper.convertValue(, JsonNode.class);
		mapper.writerWithDefaultPrettyPrinter().writeValue(file,alldata);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//mapper.writeValue(new File("c:\\file.json"), );
catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    
    log.info("output is "+node.toString());
    ApiResponseMessage resp= new ApiResponseMessage(4, "success", mapper.convertValue(alldata, JsonNode.class));
log.info("inside getPerDay END");
	return resp;
}
}
//https://graph.facebook.com/v3.0/611765415846195?access_token=&debug=all&fields=likes%2Cabout%2Caccess_token%2Ccan_post%2Cposts&format=json&method=get&pretty=0&suppress_http_code=1