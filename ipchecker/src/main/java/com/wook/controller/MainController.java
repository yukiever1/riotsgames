package com.wook.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.wook.VO.ResponseVO;
import com.wook.service.MainService;


@RestController
public class MainController {
	
	@Autowired
	MainService mainService;
	
	@RequestMapping(value="/ip/{ipAddress:.+}", headers="Accept=application/json",method=RequestMethod.GET)
    public ResponseVO getIp(@PathVariable("ipAddress") String ipAddress, HttpServletResponse response) {
    		
    	ResponseVO responseVO = new ResponseVO();  	
    	responseVO = mainService.getLocation(ipAddress);
    	
    	// If black Ip Just return 400
    	if (responseVO.getNationalCode().equals("BlackIP")) {
    		response.setStatus( HttpServletResponse.SC_BAD_REQUEST  );
    		return null;
    	}
    	// Set Time and return response!
    	java.util.Date today = new java.util.Date();
    	java.sql.Timestamp ts1 = new java.sql.Timestamp(today.getTime());
    	long tsTime1 = ts1.getTime();
    	responseVO.setTimestamp(tsTime1);
    	
    	return responseVO;
    }
}
