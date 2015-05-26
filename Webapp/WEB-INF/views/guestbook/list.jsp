<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp" />
		</div>
		<div id="content">
			<div id="guestbook">
				<form action="" method="post">
					
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right>
							<input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<c:forEach items="${list }" var="vo">
							<table>
								<tr>
									<td>${vo.no }</td>
									<td>${vo.name }</td>
									<td><a
										href="/mysite3/guestbook/delete/${vo.no}">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>${vo.context }</td>
								</tr>
							</table>
						</c:forEach> <br>
					</li>
				</ul>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/include/navigation_board.jsp"/>
		</div>
		<div id="footer">
			<p>(c)opyright 2014 </p>
		</div>
	</div>
</body>
</html>