package com.capitalone.socialApiFb.services;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.capitalone.socialApiFb.model.ApiRequestMessage;
import com.capitalone.socialApiFb.model.ApiResponseMessage;

public interface FacebookApiservice {


ApiResponseMessage getPosts(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body);

ApiResponseMessage getPerDay(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body);

ApiResponseMessage getPerWeek(@RequestHeader(value="platform-name", required=true) String appName,@RequestBody ApiRequestMessage body);
}
