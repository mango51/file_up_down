package com.care.root.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.dto.ShoesDTO;
import com.care.root.mybatis.FileMapper;

@Service	//컨트롤러에 해당 기능 service다라는 것 알려줌
public class FileServiceImpl implements FileService {
	
	@Autowired FileMapper mapper;
	
	public void fileProcess(MultipartHttpServletRequest mul){
		
		ShoesDTO dto = new ShoesDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		//해당하는 파일명도 dto에 저장해야하지만 밑의 코드 실행 완료 후 파일명 저장하기 (파일명 우리가 직접 수정해서 새로운 파일명으로 만들어지니까)
		
		MultipartFile mf = mul.getFile("file");
		if(mf.isEmpty()==false) { // mf.getSize()!=0 과 동일
			// 파일이 비어 있지 않다면 파일이 있다는 뜻
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
			// 동일한 파일명이 있을 경우 덮어쓰기 되니까 구별하기 위해 업로드한 날짜 시간 앞에 적어주기
			Calendar calendar = Calendar.getInstance(); //캘린더 기능하는 객체 가져오기
			String sysFileName = format.format(calendar.getTime());
			// 현재 날짜 시간을 calendar.getTime()으로 받아 만든 format 안에 넣어주기(format()기능으로)
			sysFileName += mf.getOriginalFilename();
			// 현재 시간날짜 정보 format 형태로 가지고 있는 sysFileName에 파일명까지 뒤에 붙여줌
			
			dto.setImgName(sysFileName); // 해당하는 파일 존재하면 dto에 방금 설정한 파일명으로 저장하기
			
			File saveFile = new File(IMAGE_REPO+"/"+sysFileName);
			// 파일 객체 만들어서 저장할 파일 어디에 저장(IMAGE_REPO)하고 파일명은 어떻게("/"+sysFileName) 할 것인지
			try {
				mf.transferTo(saveFile); // 업로드할 파일 (request로 받은 값) 을 saveFile 주소로 이동하기(transferTo())
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			// 넘어온 파일이 없으면 (업로드할 파일이 없으면) 그냥 이미지 파일명 nan으로 설정
			// 해당하는 이미지 없음을 알리는 것 >> nan
			dto.setImgName("nan");
		}
		mapper.saveData(dto);
	}
	
	public void getShoesImage(Model model) {
		model.addAttribute("list", mapper.getShoesImage());
		// 오버라이딩하기
	}
}
