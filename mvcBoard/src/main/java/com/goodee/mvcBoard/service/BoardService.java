package com.goodee.mvcBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcBoard.mapper.BoardMapper;
import com.goodee.mvcBoard.mapper.BoardfileMapper;
import com.goodee.mvcBoard.vo.Board;
import com.goodee.mvcBoard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // 스프링 컨텍스트가 실행될 때 자동으로 빈으로 등록 -> DI, 트랜잭션 관리 등에 사용함.
@Transactional // 어디에든지 트랜잭션 처리가 가능하지만 서비스단에서 트랜잭션 처리를 진행하겠다.
public class BoardService {
	@Autowired // 관련 내용을 주입해 줘야 한다 -> null 에러 방지
	private BoardMapper boardMapper; 
	
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	// REST API chart 호출
	public List<Map<String, Object>> getLocalNameList() {
		return boardMapper.selectLocalNameList();
	}
	
	public int removeBoard(Board board) {
		return boardMapper.deleteBoard(board);
	}
	
	public int modifyBoard(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	public int addBoard(Board board, String path) {
		
		// 디버깅 
		log.debug(">> BoardService.addBoard.param path : "+path);
		log.debug(">> BoardService.addBoard.param board : "+board);
		
		int row = boardMapper.insertBoard(board); // 매퍼의 보트 삽입 메서드 실행 후 row값 반환
		
		// board입력이 성공하고 첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(row == 1 && fileList != null && fileList.size() >= 1 ) {
			
			log.debug(">> BoardService.addBoard.param : 첨부 파일이 있습니다.");
			
			int boardNo = board.getBoardNo(); // 매퍼보다 먼저 실행시 기본값 0이 입력된다.
			
			log.debug(">> BoardService.addBoard.param boardNo : "+boardNo);
			
			for(MultipartFile mf : fileList) { // 첨부된 파일의 개수만큼 반복, DB단에서 확장자만 따로 저장할 공간 저장
				if(mf.getSize() > 0) {
					Boardfile bf = new Boardfile();
					bf.setBoardNo(boardNo); // 부모키값
					bf.setOriginFilename(mf.getOriginalFilename()); // 원본파일이름
					bf.setFilesize(mf.getSize()); // 파일 사이즈
					bf.setFiletype(mf.getContentType()); // 파일 타입(MIME)
					// 저장될 파일 이름 = 새로운 이름(UUID) + 확장자
					// 확장자
					String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
					bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
					
					log.debug(">> BoardService.addBoard.param bf : "+bf);
					
					// 테이블에 저장
					boardfileMapper.insertBoardfile(bf);
					// 파일 저장(저장위치필요 -> path변수) -> 정적(서버가 변경되지 않을 경우 유용함)경로 OR 동적 경로 방법
					File f = new File(path+bf.getSaveFilename()); // path위치에 저장파일이름으로 빈파일을 생성
					// 빈파일에 첨부된 파일의 스트림을 주입
					// throws로 던질경우 controller로 예외가 넘어가게된다, 트랜잭션이 동작하지 않음 그래서 try-catch문 사용 
					try {
						mf.transferTo(f);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
						
						// 트랜잭션 작동을 위해 예외(try-catch 강요하지 않는 예외 ex: RuntimeException) 발생이 필요
						throw new RuntimeException();
					}
				}
			}
		}
		return row;
	}
	
	public List<Boardfile> getBoardfileOne(Board board) {
		
		return boardfileMapper.selectBoardfileOne(board);
	}
	
	public Board getBoardOne(int boardNo) {
		
		return boardMapper.selectBoardOne(boardNo);
	}
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName) {
		
		// service layer 역할1 : controller가 넘겨준 매개값을 dao의 매개갑에 맞게 가공
		int beginRow = (currentPage-1)*rowPerPage;
		// 반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		int boardCount = boardMapper.selectBoardCount();
		// service layer 역할2 : dao에서 반환받은 값을 가공하여 controller에 반환
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage = lastPage + 1;
		}
		
		// 맵으로 개별적인 값들을 담기
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);
		
		return resultMap;
		// return localNameList, localNameList; 파이썬에서 가능한 방식
		
	}
}
