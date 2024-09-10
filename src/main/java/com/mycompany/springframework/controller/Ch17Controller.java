package com.mycompany.springframework.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.security.Ch17UserDetails;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch17")
public class Ch17Controller {
	@RequestMapping("loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch17");
		return "ch17/loginForm";
	}
	
	@GetMapping("/authorityCheck")
	public String authoriyCheck(Model model) {
		model.addAttribute("chNum", "ch17");
		return "ch17/authorityCheck";
	}
	
	@GetMapping("/userInfo")
	public String userInfo(Model model, Authentication authentication) {
		model.addAttribute("chNum", "ch17");
		
		// 사용자의 id만 얻고 싶을 경우
		// 방법 1
		String mid = authentication.getName();
		
		// 방법 2
		// Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		// String mid = userDetails.getUsername();
		
		model.addAttribute("mid", mid);
		
		// 사용자의 모든 정보를 얻고 싶을 경우
		Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		Ch13Member member = userDetails.getMember();
		log.info(member.toString());
		model.addAttribute("member", member);
		
		return "ch17/userInfo";
	}
}
