<%@ page contentType="text/html;charset=utf-8" import="java.util.*, team1.togather.domain.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
						<hr width='600' size='2' noshade>
						<h2>Simple Board with MVC with Jinun</h2>
						&nbsp;&nbsp;&nbsp;
						<a href='input.jsp'>글쓰기 </a>
						<hr width='600' size='2' noshade>
						<table border='1' width='600' align='center' cellpadding='3'>
					
						<tr>
							<td width='100' align='center'>글번호</td>
							<td>${board.bnum}</td>
							</tr>
						<tr>
							<td align='center'>글쓴이</td>
							<td>${name}</td>
						</tr>
						<tr>
							<td align='center'>카테고리</td>
							<td>${board.bcategory}</td>
						</tr>
						<tr>
							<td align='center'>글제목</td>
							<td>${board.btitle}</td>
						</tr>
						<tr>
							<td align='center'>글내용</td>
							<td>${board.bcontent}</td>
						</tr>
						<tr>
							<td align='center'>파일</td>
							<td>${board.bfile}</td>
						</tr>
						<tr>
							<td align='center'>좋아요</td>
							<td>${board.blike}</td>
						</tr>
						<tr>
							<td align='center'>조회수</td>
							<td>${board.bview}</td>
						</tr>
						
						</table>
						
						<hr width='600' size='2' noshade>
						<hr width='600' size='2' noshade>
						<b>
						<a  href='board.do?m=update&bnum=${board.bnum}&name=${name}'>수정</a>
						 | 
						<a href='board.do?m=del&bnum=${board.bnum}'>삭제</a> 
						 | 
						<a href='board.do'>목록!</a>
						</b>
						<hr width='600' size='2' noshade> 
						</center>
</body>
</html>