package com.care.root.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.service.MailService;

@Controller
public class MailController {
	
	@Autowired MailService ms;
	
	@GetMapping("sendmail")
	public void sendMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//이메일 보내기 기능
		
		ms.sendMail("mango51@daum.net","테스트 메일(제목)","안녕하세요, 테스트 메일입니다.");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); // 출력 기능
		out.print("<script> alert('메일이 전송되었습니다.') </script>"); //메시지 전달
		// out.print("메일 전송 완료");
		}
	
	@GetMapping("sendmail2")
	public void sendMail2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//이메일 보내기 기능
		
		StringBuffer sb = new StringBuffer(); 
		sb.append("<html><body>");
		sb.append("<h1> 뭐야 왜 안돼 </h1>");
		sb.append("<a href='https://www.naver.com/'>");
		sb.append("<img src='http://localhost:8085/download?file=20220212115600-캡처0125.PNG'>");
		sb.append("</a></body></html>");
		
		String str = sb.toString();
		//밑에 사용된 String보다 처리 속도 빠름 (위, 아래 코드 동일)
//		String str ="<html><body>";
//		str+="<h1> 뭐야 왜 안돼 </h1>"; str+="<a href='https://www.naver.com/'>";
		
		ms.sendMail("mango51@daum.net","테스트 메일(제목)",str);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); // 출력 기능
		out.print("<script> alert('메일이 전송되었습니다.') </script>"); //메시지 전달
		// out.print("메일 전송 완료");
		}
	
	@GetMapping("auth_form")
	public String authForm() {
		return "auth";
	}
	
	@GetMapping("auth")
	public String auth(HttpServletRequest req) {
		ms.auth(req);
		return "redirect:https://www.daum.net/"; //https://mail.daum.net/
	}
	
	@RequestMapping("auth_check")
	public String auth_check(@RequestParam String userid, @RequestParam String userKey,HttpSession session) {
		String sessionKey= (String) session.getAttribute(userid);
		if(sessionKey.equals(userKey)) {
			session.setAttribute("userid", userid);
			
		} return "redirect:auth_form";
		
	}
}
