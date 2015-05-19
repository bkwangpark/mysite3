package com.sds.icto.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.GuestBookVo;
import com.sds.icto.mysite.repository.GuestBookDao;

@Service
public class GuestBookService {
	@Autowired
	GuestBookDao guestbookDao;

	public List<GuestBookVo> fetchList(){
		return guestbookDao.fetchList();
	}
	
	public void insertUser(GuestBookVo vo){
		guestbookDao.insert(vo);
	}

	public void deleteContent(GuestBookVo vo){
		guestbookDao.delete(vo);
	}
}
