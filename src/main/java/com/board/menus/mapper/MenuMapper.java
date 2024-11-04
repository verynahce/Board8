package com.board.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.menus.vo.MenuVo;

import lombok.NonNull;

@Mapper
public interface MenuMapper {

	List<MenuVo> getMenuList();

     void insertMenu(MenuVo menuVo);

	void insertMenu1(MenuVo vo);

	void deleteMenu(MenuVo vo);

	MenuVo getMenu(@NonNull String menu_id);

	void updateMenu(MenuVo vo);

	
}



