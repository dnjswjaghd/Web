<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, jin.board.domain.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1>
		Address List MVC
	</h1>
	<a href = "../">인덱스</a>
	<a href = "board.do?m=input">글쓰기</a>
	<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	    <tr>
		    <th>글번호</th>
			<th>작성자</th>
			<th>이메일</th>
			<th>제목</th>
			<th>날짜</th> 
		</tr>
<% 
		List<Board> list = (List<Board>)request.getAttribute("list");
		if(list != null){
			int size = list.size();
			if(size==0){
%>
			<tr>
				<td colspan="5" align='center'>데이터가 하나도 없네요</td>
			</tr>
<%
			}else{
				for(Board dto1 :list){
%>
<!--   
			<tr>
				<td align='center'><%=dto1.getSeq()%></td> 
				<td><%=dto1.getWriter()%></td>
				<td><%=dto1.getEmail()%></td>
				<td><a href='board.do?m=content&seq=<%=dto1.getSeq()%>'><%=dto1.getSubject()%></a></td>
				<td><%=dto1.getRdate()%></td>
			</tr>
			-->
<% 
				}
			}
		}
		
%>
	
	</table>
</center>

</body>
</html>
