package com.board.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;
import com.board.pds.vo.FilesVo;
import com.board.pds.vo.PdsVo;

@Service
public class PdsServiceimpl implements PdsService {
     
	//application.properties의 part4.upload-path 정보 가져오기
	@Value("${part4.upload-path}")
	private String uploadPath;
	
	
	@Autowired
	 private PdsMapper pdsMapper;
	
	
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {		 
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);		  
		return pdsList;
	}


	@Override
	public void serWrite(HashMap<String, Object> map, MultipartFile[] uploadfiles) {

		  //저장
		  //1. map 대한 정보 처리 (새글 추가 ->db : Board table 저장)
		  //2. MultipartFile[] 정보저장
		  //2-1. 실제폴더에 파일 저장 (중복파일처리)       -> uploadPath(d:\dev\data\)
		  //2-2. 저장된 파일정보를 db에 저장 -> files table에
		
		//파일저장 + 자료실 글 쓰기
		//1.파일저장
		//uploadfiles[] -> d:dev\data\
		//String uploadPath = "d:dev\\data\\";

		map.put("uploadPath", uploadPath );
		System.out.println(uploadPath);
		//pdsFile class(별도 생성)
		PdsFile.save(map,uploadfiles);
		
		
		//2. 넘어온 값 콘솔에 나타내보기
		
		
		//3.board db에 저장, files db 저장
		pdsMapper.setWriter(map);
		
		//4.Files db 저장
		List<FilesVo> fileList = (List<FilesVo>)map.get("fileList");
		if(fileList.size()>0)
		pdsMapper.setFileWriter(map);
		

		
	}


	@Override
	public List<PdsVo> getPdsPagingList(HashMap<String, Object> map) {

        List <PdsVo> pdsList = pdsMapper.getPdsList(map);
		return pdsList;
	}
	
	

}
