package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	public void sendMail(String to, String subject, String body) {
		
		// 보내고자 하는 사용자 to (수신자)
		MimeMessage message = mailSender.createMimeMessage();
		// MimeMessage : 인터넷으로 메일 보내는 기능하는 클래스
		// mailSender.createMimeMessage() : 
		// 컨트롤러에서 가지고 온 데이터 (javamailsender로 만든) mailsender에 인터넷으로 메일 보낼 수 있도록 만들기 (createMimeMessage())
		
		try {
			MimeMessageHelper mm = new MimeMessageHelper(message,true,"UTF-8");
			mm.setSubject(subject);
			mm.setTo(to);
			mm.setText(body, true); //true : 작성한 html 태그 적용
			// 가지고 온 mm 데이터에서 set() 통해서 보낼 메일의 subject, to, body 설정하기
			// MimeMessageHelper 안에 있는 setSubject(), setTo(), setText() 함수들 >> 가져온 mm 값 거기다가 넣어서 보내는 메일 설정하기
			
			mailSender.send(message); //message 문자 보내기
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void auth(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId ="hyom";
		String userKey=rand();
		session.setAttribute(userId, userKey);
		// userId 속성값은 userKey
		String body="<h3>이메일 인증</h3>";
		body+="<h5>"+userId+"님 </h5>"+ "<p>인증하기 버튼을 누르세요</p>"+"<a href='http://localhost:8085"
		+req.getContextPath()+"/auth_check"+"?userid="+userId+"&userKey="+userKey+"'>인증하기</a>";
		sendMail("mango51@daum.net","이메일 인증입니다",body);
	}
	
	private String rand() {
		Random ran = new Random();
		String str="";
		int num;
		while(str.length()<=20) {
			num=ran.nextInt(75)+48; //(0~74 랜덤 값 뽑아줌)+48 => 알파벳과 숫자(0,1,2,3,4,5,6,7,8,9,소문자,대문자)로 만들기 위해서 (48~122)
			if((num>=48 && num<=57) || (num>=65 && num<=90) || (num>=97 && num<=122)) {
				// 문자 0부터 문자 9까지의 숫자일 경우 || 대문자로 들어왔는지 || 소문자로 들어왔는지
				str+=(char)num; // 랜덤 관리자 아이디 나옴
			}
		}return str;
	}
}
