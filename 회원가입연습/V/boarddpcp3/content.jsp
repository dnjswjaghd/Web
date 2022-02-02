<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.Board"%> 
<%
			request.setCharacterEncoding("utf-8");
			String seqStr = request.getParameter("seq");
			int seq = -1;
			if(seqStr != null){
				seqStr = seqStr.trim();
				if(seqStr.length()!=0){
					try{
						seq = Integer.parseInt(seqStr);
					}catch(Exception ie){
						response.sendRedirect("content.jsp");
					}
				}else{
					response.sendRedirect("content.jsp");
				}
			} 
			ArrayList<Board> list =(ArrayList<Board>)request.getAttribute("listup");
			
%>
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
	<center>
	<hr width='600' size='2' noshade>
	<h2>Simple Board with MVC with Jinun</h2>
	&nbsp;&nbsp;&nbsp;
	<a href='input.jsp'>글쓰기 </a>
	<hr width='600' size='2' noshade>
	<table border='1' width='600' align='center' cellpadding='3'>
	<%for(Board dto1:list){ %>
	<tr>
		<td width='100' align='center'>글번호</td>
		<td><%=dto1.getSeq()%></td>
		</tr>
	<tr>
		<td align='center'>글쓴이</td>
		<td><%=dto1.getName()%></td>
	</tr>
	<tr>
		<td align='center'>이메일</td>
		<td><%=dto1.getEmail()%></td>
	</tr>
	<tr>
		<td align='center'>글제목</td>
		<td><%=dto1.getSubject()%></td>
	</tr>
	<tr>
		<td align='center'>글내용</td>
		<td><%=dto1.getContent()%></td>
	</tr>
	</table>
	
	<hr width='600' size='2' noshade>
	<hr width='600' size='2' noshade>
	<b>
	<a  href='board.do?m=update&seq=<%=dto1.getSeq()%>'>수정</a>
	 | 
	<a href='board.do?m=del&seq=<%=dto1.getSeq()%>'>삭제</a> 
	<%} %>
	 | 
	<a href='board.do'>목록!</a>
	</b>
	<hr width='600' size='2' noshade> 
	</center>
