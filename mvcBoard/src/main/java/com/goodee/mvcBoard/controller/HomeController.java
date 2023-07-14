package com.goodee.mvcBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // implement Servlet
public class HomeController {
	@GetMapping("/home") // web.xml의 url 패턴매핑 or 애노테이션 WebServlet 기능을 대신함.
	public String home() {
		return "home"; // RequestDispatcher.forward()를 대신함
	}
}
