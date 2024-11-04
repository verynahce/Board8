package com.board.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.users.mapper.UserMapper;
import com.board.users.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Users")
public class UserController {
     
	@Autowired
	private UserMapper userMapper;
	
	///Users/WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("users/write");
		
		
		return mv;
	}
	
	///Users/Write
	@RequestMapping("/Write")
	public ModelAndView writeForm(UserVo vo) {
		//write.jsp가 넘겨준 데이터를 저장
		userMapper.insertUser(vo);
		
		//list.jsp로 이동
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/Users/List");
		return mv;
	}
	
	//사용자 목록
	//http://localhost:9090/Users/List
	@RequestMapping("/List")
	public ModelAndView list() {
		
		List <UserVo> userList = userMapper.getUserMapper();
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.setViewName("users/list");
		
		
		return mv;
	}
	
	//http://localhost:9090/Users/Delete
	
	@RequestMapping("/Delete")
	public ModelAndView delete(UserVo vo) {
		
		userMapper.deleteUser(vo);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
	}
	
	//http://localhost:9090/Users/UpdateForm
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm(UserVo vo) {
		ModelAndView mv = new ModelAndView();
		
	UserVo  uservo =	userMapper.getUser(vo);
		mv.addObject("uservo",uservo);
		mv.setViewName("users/update");
		return mv;
		
		
	}
	
	//http://localhost:9090/Users/UPdate
	@RequestMapping("/Update")
	public ModelAndView update(UserVo vo) {
		
		//DB 수정
		userMapper.updateUser(vo);
		
		// 목록 조회 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
	}
//----------------------------------------------
	//새창 방식 호출
	// /Users/DupCheck?userid=?
	@RequestMapping("/DupCheck")
	public ModelAndView dupCheck(UserVo vo) {
		
		//중복 확인을 위한 조회
		UserVo userVo = userMapper.getUser(vo);
		
		//결과를 출력할 jsp를 리턴
		ModelAndView mv = new ModelAndView();
		mv.addObject("userVo",userVo);
		mv.setViewName("users/dupcheck");
		return mv;
		
	}
	
	//아이디 중복확인/Users/IdDuoCheck
	// 비동기 호출로 구현 AJAX
	//Users/IdDupCheck?userid=aaaa
	//responsebody는 jsp파일이 아닌 결과문자열(json)을 리턴한다
	@RequestMapping(value="/IdDupCheck", 
			method=RequestMethod.GET, 
			headers ="Accept=application/json")
	@ResponseBody   //json문자열을 리턴하겠다
	public UserVo idDupCheck(String userid) {
			
		String result =""; //조회결과
		//db조회
		UserVo userVo = userMapper.idDupCheck(userid);

		return userVo;
		
}
//---------------------------------	
	//LOGIN
	
	//Users/LoginForm
	@GetMapping("/LoginForm")
	public String loginForm(String uri, String menu_id, int nowpage, Model model) {

		model.addAttribute("uri",uri);
		model.addAttribute("menu_id",menu_id);
		model.addAttribute("nowpage",nowpage);
		return "users/login";
	}
	
	
	//Users/Login
	@PostMapping("/Login")
	public String login(HttpServletRequest request, HttpServletResponse responese) {
       
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String uri = request.getParameter("uri");
		String menu_id = request.getParameter("menu_id");
		String nowpage = request.getParameter("nowpage");
		
		//db조회
		UserVo vo = userMapper.login(userid,passwd);
		
		HttpSession session = request.getSession();
		session.setAttribute("login", vo);
		

		return "redirect:/"+uri+
				"/List?menu_id="+menu_id+
				"&nowpage="+ nowpage;
	}
	
	///Users/Logout
	@RequestMapping( value="/Logout", method =RequestMethod.GET )
	public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		Object url = session.getAttribute("URL");
		session.invalidate();
		
		//return "redirect:" + (String) url;
		 return "redirect:/";
	}

	
	
	
	
	
	
	
}	
