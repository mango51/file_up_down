package com.care.root.service;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface FileService { 
	//파일을 저장해야하니까 "파일 담는 공간" 만들기
	public static final String IMAGE_REPO="C:\\spring\\image_repo";
	// C:/spring/image_repo (위의 \\ 사용한 주소와 동일함) 에 파일들 저장됨
	
	public void fileProcess(MultipartHttpServletRequest mul);
	public void getShoesImage(Model model);
	
}
