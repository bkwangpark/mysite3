<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="/WEB-INF/views/include/header.jsp" flush="false"/>
		</div>
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img id="profile" src="https://scontent.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/396256_298008146978836_34226270_n.jpg?oh=68ad258be592e4a8d79c58ae84f2e421&oe=560BE8B2" width=200>
					<h2>안녕하세요.  박병광의  mysite에 오신 것을 환영합니다.</h2>
					<p>
						이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.<br>
						메뉴는  사이트 소개, 방명록, 게시판이 있구요. JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서
						만들어 놓은 사이트 입니다.<br><br>
						
						<a href="/mysite3/guestbook">방명록</a>에 글 남기기<br>
					</p>
				</div>
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