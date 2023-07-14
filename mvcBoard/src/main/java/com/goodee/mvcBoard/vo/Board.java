package com.goodee.mvcBoard.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String localName;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private String createdate;
	private String updatedate;
	
	// table속성은 아니고 입력폼 속성 -> BoardForm.class(DTO), Board.class(도메인) 분리해서 사용가능
	private List<MultipartFile> multipartFile; // List 사용시 다수의 배열로 받음, 아닐경우 단일 파일로 받는다.
}
