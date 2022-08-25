package com.topia.myapp.pagination;

public class Pagination {
	
	private int page;
	private int perPage;
	private int pageSize;
	private String keyword;
	private String searchType;
	private String criteria;
	
	private int totalCnt;
	private int totalPages;
	private int start;
	private int end;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	
	public Pagination(int totalCnt, int page, int perPage, int pageSize) {
		this.totalCnt = totalCnt;
		this.calculation(page, perPage, pageSize);
	}
	
	private void calculation(int page, int perPage, int pageSize) {
		
		if(page > this.totalCnt) {
			page = this.totalCnt;
		}
		
		this.totalPages = (int) Math.ceil(this.totalCnt / perPage);
		this.start = perPage * (page - 1) + 1;
		this.end = this.start + perPage - 1;
		if(this.totalCnt < this.end) {
			this.end = this.totalCnt;
		}
		this.startPage = pageSize * ((int) Math.floor((page / pageSize))) + 1;
		this.endPage = this.startPage + pageSize - 1;
		this.prev = this.startPage != 1;
		this.next = this.endPage < this.totalPages;
		
	}
	
}
