package com.sds.icto.mysite.exception;

public class GuestBookDaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public GuestBookDaoException(){
		super("GuestBook Dao Exception");
	}

	public GuestBookDaoException(String msg){
		super(msg);
	}
}
