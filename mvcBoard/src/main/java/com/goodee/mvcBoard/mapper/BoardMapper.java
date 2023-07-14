package com.goodee.mvcBoard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcBoard.vo.Board;

@Mapper // 1. SQL 쿼리와 자바 메소드를 연결 2. 스프링 컴파일 시점에 Bean 등록 -> 필요로 할 때 언제든지 가져다가 사용
public interface BoardMapper {
	
	int updateBoard(Board board);
	
	int deleteBoard(Board board);
	
	int insertBoard(Board board);
	
	Board selectBoardOne(int boardNo); // 게시판 상세보기
	
	// local_name컬럼과 count() 반환
	List<Map<String, Object>> selectLocalNameList(); // 구현부가 없다 -> 추상메서드, 아무나 가져다 쓸수 있음 -> public 생략
	
	// mybatis 메서드는 매개값을 하나만 허용
	// param : Map<String, Object> map -> int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object> map);
	
	// 전체 행의 수
	int selectBoardCount();
}
