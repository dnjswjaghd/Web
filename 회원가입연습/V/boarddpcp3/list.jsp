<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.*"%> 
	
<%
	System.out.println("한글한글");
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
<%
	String id = (String)session.getAttribute("userId");
	System.out.println("id:"+id);
	if(id==null){
		id = "Guest";
	}
%>
<h1 align='left'><%=id%></h1>
<p></p>
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
		ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
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
			<tr>
				<td align='center'><%=dto1.getSeq()%></td> 
				<td><%=dto1.getName()%></td>
				<td><%=dto1.getEmail()%></td>
				<td><a href='board.do?m=content&seq=<%=dto1.getSeq()%>'><%=dto1.getSubject()%></a></td>
				<td><%=dto1.getRdate()%></td>
			</tr>
<% 
				}
			}
		}
		
%>
	
	</table>
</center>

