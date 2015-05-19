<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
<div id="container">
		<div id="header">
		<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false"/>
		</div>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="/mysite3/member">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${authMember.name}">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${authMember.email}">
					
					<label class="block-label">성별</label>
					<input name="gender" type="text" value="${authMember.gender}">
					<br>
					<a href="/mysite3/index">메인으로</a>
				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/include/navigation.jsp"/>
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>