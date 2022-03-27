package com.care.root.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Bean
	public static JavaMailSender mailSender() {
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		
		jms.setHost("smtp.gmail.com"); // jms.setHost(host);에서 host : google smtp 서버 설정 (gmail로 메일 보낼 수 있겠끔 구글 서버 설정)
		jms.setPort(587); //이메일 보낼 포트번호
		jms.setUsername("hyomango51");
		jms.setPassword("mangomango");
		// 발신자 정보 >> 로그인해놔서 메일 보낼 수 있도록 하기 (메일 보내는 주최자, 발신자)
		
		//메일 전달 프로토콜 세부 설정
		Properties prop = new Properties();
		
		prop.setProperty("mail.transport.protocol", "smtp"); 
		//해당 프로토콜(mail.transport.protocol)을 통해 gmail 메일(smtp)을 전송할 것이다
		// 메일 전송 프로토콜을 사용한다
		
		prop.setProperty("mail.smtp.auth", "true");
		// 메일 전송 시 사용자 인증한다 (true)
		
		prop.setProperty("mail.smtp.starttls.enable", "true");
		// tls방식으로 처리되며 tls는 보안처리를 해준다.
		
		jms.setJavaMailProperties(prop); //위에  설정한 properties를 저장하기
		
		return jms;
	}
}
