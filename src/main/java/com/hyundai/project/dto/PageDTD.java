package com.hyundai.project.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: BoardDTO
 * @author			: 고정민
 * @description		: Board 테이블의 데이터를 매핑하기 위한 객체
**/

@Getter
@ToString
public class PageDTD {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private CriteriaDTO cri;
	
	public PageDTD(CriteriaDTO cri, int total) {
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0)) * 10;
		
		this.startPage = this.endPage - 9;
		
		int realEnd = (int) Math.ceil((total * 1.0) / cri.getAmount());
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = (this.startPage > 1);
		this.next = (this.endPage < realEnd);
	}
}
