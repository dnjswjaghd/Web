<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.Board"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset='utf-8'>
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none }
</style>
<body onload='javascript:document.f.email.focus();'>
	<center>
		<hr width='600' size='2' noshade>
		<h2>Simple Board with JSTL+EL</h2>
		<a href='list.jsp'>글목록</a>
		<hr width='600' size='2' noshade>
	</center>
	<%
		  request.setCharacterEncoding("utf-8");
		  String seqStr = request.getParameter("seq"); 
		  
		  int seq = -1;
			  if(seqStr != null) { 
				seqStr = seqStr.trim();
				try{
					seq = Integer.parseInt(seqStr);
				}catch(Exception se){
					response.sendRedirect("update.do"); 
					System.out.println("8");
				}
			  } 
	%>
<c:forEach items="${listup}" var="board">
	<form name='f' method='post' action='board.do?m=updateGet'>
			<input type='hidden' name='seq' value='${board.seq}'>
			<input type='hidden' name='writer' value='${board.name}'>
		<table border='1' width='600' align='center' cellpadding='3' cellspacing='1'><tr>
			<td width='30%' align='center'>글쓴이</td>
			<td align='center'><input type='text' name='name' size='60' value='${board.name}' disabled></td>
		</tr>
		<tr>
			<td width='30%' align='center'>이메일</td>
			<td align='center'><input type='text' name='email' size='60' value='${board.email}'></td>
		</tr>
		<tr>
			<td width='30%' align='center'>글제목</td>
			<td align='center'><input type='text' name='subject' size='60' value='${board.subject}'></td>
		</tr>
		<tr>
			<td width='30%' align='center'>글내용</td>
			<td align='center'><textarea name='content' rows='5' cols='53'>${board.content}</textarea></td>
		</tr>
		<tr>
</c:forEach>
			<td colspan='2' align='center'>
			<input type='submit' value='수정'>
		</td>
		</tr>
		</table>
	</form>
</body>