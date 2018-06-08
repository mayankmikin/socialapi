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

import com.capitalone.socialApiFb.model.ApiRequestMessage;
import com.capitalone.socialApiFb.model.ApiResponseMessage;
import com.capitalone.socialApiFb.model.FBSaveJsonModel;
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
}

//https://graph.facebook.com/v3.0/611765415846195?access_token=&debug=all&fields=likes%2Cabout%2Caccess_token%2Ccan_post%2Cposts&format=json&method=get&pretty=0&suppress_http_code=1