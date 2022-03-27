<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>auth.jsp</title>
</head>
<body>
auth.jsp
<c:if test="${userid ==null }"> <!-- 이메일 인증 안했다면 -->
	<a href="${contextPath }/auth">이메일 인증하기</a>
</c:if>

	<c:if test="${userid !=null }"> <!-- 이메일 인증되었으면  -->
		<h3>${userid  }님 이메일 인증 완료 되었습니다.</h3>
	</c:if>

</body>
</html>