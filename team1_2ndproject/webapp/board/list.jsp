<%@ page contentType="text/html;charset=utf-8" import="java.util.*, team1.togather.domain.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToGather 게시판</title>
 ${userid}님 환영합니다!
</head>
<body>

<center>
	<h1>
		ToGather 게시판
	</h1>
	<a href = "../">인덱스</a>
	<a href = "board.do?m=input">글쓰기</a>
	<form name="input2" method="post" action="board.do?m=olist">
		<select name="option">
			<option value="none">===선택===</option>
			<option value="bcategory">카테고리</option>
			<option value="btitle">제목</option>
			<option value="mname">작성자</option>
		</select>
		<input type="text" name="ocontent">
		<input type="submit" value="전송" onclick="check()">
	</form>
	<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	    <tr class="text-white font-weight-bold">
		    <th class="text-white font-weight-bold">글번호</th>
		    <th class="text-white font-weight-bold">카테고리</th>
			<th class="text-white font-weight-bold">제목</th>
			<th class="text-white font-weight-bold">작성자</th>
			<th class="text-white font-weight-bold">조회수</th>
			<th class="text-white font-weight-bold">등록일</th> 
		</tr>



<%  ArrayList<Board> blist =(ArrayList<Board>)request.getAttribute("blist");
    ArrayList<String> namelist = (ArrayList<String>)request.getAttribute("namelist"); 
	for(int i=0; i<blist.size(); i++){
%>
    <tr>
		<td class="text-white font-weight-bold" align='center'><%=blist.get(i).getBnum()%></td>
		<td class="text-white font-weight-bold" align='center'><%=blist.get(i).getBcategory()%></td>
		<td class="text-white font-weight-bold"><a href='reply.do?m=list&bnum=<%=blist.get(i).getBnum()%>&name=<%=namelist.get(i)%>'><%=blist.get(i).getBtitle()%></a></td> 
		<td class="text-white font-weight-bold"><% out.print(namelist.get(i));%></td>
		<td class="text-white font-weight-bold"><%=blist.get(i).getBview()%></td>
		<td class="text-white font-weight-bold"><%=blist.get(i).getRdate()%></td>
	 </tr>
<%} %>

	
	</table>
</center>
</body>
</html>