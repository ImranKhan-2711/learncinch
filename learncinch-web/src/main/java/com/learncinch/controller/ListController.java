package com.learncinch.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learncinch.dto.AccountDto;
import com.learncinch.service.AccountMasterService;

@RestController
public class ListController {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	AccountMasterService accountMasterService;
	
	
	@RequestMapping(value = "/api/auth/list", method = RequestMethod.GET)
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		
		
		AccountDto accountDto = accountMasterService.findByUserName("mandeep");
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		tokenMap.put("Account",accountDto );
		try {
			objectMapper.writeValue(response.getWriter(), tokenMap);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
