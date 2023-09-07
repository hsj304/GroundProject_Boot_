package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@RequestMapping("ground/")
@CrossOrigin("http://localhost:3000")
public class GroundRestController {
	@Autowired
	private GroundDAO dao;
	
	@GetMapping("ground_hit")
	public List<GroundEntity> ground_hit() {
		List<GroundEntity> list=dao.groundHitData();
		
		for(GroundEntity vo:list) {
			String image=vo.getGimage();
			if(image.contains("^")) {
				image=image.substring(0,image.indexOf("^"));
				vo.setGimage(image);
			}
		}
		
		return list;
	}
	@GetMapping("ground_rand")
	public List<GroundEntity> ground_rand(){
		List<GroundEntity> list=dao.groundRandData();
		
		for(GroundEntity vo:list) {
			String image=vo.getGimage();
			if(image.contains("^")) {
				image=image.substring(0,image.indexOf("^"));
				vo.setGimage(image);
			}
		}
		
		return list;
	}
	
	@GetMapping("ground_list")
	public List<GroundEntity> ground_list(String fd, int curpage){
		
		int rowsize=9;
		int start=rowsize*curpage-rowsize;
		
		List<GroundEntity> list=dao.groundListData(fd,start);
		
		for(GroundEntity vo:list) {
			String image=vo.getGimage();
			if(image.contains("^")) {
				image=image.substring(0,image.indexOf("^"));
				vo.setGimage(image);
			}
		}
		
		return list;
	}
	
	@GetMapping("ground_total")
	public PageVO ground_total(String fd, int curpage) {
		int totalpage=dao.groundTotalPage(fd);
		final int BLOCK=5;
		int startpage=(curpage-1)/BLOCK*BLOCK+1;
		int endpage=(curpage-1)/BLOCK*BLOCK+BLOCK;
		if(endpage>totalpage) {
			endpage=totalpage;
		}
		
		PageVO vo=new PageVO();
		vo.setCurpage(curpage);
		vo.setTotalpage(totalpage);
		vo.setStartpage(startpage);
		vo.setEndpage(endpage);
		
		return vo;
		
	}
	
	@GetMapping("ground_detail")
	public GroundEntity ground_detail(int gno) {
		
		GroundEntity vo=dao.findByGno(gno);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		
		String image=vo.getGimage();
		if(image.contains("^")) {
			image=image.substring(0,image.indexOf("^"));
			vo.setGimage(image);
		}
		
		return vo;
	}
}
