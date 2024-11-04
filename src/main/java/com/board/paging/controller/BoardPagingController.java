package com.board.paging.controller;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.board.mapper.BoardMapper;
import com.board.board.vo.BoardVo;
import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;
import com.board.paging.mapper.BoardPagingMapper;
import com.board.paging.vo.Pagination;
import com.board.paging.vo.PagingResonse;
import com.board.paging.vo.SearchVo;
import com.board.users.vo.UserVo;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private BoardPagingMapper boardPagingMapper;
	
	@RequestMapping("/List")
	public ModelAndView list(int nowpage, BoardVo boardVo,HttpSession session) {
		
		//List 가져오기 // 제목 줄을 위해서
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		
		//게시물 목록 조회(페이징해서)
		//해당하는 자료수가 1보다 작으면
		//응답 데이터에 비어있는 리스트와 null을 담아 리턴
		//count : boardVo 안에 menu_id 에 해당하는 총자료수
		
		int count = boardPagingMapper.count(boardVo);
		System.out.println(count);  //TRC
		
		//자기 계정 글만 보기
		UserVo login = (UserVo) session.getAttribute("login");
		String writer  =login.getUserid();
		
		boardVo.setWriter(login.getUserid());
		int countt = boardPagingMapper.ccount(boardVo);
		//
		
		PagingResonse<BoardVo> response = null;
		if(count<1) {
			response = new PagingResonse<> (Collections.emptyList(),null);
			//Collections.emptyList() 자료는 없는 빈 리스트를 채운다 new 를 한건데 자료가 없는 거지
		}
	
				
		//페이징을 위한 초기설정
		SearchVo searchVo = new SearchVo();
		searchVo.setPage(nowpage);  //현재 페이지 정보
		searchVo.setRecordSize(10); //페이지당 10개
		searchVo.setPageSize(10);   //뷰에 출력할 page 번호 수
		
		// Pagination 설정
		//Pagination pagination = new Pagination(count,searchVo);
		Pagination pagination = new Pagination(countt,searchVo);
		searchVo.setPagination(pagination);
		
		
		
		
		//----------------------------------------------------------------------------------------------
		String menu_id = boardVo.getMenu_id();
		
		//추가된 조회 옵션들 (null 상태)
		String title   =boardVo.getTitle();
		String content =boardVo.getContent();
		//  String writer
		
		//추가된 조회 옵션들
		int offset     =searchVo.getOffset();
		int recordSize =searchVo.getRecordSize();
		
	
		
		//해당 page에 결과물을 10개식 들고 오는 list
		List <BoardVo> list = boardPagingMapper.getBoardPagingList( menu_id,title,writer,content,offset,recordSize);
		
		
		response = new PagingResonse<> (list,pagination);
		System.out.println(response);
		
		//임시 로그인 처리 - 로그인 intercepter
	
		
		 
		ModelAndView mv = new ModelAndView();
		mv.addObject("response",response);
		mv.addObject("menuList",menuList);
		
		mv.addObject("menu_id",menu_id);
		mv.addObject("nowpage",nowpage);
		mv.addObject("searchVo",searchVo);
		
		mv.setViewName("boardpaging/list");

		return mv;
	}
    //게시글 조회 - 조회수 증가
	@RequestMapping("/View")
	public ModelAndView view(int nowpage, BoardVo boardVo) {
		//상단 메뉴 목록 조회
		List <MenuVo> menuList = menuMapper.getMenuList();
		String menu_id =boardVo.getMenu_id();
		
		MenuVo vvo = menuMapper.getMenu(menu_id);
		String menu_name = vvo.getMenu_name();

		//조회수 증가
		boardPagingMapper.incHit(boardVo);
		
		//idx로 게시글 조회
		BoardVo vo = boardPagingMapper.getBoard(boardVo);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("vo",vo);
		mv.addObject("nowpage",nowpage);
		mv.addObject("menu_name",menu_name);
		mv.setViewName("boardpaging/view");
		return mv;
	}
	
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(int nowpage, String menu_id) {
		
		List <MenuVo> menuList = menuMapper.getMenuList();
		MenuVo vo = menuMapper.getMenu(menu_id);
		String menu_name = vo.getMenu_name();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/write");
		mv.addObject("nowpage",nowpage);
		mv.addObject("menu_name",menu_name);
		mv.addObject("menu_id",menu_id);
		mv.addObject("menuList",menuList);
		return mv;
	}
	
	@RequestMapping("/Write")
	public ModelAndView write(int nowpage, BoardVo boardVo) {
		
	
		
		boardPagingMapper.insertMapper(boardVo);
		String menu_id = boardVo.getMenu_id();
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/BoardPaging/List?menu_id="+menu_id+"&nowpage="+nowpage);
		return mv;
	}
	//UpdateForm
	@RequestMapping("/UpdateForm")
	public ModelAndView updateFrom(int nowpage,BoardVo boardVo) {
		List <MenuVo> menuList = menuMapper.getMenuList();
		BoardVo vo = boardPagingMapper.getBoard(boardVo);
		
		
		MenuVo menu = menuMapper.getMenu(boardVo.getMenu_id());
		String menu_name = menu.getMenu_name();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("boardpaging/update");
		mv.addObject("vo",vo);
		mv.addObject("nowpage",nowpage);
		mv.addObject("menu_name",menu_name);
		mv.addObject("menuList",menuList);
		
		return mv;
	}
	//http://localhost:9090/BoardPaging/Update
	// IDX(고유번호 where에 적합) 
	//MENU_ID(이걸 바꾸면 게시판 이동을 의미)  //NOWPAGE   ==>  이 두개는 list 이동을 위한 칼럼
	//TITLE CONTENT (업데이트 필요한 것)
		
	@RequestMapping("/Update")
	public ModelAndView update(int nowpage, BoardVo boardVo) {
		
		String menu_id =boardVo.getMenu_id() ;
		int idx =boardVo.getIdx();
		
		boardPagingMapper.updateMapper(boardVo);
		
		ModelAndView mv = new ModelAndView();
		String fmt = "redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String loc = String.format(fmt, menu_id, nowpage);
		mv.setViewName(loc);
		
		//mv.setViewName("redirect:/BoardPaging/View?menu_id="+menu_id+"&nowpage="+nowpage+"&idx="+idx);
		return mv;
		
	}
	@RequestMapping("/Delete")
	public ModelAndView delete (int nowpage, BoardVo boardVo) {
		
		
		ModelAndView mv = new ModelAndView();
		String menu_id =boardVo.getMenu_id();
		boardPagingMapper.deleteMaper(boardVo);
		
		
		//String loc = MessageFormat.format(fmt, null);
		
		
		mv.setViewName("redirect:/BoardPaging/List?menu_id="+menu_id+"&nowpage="+nowpage);
		
		return mv;
		
	}
	
	
}
