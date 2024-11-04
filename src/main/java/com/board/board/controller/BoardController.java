package com.board.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.board.mapper.BoardMapper;
import com.board.board.vo.BoardVo;
import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Board")
public class BoardController {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private BoardMapper boardMapper;
	
	
 // /Board/List
	@RequestMapping("/List")
	public ModelAndView list(MenuVo menuVo ){
		
		log.info("----------"+menuVo);
		//include 구하는 식 // 메뉴리스트 //아이디랑 네임 구해놓기!
		List<MenuVo> menuList = menuMapper.getMenuList();
		String menu_id = menuVo.getMenu_id();
		MenuVo  mVo = menuMapper.getMenu(menu_id);
		String menu_name = mVo.getMenu_name();
		
		//
       //개시판 리스트
		List<BoardVo> boardList = boardMapper.getBoardList(menu_id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", boardList);
		
		///
		mv.setViewName("board/list");
		mv.addObject("menuList", menuList);  //include용
		mv.addObject("menu_name", menu_name);
		mv.addObject("menu_id", menu_id);
		return mv;
	}
	
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(MenuVo menuVo) {
		
		List<MenuVo> menuList = menuMapper.getMenuList();
		MenuVo Vo = menuMapper.getMenu(menuVo.getMenu_id());
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList", menuList); 
		mv.setViewName("board/write");
		mv.addObject("menu_id",Vo.getMenu_id());
		mv.addObject("menu_name",Vo.getMenu_name());
		return mv;
	}
	
	// menu_id title writer content
	@RequestMapping("/Write")
	public ModelAndView write(BoardVo boardVo) {
		
		//메뉴목록조회
		
		//글쓰기
		boardMapper.insertMapper(boardVo);
		String menu_id = boardVo.getMenu_id();
		//목록조회
		ModelAndView mv= new ModelAndView();
		
		mv.setViewName("redirect:/Board/List?menu_id="+menu_id);
		return mv;    
	}
	
	//http://localhost:9090/Board/View?idx=
	@RequestMapping("/View")
	public ModelAndView view(BoardVo boardVo) {
        
		//조회수 증가 커리
		boardMapper.incHit(boardVo);

		
		//변수 만들기
		List<MenuVo> menuList =menuMapper.getMenuList();
		BoardVo bvo = boardMapper.getBoard(boardVo);
		
		
		String menu_id = bvo.getMenu_id();    //id 밖에 없는 상태
		MenuVo mvo = menuMapper.getMenu(menu_id);  // id name seq를  받아온 함수 
		String menu_name = mvo.getMenu_name(); // 여기서 name만 꺼낸다
		
		// 조회한 글의 content 부분의 '/n'-> '<br>로 바꿔줘야함 
		// 출력하는 곳이 태그 안이니까!
		String content = bvo.getContent();
		bvo.setContent(content.replace("\n", "<br>"));
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("menu_name",menu_name);
		mv.addObject("vo",bvo);
		mv.setViewName("board/view");
		return mv;
	}
	
	//   /http://localhost:9090/Board/Delete?idx=7&menu_id=MENU01
	@RequestMapping("/Delete")
	public ModelAndView delete(BoardVo boardVo) {
		
		//메뉴아이디 구하기 (idx 하나로 DB를 받아올래 // 아니면 처음부터 메뉴도 같이 받던가
	
		String menu_id = boardVo.getMenu_id();
		
		//삭제
		boardMapper.deleteBoard(boardVo);
		
		//목록으로 이동
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" +menu_id);
		return mv;
	}
	
     ///http://localhost:9090/Board/UpdateForm?idx=
	@RequestMapping("/UpdateForm")
	
	public ModelAndView updateForm(BoardVo boardVo) {
		List<MenuVo> menuList =menuMapper.getMenuList();
		BoardVo vo = boardMapper.getBoard(boardVo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		mv.setViewName("board/update");
		mv.addObject("menuList", menuList); 
		return mv;
	}
	
     //http://localhost:9090/Board/Update?idx=7&menu_id=MENU01
	@RequestMapping("/Update")
	public ModelAndView update(BoardVo boardVo) {
		
		int idx = boardVo.getIdx();
		boardMapper.updateBoard(boardVo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Board/View?idx=" + idx);
		return mv;
		
	}
	
	
	
	
}
