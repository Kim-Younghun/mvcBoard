package com.goodee.mvcBoard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
// @SessionAttributes(name= "로그인정보키")
public class LoginController {
	@PostMapping("/login")
	public String login(// Model model 
						HttpSession session,
						@RequestParam(name="memberId") String memberId,
						@RequestParam(name="memberPw") String memberPw) { // (name="memberId") 두 명이 같을 경우 생략가능
			// service(memberId, memberPw) -> mapper -> 로그인 성공 유무 반환
		
			// 로그인 성공시
			// model.addAttribute(,);
		
			session.setAttribute("", memberId); // 로그인 정보저장
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate(); // 세션 삭제
		
		return "redirect:/";
	}
}
