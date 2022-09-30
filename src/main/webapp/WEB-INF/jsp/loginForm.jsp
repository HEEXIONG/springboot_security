
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<h1>로그인 페이지</h1>
<hr/>
<form action="/login" method="post">
	<input type="text" name="username" placeholder="Username 입력 바랍니다."/> <br><br/>
	<input type="password" name="password" placeholder="Password 입력 바랍니다." /> <br><br/>
	<button>로그인</button><br><br/>
</form>
	<a href="/joinForm">회원가입을 안하신 분들은 클릭 바랍니다.</a>
</body>
</html>