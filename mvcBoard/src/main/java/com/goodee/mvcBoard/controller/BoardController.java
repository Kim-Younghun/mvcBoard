package com.goodee.mvcBoard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcBoard.service.BoardService;
import com.goodee.mvcBoard.vo.Board;
import com.goodee.mvcBoard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/*
	    ANSI_RESET = "\u001B[0m";
	    ANSI_BLACK = "\u001B[30m";
	    ANSI_RED = "\u001B[31m";
	    ANSI_GREEN = "\u001B[32m";
	    ANSI_YELLOW = "\u001B[33m";
	    ANSI_BLUE = "\u001B[34m";
	    ANSI_PURPLE = "\u001B[35m";
	    ANSI_CYAN = "\u001B[36m";
	    ANSI_WHITE = "\u001B[37m";
	    ANSI_BLACK_BACKGROUND = "\u001B[40m";
	    ANSI_RED_BACKGROUND = "\u001B[41m";
	    ANSI_GREEN_BACKGROUND = "\u001B[42m";
	    ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	    ANSI_BLUE_BACKGROUND = "\u001B[44m";
	    ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	    ANSI_CYAN_BACKGROUND = "\u001B[46m";
	    ANSI_WHITE_BACKGROUND = "\u001B[47m";
    */
	
	@GetMapping("/board/removeBoard")
	public String removeBoard() {
		return "/board/removeBoard";
	}
	
	@PostMapping("/board/removeBoard")
	public String removeBoard(@ModelAttribute Board board) { // @ModelAttribute를 통한 자동 바인딩
		int row = boardService.removeBoard(board);
		log.debug("\u001B[31m"+"row: "+row+"\u001B[0m");
		// return "/board/boardList"; // 보드리스트로 포워딩
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/modifyBoard")
	public String modifyBoard() {
		return "/board/modifyBoard";
	}
	
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(@ModelAttribute Board board) {
		int row = boardService.modifyBoard(board);
		log.debug("\u001B[31m"+"row: "+row+"\u001B[0m");
		// return "/board/boardList"; // 보드리스트로 포워딩
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request, Board board) { // 매개값 request객체를 받음 <- request api 직접호출
		String path = request.getServletContext().getRealPath("/upload/"); // 경로 입력
		int row = boardService.addBoard(board, path); // 서비스단의 addBoard 메서드 실행후 row값 반환
		log.debug("\u001B[31m"+"row: "+row+"\u001B[0m");
		
		
		// return "/board/boardList"; // 보드리스트로 포워딩
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardOne")
	public String getBoardOne(Model model,
								@RequestParam(name="boardNo") int boardNo) {
		
		log.debug("\u001B[31m"+"boardController: "+boardNo+"\u001B[0m"); // 디버깅
		
		Board board = boardService.getBoardOne(boardNo);// service를 통해 검색어 매개값 적용
		
		List<Boardfile> boardfile = boardService.getBoardfileOne(board);
		
		model.addAttribute("board", board); // 컨트롤러가 처리한 결과 데이터나 상태 등 필요한 정보들을
											// 담아 보낼 목적으로 Model을 설정
		model.addAttribute("boardfile", boardfile);
		
		return "/board/boardOne";
	}
	
	@GetMapping("/board/boardList")
	// 조건문을 통한 null 처리 및 형변환을 자동으로 실행해 주는 spring 프레임워크
	public String boardList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(name = "localName", required = false) String localName) { // 넘어온 값이 없을경우 에러가 뜨지 않도록, required = false 사용하여 null일 경우 쿼리 조건문 수정
		
		log.debug("\u001B[31m"+"localName: "+localName+"\u001B[0m");
		/*
		List<Map<String, Object>> localNameList = boardService.getBoardList();
		model.addAttribute("localNameList", localNameList); // request.setAttribute()와 비슷한 역할
		*/
		
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName);
		
		// view로 넘길때는다시 분리해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		model.addAttribute("currentPage", currentPage); // 클라이언트 단으로부터 전달되는 값
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		
		return "/board/boardList"; // /board 폴더 안에 boardList를 만든다.
	}
	
}
