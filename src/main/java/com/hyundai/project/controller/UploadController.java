package com.hyundai.project.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.service.BoardService;

import lombok.extern.log4j.Log4j2;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: UploadController
 * @author			: 고정민
 * @description		: 파일첨부 기능 구현하는 컨트롤러
**/

@RestController
@Log4j2
public class UploadController {
	// spring 어노테이션
	// application.properties 변수
	@Value("${com.hyundai.upload.path}")
	private String uploadPath;
	
	@Autowired
	private BoardService BoardService;

	// 폴더 이름 만드는 함수
	private String makeFolder() {
		// 오늘 날자 생성패턴 대소문자 구분 주의
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// 날짜 구분
		String folderPath = str.replace("/", File.separator);
		// 폴더 생성
		File uploadPathFolder = new File(uploadPath, folderPath);
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		} // end if
		log.info(folderPath);
		return folderPath;
	}// end make..

}// end class
