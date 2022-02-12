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
							<td>${board.mname}</td>
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
						<a  href='board.do?m=update&bnum=${board.bnum}&mname=${name}'>수정</a>
						 | 
						<a href='board.do?m=del&bnum=${board.bnum}'>삭제</a> 
						 | 
						<a href='board.do'>목록!</a>
						</b>
						<hr width='600' size='2' noshade> 
						<form name="input" method="post" action="../board/reply.do?m=insert&bnum=${board.bnum}">
							댓글을 달아주세요▶<input type='text' name='rcontent' size='70%'>
							<input type="submit" value="전송" onclick="check()" hidden>
						</form>
						<hr width='600' size='2' noshade>
						<table border='1' width='1000' align='center' cellpadding='2'>
						<tr>
							<th align='center' width='15%'>작성자</th>
							<th align='center' width='40%'>글내용</th>
							<th align='center' width='15%'>좋아요 갯수</th>
							<th align='center' width='15%'>작성일</th>
							<th align='center' width='30%'></th>
						</tr>
						<c:if test ="${empty rlist}">
									<tr>
										<td colspan="5" style ="text-align:center">데이터가 하나도없네요</td>				
									</tr>
						</c:if>
						<c:forEach items = "${rlist}" var ="reply">
								<tr>
							<td align='center'>${reply.mname}</td>
							<td align='center'>${reply.content}</td>
							<td align='center'>${reply.r_like}</td>
							<td align='center'>${reply.rdate}</td>
							<td align='center'>
							<a href = 'reply.do?m=update1&rseq=${reply.rseq}}&bnum=${board.bnum}'> 수정 </a><a href='reply.do?m=del&rseq=${reply.rseq}&bnum=${board.bnum}'> 삭제 </a><a href='reply.do?m=r_like&rseq=${reply.rseq}&r_like=${reply.r_like}&bnum=${board.bnum}'> 좋아요 </a>
							</td>
						</tr>
						</c:forEach>					
						</table>
<hr width='600' size='2' noshade>
						</center>
						
</body>
</html>