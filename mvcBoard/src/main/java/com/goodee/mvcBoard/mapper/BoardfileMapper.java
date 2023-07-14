package com.goodee.mvcBoard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcBoard.vo.Board;
import com.goodee.mvcBoard.vo.Boardfile;
@Mapper
public interface BoardfileMapper {
	int insertBoardfile(Boardfile boardfile);
	
	List<Boardfile> selectBoardfileOne(Board board);
}
