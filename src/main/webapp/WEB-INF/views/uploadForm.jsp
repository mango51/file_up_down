<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!-- 컨트롤러에서 pageContext.request로 받아온 contextPath를 변수 contextPath에 넣기 
	url에 포트번호 뒤 form 입력 시 해당 uploadForm.jsp 페이지로 이동하고 이동하면서 어디서 넘어왔는지 contextPath에 저장 >> 컨트롤러에서 여기로 연결되어 왔다 표시
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
uploadForm.jsp
<h1> 파일 업로드</h1>
<form action="${contextPath }/upload" enctype="multipart/form-data" method="post">
	<!-- multipart/form-data : multipart 꼭 사용해야함 >> 파일 업로드 기능하기 위해서는
		method="post" >> 대량의 데이터 전송하니 post로 감춰서 보내기
	 -->
	 <input type="text" name="id" placeholder="id"><br>
	 <input type="text" name="name" placeholder="name"><br>
	 <input type="file" name="file"><br>
	 <input type="submit" value="업로드">
</form>
	
	<a href="${contextPath }/views">파일 보기</a>
	<!-- 컨트롤러로 가서 views 내용 실행 -->
</body>
</html>