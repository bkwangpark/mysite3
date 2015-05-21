package com.sds.icto.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.repository.BoardDao;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;

	public List<BoardVo> fetchList() {
		return boardDao.fetchList();
	}

	public void insertUser(BoardVo vo) {
		boardDao.insert(vo);
	}
	
	public BoardVo showContent(Long no){
		BoardVo vo = boardDao.show(no);
		return vo;
	}

	public void deleteContent(BoardVo vo) {
		boardDao.delete(vo);
	}
}
