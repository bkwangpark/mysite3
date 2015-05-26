package com.sds.icto.mysite.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(BoardVo vo) {
		sqlMapClientTemplate.insert("board.insert", vo);
	}

	public void update(BoardVo vo) {
		sqlMapClientTemplate.update("board.update", vo);
	}

	public void updateView(BoardVo vo) {
		sqlMapClientTemplate.update("board.updateView", vo);
	}

	public void delete(BoardVo vo) {
		sqlMapClientTemplate.delete("board.delete", vo);
	}

	@SuppressWarnings("unchecked")
	public List<BoardVo> findList(String key) {
		List<BoardVo> findlist = new ArrayList<BoardVo>();
		findlist = sqlMapClientTemplate.queryForList("board.findlist", key);
		return findlist;
	}

	@SuppressWarnings("unchecked")
	public List<BoardVo> fetchList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		list = (List<BoardVo>)sqlMapClientTemplate.queryForList("board.list");
		return list;
	}

	public BoardVo show(Long no) {
		BoardVo vo1 = new BoardVo();
		vo1 = (BoardVo) sqlMapClientTemplate.queryForObject("board.show", no);
		return vo1;
	}
}
