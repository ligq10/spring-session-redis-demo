package com.changhongit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.changhongit.model.SessionObject;

@Controller
public class TestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/session",method = RequestMethod.POST, 
			produces = "application/json;charset=UTF-8")
	public HttpEntity<?> postSession(
			@RequestBody SessionObject sessionObject,
			HttpServletRequest request) {

		String host = request.getLocalName();
		int port = request.getLocalPort();
		HttpSession session =  request.getSession();
		logger.info(session.getId());
		sessionObject.setSessionId(session.getId());
		sessionObject.setHost(host+":"+port);
		session.setAttribute(sessionObject.getKey(), sessionObject);
		return new ResponseEntity<SessionObject>(
				sessionObject,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/session/{key}",method = RequestMethod.GET, 
			produces = "application/json;charset=UTF-8")
	public HttpEntity<?> getSession(
			@PathVariable String key, 
			HttpServletRequest request) {
		HttpSession session =  request.getSession();
		logger.info(session.getId());
		SessionObject sessionObject = (SessionObject) request.getSession().getAttribute(key);
		if(null == sessionObject){
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

		}
		String host = request.getLocalName();
		int port = request.getLocalPort();
		sessionObject.setHost(host+":"+port);

		return new ResponseEntity<SessionObject>(
				sessionObject,HttpStatus.OK);
	}
}
