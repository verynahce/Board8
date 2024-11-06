package com.board.pds.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.board.board.vo.BoardVo;
import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;
import com.board.paging.vo.Pagination;
import com.board.paging.vo.PagingResonse;
import com.board.paging.vo.SearchVo;
import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;
import com.board.pds.vo.PdsVo;

@Controller
@RequestMapping("/Pds")
public class PdsController {

	
	@Autowired
	private PdsService pdsService;
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private PdsMapper pdsMapper;
	
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		
		System.out.println(map);
		
		//메뉴 목록
		List <MenuVo> menuList = menuMapper.getMenuList();
		
		//자료실 목록 (모든 data list)		
		//List <PdsVo> pdsList = pdsService.getPdsList(map); 
		
		//자료실 목록 ( data list : 10개 record)
				
		//1. 전체 record 수를 조회  +  count 0 일때 경우 처리
		String menu_id = String.valueOf(map.get("menu_id"));
		int count      = pdsMapper.count(menu_id);
		
		PagingResonse<PdsVo> response = null;
		if(count<1) {
			response = new PagingResonse<> (Collections.emptyList(),null);
			//Collections.emptyList() 자료는 없는 빈 리스트를 채운다 new 를 한건데 자료가 없는 거지
		}
		
		//2.페이징을 위한 초기설정
		SearchVo searchVo = new SearchVo();
		int nowpage = Integer.parseInt(String.valueOf(map.get("nowpage")));
		searchVo.setPage(nowpage);  //현재 페이지 정보
		searchVo.setRecordSize(10); //페이지당 10개
		searchVo.setPageSize(10);   //뷰에 출력할 page 번호 수
		
		// Pagination 설정
		//Pagination pagination = new Pagination(count,searchVo);
		Pagination pagination = new Pagination(count,searchVo);
		searchVo.setPagination(pagination);		
		
		// 3. list를 위한 변수 menu_id,nowpage,offset,recordSize,
		//   조회를 위한 변수  title writer content
		int offset = searchVo.getOffset();
		int recordSize = searchVo.getRecordSize();
		
		// 참고 StringvalueOF를 이용한 값을 MAP 넣어 XML로 가면 인식하지 않는다
		

		map.put("search", map.get("search"));
		map.put("searchtext", map.get("searchtext"));

		map.put("offset", offset);
		map.put("recordSize", recordSize);
		
		//4. 10개씩 들고오는 select + 조회 값
		List <PdsVo> pdsList = pdsService.getPdsPagingList(map); 
		
		//5. 페이징 한것과 조회값 을 하나의 응답 값으로 넣어준다
		response = new PagingResonse<> (pdsList,pagination);
		System.out.println(response);
		
		ModelAndView mv =new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("response",response);
		mv.addObject("map",map);
		mv.addObject("searchVo",searchVo);
		mv.setViewName("pds/list");
		
		return mv;
	}
	
  @RequestMapping("/WriteForm")
  public ModelAndView writeForm(@RequestParam HashMap<String,Object> map) {
	  //메뉴 목록
      List <MenuVo> menuList =menuMapper.getMenuList();
	  
	  ModelAndView mv =new ModelAndView();
	  mv.addObject("menuList",menuList);
	  mv.addObject("map",map);
	  mv.setViewName("pds/write");
	  return mv;
  }
	
 //  @PutMapping("/Write") @PostMapping("/Write") 
  // map: menu_id,nowpage 
  // request: upload 파일
  // MultipartFile[] upload 몇개의 파일 (<-HttpServletRequest)
  @RequestMapping("/Write")
  public ModelAndView write(@RequestParam HashMap<String,Object> map, 
		                    @RequestParam(value="upfile") MultipartFile[] uploadfiles) {
	
	  System.out.println("map:"+map);
	  System.out.println("uploadfiles:"+uploadfiles);
	  
	  pdsService.serWrite(map,uploadfiles);	  
	  
	  ModelAndView mv= new ModelAndView();
	  String fmt ="redirect:/Pds/List?menu_id=%s&nowpage=%d";
	  String loc =String.format(fmt, map.get("menu_id"),Integer.parseInt(String.valueOf(map.get("nowpage"))));
	  mv.setViewName(loc);
	  
	  return mv;
  }
  
}
