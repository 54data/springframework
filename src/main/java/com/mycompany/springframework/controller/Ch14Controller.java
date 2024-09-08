package com.mycompany.springframework.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springframework.aspect.LoginCheckAOP;
import com.mycompany.springframework.aspect.RuntimeCheck;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch14")
public class Ch14Controller {
	@RequestMapping("/before")
	public String before() {
		log.info("실행");
		return "redirect:/";
	}
	
	@RequestMapping("/after")
	public String after() {
		log.info("실행");
		return "redirect:/";
	}
	
	@RequestMapping("/afterReturning")
	public String afterReturning() {
		log.info("실행");
		return "redirect:/";
	}
	
	@RequestMapping("/afterThrowing")
	public String afterThrowing() {
		log.info("실행");
		boolean result = true;
		if (result) {
			throw new RuntimeException("예외가 발생!");
		}
		return "redirect:/";
	}
	
	@RequestMapping("/around")
	public String around() {
		log.info("실행");
		return "redirect:/";
	}
	
	@Autowired
	private Ch13BoardService boardService;
	
	@RuntimeCheck
	@GetMapping("/boardList")
	public String boardList(
			Model model, 
			@RequestParam(defaultValue="1") int pageNo,
			HttpSession session
		) {
		model.addAttribute("chNum", "ch13");	
		int totalRows = boardService.getTotalRows(); // 데이터 베이스에서 카운팅해서 가져와야 함
		// pageNo - 사용자가 클릭한 번호를 브라우저에서 받아야 함
		Ch13Pager pager = new Ch13Pager(7, 5, totalRows, pageNo);
		session.setAttribute("pager", pager); // model 저장은 request 범위 내에 있음
		
		List<Ch13Board> list = boardService.getBoardList(pager);
		model.addAttribute("list", list); // boardList.jsp에서 사용하기 위함
		return "ch13/boardList";
	}
	
	@LoginCheckAOP
	@GetMapping("/writeBoardForm") // 게시물 쓰기 폼 요청
	public String writeBoardForm(Model model) {
		return "redirect:/ch13/writeBoardForm";
	}
}