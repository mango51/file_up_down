package com.care.root.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 파일 업로드 기능 라이브러리 import 함
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
public class FileConfig {
	// 파일 설정 >> 업로드할 때에 대한 설정할 때 사용되는 파일
	@Bean //객체 만들어주는 것
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(52428800); //50MB
		mr.setDefaultEncoding("utf-8");
		return mr;
	} // 파일 업로드하려면 무조건 위의 코드로 설정해줘야함 >> 파일 업로드 기능
}
