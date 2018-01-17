package com.wook;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wook.VO.ResponseVO;
import com.wook.controller.MainController;
import com.wook.service.MainService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MainController.class)
public class MainControllerTests {
	
	//Honestly This Fails....
	//Not a great tester
	//Willing to F/U TDD
	
	@Autowired
    private MockMvc mvc;
 
    @Mock
    @Autowired
    private MainService service;
    
    @Test
    public void testMainController()
      throws Exception {
         
    	ResponseVO responseVO = new ResponseVO("150.242.104.0","BD");
                
        when(service.getLocation("150.242.104.0")).thenReturn(responseVO);
     
        mvc.perform(get("/ip/150.242.104.0")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
    

}
