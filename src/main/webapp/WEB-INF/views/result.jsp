<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
 a:link { color: red; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: blue; text-decoration: underline;}
</style>
</head>
<body>
	<c:forEach var="dto" items="${list }">
	<!-- request에서 받아온 list 안에 내용 items를 하나하나씩 출력하기 -->
	아이디 : ${dto.id }<br>
	이름 : ${dto.name }<br>
	파일 : ${dto.imgName }<br>
	<img src="${contextPath }/download?file=${dto.imgName }" style="width:100px; height:100px">
	<a href="${contextPath }/download?file=${dto.imgName }">
		${dto.imgName }
	</a><hr>
	</c:forEach> <!-- items 다 출력하면 넘어가기 -->
	<a href="${contextPath }/form">업로드 이동</a> <!-- form이라는 주소(컨트롤러에서 설정)로 이동 -->
</body>
</html>