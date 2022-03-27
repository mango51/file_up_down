package com.care.root.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.service.FileService;

@Controller
public class FileUploadController {
	
	@Autowired
	FileService fs;
	
	@RequestMapping("form")
	public String form() {
		return "uploadForm";
	} // 컨트롤러에서 페이지 이동 컨트롤함 >> url에 포트번호 뒤에 form 입력되어야 uploadForm 파일로 갈 수 있음
	// form 입력 시 uploadForm으로 이동
	
	@PostMapping("upload")
	public String upload(MultipartHttpServletRequest mul) {
		//MultipartHttpServletRequest mul >> 업로드하는 파일 형식(MultipartHttpServletRequest) mul에 받아옴
		String id = mul.getParameter("id");
		String name = mul.getParameter("name");
		// 입력한 값 id, name 가지고 오기
		
		MultipartFile file = mul.getFile("file");
		// mul.getFile("file") : 가지고 온 request 정보에서 파일형태 file 가지고 오기
		String originalName = file.getOriginalFilename();
		// 해당 파일명은 originalName에 넣기
		
		System.out.println("id : "+id);
		System.out.println("name : "+name);
		System.out.println("originalName : "+originalName);
		
		fs.fileProcess(mul);
		// 해당 파일 mul을 (fileProcess() 통해) fs(FileService 파일)에 업로드하기
		// mul 파일을 FileService형 fs의 fileProcess() 기능을 통해 넘기기
		
		return "redirect:form";
	} // upload 입력 시 위의 코드 실행 후 다시 form 으로 이동 (uploadForm 페이지로 이동)
	
	@GetMapping("views")
	public String views(Model model) {
		fs.getShoesImage(model);
		return "result";
		// result.jsp로 이동
	}
	
	@GetMapping("download") //다운로드 기능
	public void download(@RequestParam("file") String fileName, HttpServletResponse response) throws Exception{
		// 사용자에게 응답하기 위해 response 변수 받기
		
		// 객체에 name이라는 기능(다운로드) 실행하겠다 >> value값(파일)을 가지고!!!
		//addHeader(name,value) => name 안에 Content-disposition : 파일 다운로드 하겠다는  http 헤더의 하나다.
		// attachment; 해당하는 파일(fileName)을 붙여서 넣어주겠다
		// fileName : 전달될 파일명 지정해 줌
		response.addHeader("Content-disposition","attachment;fileName="+fileName);
		// response.addHeader("Content111-disposition","attachment111;fileName="+fileName); >> 111넣으면 사진 다운로드 X,새로운 창에 표시
		
		
		File file = new File(FileService.IMAGE_REPO+"/"+fileName);
		// IMAGE_REPO에 저장하는 파일 객체 생성
		
		FileInputStream fi = new FileInputStream(file);
		// 가져온 파일 input stream하기 (컴터 안에 넣어주기)
		
		FileCopyUtils.copy(fi, response.getOutputStream());
		//fi 파일 가지고 getOutputStream 하기 >> 사용자에게 fi 파일 보여주기 (filecopyutils 기능 중 copy로 실행)
		fi.close();
	}
}
