package com.board.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.pds.service.PdsService;
import com.board.pds.vo.PdsVo;

@Controller
@RequestMapping("/Pds")
public class PdsController {

	
	@Autowired
	private PdsService pdsService;
	
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		
		System.out.println(map);
		
		//자료실 목록 준비
		List <PdsVo> pdsList = pdsService.getPdsList(map); 
				
		ModelAndView mv =new ModelAndView();
		mv.addObject("map",map);
		mv.addObject("pdsList",pdsList);
		mv.setViewName("pds/list");
		
		return mv;
	}
	

	
}
