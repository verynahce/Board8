package com.board.paging.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingResonse<T> {
	//현재페이지에 보여줄 db 자료 : 10줄 짜리 record - select 결과물
	private List<T> list = new ArrayList<>();
	
	//아래의 paging.jsp 에서 사용할 변수들
	private Pagination pagination;

	public PagingResonse(List<T> list, Pagination pagination) {

		 this.list.addAll(list); 
		this.pagination = pagination;
	}
	
	
	
	
}
