package com.sds.icto.mysite.domain;

public class BoardVo {
	private Long no;
	private String title;
	private String context;
	private Long mem_no;
	private String mem_name;
	private Long view_cnt;
	private String date;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Long getMem_no() {
		return mem_no;
	}
	public void setMem_no(Long mem_no) {
		this.mem_no = mem_no;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(Long view_cnt) {
		this.view_cnt = view_cnt;
	}
	
}
