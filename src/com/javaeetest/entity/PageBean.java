package com.javaeetest.entity;

import java.util.List;

public class PageBean<T> {

	private List<T> list; // 通过hql从数据库分页从查询出来的list集合
	private int allRows; // 总记录数
	private int totalPage; // 总页数
	private int currentPage; // 当前页
	
	/**
	 * 得到总页数
	 * @param pageSize 每页放的记录数
	 * @param allRows 总记录数
	 * @return 总页数
	 */
	public int getTotalPages(int pageSize,int allRows){
		int totalPage = (allRows % pageSize == 0) ? (allRows / pageSize) : (allRows / pageSize)+1;
		return totalPage;
	}
	
	/**
	 * 得到当前开始记录号
	 * @param pageSize 每页放的记录数
	 * @param currentPage 当前页
	 * @return 当前开始记录号
	 */
	public int getCurrentPageOffset(int pageSize,int currentPage){
		int offset = pageSize * (currentPage - 1);
		return offset;
	}
	
	/**
	 * 得到当前页，如果为0，则开始第一页，否则为当前页
	 * @param page
	 * @return
	 */
	public int getCurPage(int page){
        int currentPage;
        if(page == 0){
        	currentPage = 1;
        }else{
        	currentPage = page;
        }
        return currentPage;

	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getAllRows() {
		return allRows;
	}

	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
