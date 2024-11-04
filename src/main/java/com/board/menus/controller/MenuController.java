package com.board.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.menus.mapper.MenuMapper;
import com.board.menus.vo.MenuVo;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Menus")
public class MenuController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	
	// 메뉴 목록 조회  /Menus/List
	@RequestMapping("/List")   
	public   String   list( Model model ) {
		// 조회한결과를 ArrayList 로 돌려준디
		List<MenuVo> menuList = menuMapper.getMenuList();
		//System.out.println(menuList);	
		
		// 조회 결과를 넘겨준다 ( Model )
		model.addAttribute("msg", "haha" );
		model.addAttribute( "menuList", menuList );
		//System.out.println( "MenuController list() menuList:" + menuList );
		
		return "menus/list";
	}
	

	@RequestMapping("/WriteForm")
	public String writeForm() {
		return "menus/write";
	}

	//Menus/Write
	@RequestMapping("/Write")
	public String write(MenuVo menuVo, Model model) {
		//넘어오는 값 받아서 
		//db에 저장하고
      menuMapper.insertMenu(menuVo);
      
      /*
//list.jsp에 출력할 data를 조회하여 model에 담는다
      List<MenuVo> menuList = menuMapper.getMenuList(); 
      model.addAttribute("menuList",menuList);
		//목록보기 페이지로 이동
		return "menus/list";
		*/
      return "redirect:/Menus/List";
	
	}
	
	@RequestMapping("/WriteForm2")
	public String writeForm2() {
		
		return "menus/write2";
	}

	@RequestMapping("/Write2")
	public String write2(MenuVo vo, Model model) {

	menuMapper.insertMenu1(vo);	
		
		
		return "redirect:/Menus/List";
	}
	
	@RequestMapping("/Delete")
	public String delete(MenuVo vo) {
		
		menuMapper.deleteMenu(vo);
		
		return "redirect:/Menus/List";
	}
	
	//Menus/UpdateForm?menu_id=MENU01     //id만 받아오기때문에 select로 가서 다시 필요한 정보들을 받아서 model에 넘겨야함
	@RequestMapping("/UpdateForm")
	public String updateForm(MenuVo vo, Model model) {
		
		MenuVo menu = menuMapper.getMenu(vo.getMenu_id());
		
		model.addAttribute("menu",menu);
		
		return "menus/update";
		
	}

	
	// /Menus/Update
	@RequestMapping("/Update")
	public String update( MenuVo vo) {
		
		menuMapper.updateMenu(vo);
		
		return  "redirect:/Menus/List";
	}
	
	

	
	
}






