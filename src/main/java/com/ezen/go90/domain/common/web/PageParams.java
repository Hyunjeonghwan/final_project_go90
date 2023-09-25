package com.ezen.go90.domain.common.web;

import lombok.Builder;

/**
 * 
 * 페이지 계산에 필요한 정보들 포장
 *
 * @Project final_project_go90
 * @Author 장원종, 김지연
 * @Date 2023. 9. 15.
 */
@Builder
public class PageParams {
	
	private int elementSize;    /** 페이지에 보여지는 목록 갯수 */
	private int pageSize;       /** 페이지에 보여지는 페이지 갯수 */
	private int requestPage;    /** 사용자 요청 페이지 */
	private int rowCount;       /** 테이블 목록 갯수 */
	private int boardId;
	private String input;
	private String type;
	
	public PageParams() {
		this(10, 10, 1, 0, 10, null, null);
	}
	
	public PageParams(int elementSize, int pageSize, int requestPage, int rowCount, int boardId, String input, String type) {
		this.elementSize = elementSize;
		this.pageSize = pageSize;
		this.requestPage = requestPage;
		this.rowCount = rowCount;
		this.boardId = boardId;
		this.input = input;
		this.type = type;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getElementSize() {
		return elementSize;
	}

	public void setElementSize(int elementSize) {
		this.elementSize = elementSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRequestPage() {
		return requestPage;
	}

	public void setRequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	@Override
	public String toString() {
		return "PageParams [elementSize=" + elementSize + ", pageSize=" + pageSize + ", requestPage=" + requestPage
				+ ", rowCount=" + rowCount + ", boardId=" + boardId + ", input=" + input + ", type=" + type + "]";
	}

	
	
	
}
