package com.hyundai.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: CriteriaDTO
 * @author			: 고정민
 * @description		: Board 페이징 시 필요한 정보를 저장하는 객체
**/

@Getter
@Setter
@ToString
public class CriteriaDTO {
	
	private int pageNum;
	private int amount;
	
	public CriteriaDTO() {
		this(1,10);
	}
	
	public CriteriaDTO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
