package com.hyundai.project.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.project.dto.*;
import com.hyundai.project.service.BoardService;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

/**
 * @packageName		: com.hyundai.project.controller
 * @fileName		: BoardController
 * @author			: 고정민
 * @description		: 게시판 글 작성[파일첨부]/수정/조회/삭제/파일출력
**/

@Controller
@Log4j2
public class BoardController {
	// 첨부파일 업로드 경로
	@Value("${com.hyundai.upload.path}")
	private String uploadPath;

	@Autowired
	private BoardService BoardService;

	// 작성 페이지로 이동
	@GetMapping("insert")
	public String insert() {
		return "Board/insert";
	}// end insert

	// 전달받은 BoardDTO 객체와 MultipartFile 타입의 파일을 경로에 저장하여 service에 넘겨주도록 함
	@PostMapping("insert")
	public String insert(@ModelAttribute BoardDTO BoardDTO, Model model, MultipartFile uploadFiles) {
		log.info(BoardDTO.toString());
		try {
			log.info("uploadFiles : " + uploadFiles);
			// 첨부파일이 존재하면 경로 생성 후 파일 로컬 경로에 저장
			if (!uploadFiles.isEmpty()) {
				String originalName = uploadFiles.getOriginalFilename();
				log.info("fileName :" + originalName);
				// 폴더 생성
				String folderPath = makeFolder();
				log.info(folderPath);
				// uuid
				String uuid = UUID.randomUUID().toString();
				// 파일 이름 완성
				String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + originalName;
				log.info(saveName);
				// 경로 저장
				Path savePath = Paths.get(saveName);
				try { // 실제 저장
						// 원본 이미지 파일 저장
					uploadFiles.transferTo(savePath);
					String thumnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid
							+ "_" + originalName;
					File thumbailFile = new File(thumnailSaveName);
					// 섬네일 파일 생성 100 X 100 생성 input,output, 가로, 세로
					Thumbnailator.createThumbnail(savePath.toFile(), thumbailFile, 100, 100);
					// 각 파일 정보 리스트에 클래스로 저장
					// resultDTOList.add(new UploadResultDTO(originalName, uuid, folderPath));
					log.info("saveName" + saveName);
					String[] db_image = saveName.split(uploadPath);
					BoardDTO.setImage(db_image[1]);
				} catch (Exception e) {
					e.printStackTrace();
				} // end try
			} else { // 파일이 존재하지 않다면 null 대신 none 값 DB에 저장
				BoardDTO.setImage("none");
			}
			log.info("insertBoard : " + BoardDTO);
			BoardService.insertBoard(BoardDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물 등록 오류입니다.");
			model.addAttribute("url", "javascript:history.back();");
			return "Board/result";
		} // end try
	}// end insert..

	// 게시판 글 목록 조회 페이지로 이동
	@GetMapping("list")
	public String list(CriteriaDTO cri, Model model) throws Exception {
		try {
			// 페이징 객체를 서비스로 넘겨주고 BoardDTO 타입의 리스트로 받음
			List<BoardDTO> list = BoardService.getBoardListWithPaging(cri);
			model.addAttribute("list", list);
			//model.addAttribute("pageMaker", new PageDTD(cri, 123));
			
			// 페이징 시 필요한 총 개수 카운트
			int total = BoardService.getTotalCount(cri);
			log.info("total " + total);
			// 페이징 객체 뷰로 전달
			model.addAttribute("pageMaker", new PageDTD(cri, total));
			return "Board/list";
		} catch (Exception e) {
			throw e;
		}
	}

	// 게시글 상세 조회 뷰로 이동
	// 글 번호 변수 no를 파라미터로 받음
	@GetMapping("detail")
	public String detail(@RequestParam(defaultValue = "0") long no, @ModelAttribute("cri") CriteriaDTO cri, Model model) throws Exception {
		try {
			log.info("detail Controller");
			// 글 번호로 서비스에 넘겨주어 글 정보를 받아옴
			BoardDTO BoardDTO = BoardService.getDetail(no);
			log.info(BoardDTO);
			// 받아몬 값을 뷰로 넘겨주기 위해 모델에 저장
			model.addAttribute("boardDTO", BoardDTO);
			return "Board/detail";
		} catch (Exception e) {
			model.addAttribute("msg", "접근할 수 없는 게시물이거나 시스템 오류입니다.");
			model.addAttribute("url", "list");
			return "Board/result";
		} // end try
	}// end detell..

	// 게시글 삭제 화면으로 이동
	@GetMapping("delete")
	public String delete(@RequestParam long no, Model model) {
		try {
			model.addAttribute("no", no);
			return "Board/delete";
		} catch (Exception e) {
			throw e;
		} // end try
	}// end delete

	// 게시글 삭제하는 서비스로 객체를 넘겨줌 
	@PostMapping("delete")
	public String delete(@ModelAttribute BoardDTO BoardDTO, Model model) {
		log.info(BoardDTO.toString());
		try {
			BoardService.deleteBoard(BoardDTO);
			model.addAttribute("msg", BoardDTO.getNo() + "번 글이 삭제 되었습니다.");
			model.addAttribute("url", "list");
			return "Board/result";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "Board/result";
		} // end try
	}// end delete..

	// 게시글 수정하는 페이지로 이동
	@GetMapping("update")
	public String update(@RequestParam long no, Model model) {
		try {
			BoardDTO BoardDTO = BoardService.getDetail(no);
			model.addAttribute("boardDTO", BoardDTO);
			return "Board/update";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "Board/result";
		} // end try
	}// end update

	// BoardDTO 객체를 서비스에 넘겨줌으로써 게시글 수정 기능이 동작하도록 함
	@PostMapping("update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		try {
			BoardService.updateBoard(boardDTO);
			return "redirect:detail?no=" + boardDTO.getNo();
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "Board/result";
		} // end try
	}// end update
	
	// 폴더 이름 만드는 함수
	private String makeFolder() {
		// 오늘 날짜 생성패턴 대소문자 구분 주의
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

	// 파일 출력하는 메소드
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		ResponseEntity<byte[]> result = null;
		try {
			// 브라우저에서 파일이름이 오기때문에 디코딩
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info(srcFileName);
			File file = new File(uploadPath + File.separator + srcFileName);
			log.info(file);
			// 헤더 생성 브라우져에 보내야 하므로
			HttpHeaders headers = new HttpHeaders();
			// 헤더에 콘텐츠 타입 설정
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			//
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 500번 에러 보냄
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} // end try
		return result;
	}// end getfile..

}// end class
