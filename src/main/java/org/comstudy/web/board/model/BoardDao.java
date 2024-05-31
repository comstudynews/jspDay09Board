package org.comstudy.web.board.model;

import java.util.List;

public interface BoardDao {

	List<BoardDto> selectAll();

	BoardDto findByNum(int num);

	void insert(BoardDto dto);

	void update(BoardDto dto);

	void delete(BoardDto dto);

}