<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp" />
		</div>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${list }" var="vo">
					<tr>
						<td>${vo.no }</td>
						<td><a href="/mysite3/board?a=show&no=${vo.no }">${vo.title }</a></td>
						<td>${vo.mem_name }</td>
						<td>1</td>
						<td>${vo.date }</td>
						<td>
							<a href="" class="del">삭제</a>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="bottom">
					<a href="/mysite3/views/board/write.jsp" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation_board.jsp" />
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp" />
		</div>
	</div>
</body>
</html>