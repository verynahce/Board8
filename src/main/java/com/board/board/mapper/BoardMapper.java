package com.board.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.board.vo.BoardVo;
import com.board.menus.vo.MenuVo;

import lombok.NonNull;

@Mapper
public interface BoardMapper {


	void insertMapper(BoardVo boardVo);

	List<BoardVo> getBoardList(String menu_id);

	BoardVo getBoard(BoardVo boardVo);

	void incHit(BoardVo boardVo);

	void deleteBoard(BoardVo boardVo);

	void updateBoard(BoardVo boardVo);



 




}
