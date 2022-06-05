package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.net.URLEncoder;

/**
 * @packageName		: com.hyundai.project.dto
 * @fileName		: UploadResultDTO
 * @author			: 고정민
 * @description		: 파일 업로드 시 이미지 경로 인코딩하는 기능 구현
**/

@Data
@AllArgsConstructor // 인터페이스 구현 JSON변환 때문에
public class UploadResultDTO implements Serializable {
	private String fileName;
	private String uuid;
	private String folderPath;

	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return "URL fail";
	}// end get..

	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(folderPath + "/" + "s_" + uuid + "_" + fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} // end try
		return "Thumb fail";
	}// end getTh.

}// end class
