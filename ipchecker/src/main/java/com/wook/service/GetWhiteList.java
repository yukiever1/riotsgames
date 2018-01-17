package com.wook.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wook.VO.WhiteListVO;

@Service
public class GetWhiteList {

	public List<WhiteListVO> WhiteLists() {
		
		//Get Dynamic White List from given URI
		RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("https://recruit-riotkr.mockable.io/api/v1/managed-ip").build().toUri();      
        
        WhiteListVO[] whiteList = restTemplate.getForObject(uri, WhiteListVO[].class);
        List<WhiteListVO> whiteLists= new ArrayList<WhiteListVO>();
        
        for (WhiteListVO whiteListVO : whiteList) {
        	whiteLists.add(whiteListVO);
        }       
        return whiteLists;      
	}	
}
