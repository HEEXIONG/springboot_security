<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
<h1>회원가입 페이지</h1>
<hr/>
<form action="/join" method="post">
	<input type="text" name="username" placeholder="Username"/> <br/><br/>
	<input type="password" name="password" placeholder="Password"/> <br/><br/>
	<input type="email" name="email" placeholder="Email"/> <br/><br/>
	<button>회원가입 신청</button>
</form>
</body>
</html>