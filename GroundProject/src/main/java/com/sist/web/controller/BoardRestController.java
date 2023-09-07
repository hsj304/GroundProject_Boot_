package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@RequestMapping("board/")
@CrossOrigin("http://localhost:3000")
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("list")
	public List<BoardEntity> list(int curpage){
		int rowsize=10;
		int start=rowsize*curpage-rowsize;
		List<BoardEntity> list=dao.boardListData(start);
		
		for(BoardEntity vo:list) {
			vo.setRegdate(vo.getRegdate().split(" ")[0]);
		}
		
		return list;
	}
	 
	@GetMapping("insert")
	public void insert(BoardEntity vo) {
		dao.save(vo);
		
	}
	
	@GetMapping("update")
	public String update(BoardEntity vo) {
		BoardEntity tvo=dao.findByNo(vo.getNo());
		
		if(vo.getPwd().equals(tvo.getPwd())) {
			dao.save(vo);
			return "YES";
		} else {
			return "NO";
		}
	}
	
	@GetMapping("detail")
	public BoardEntity detail(int no) {
		BoardEntity vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		
		vo.setRegdate(vo.getRegdate().split(" ")[0]);
		
		
		return vo;
	}
	
	@GetMapping("delete")
	public void delete(int no) {
		BoardEntity vo=dao.findByNo(no);
		dao.delete(vo);
	}
}
