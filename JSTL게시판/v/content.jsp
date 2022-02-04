<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.Board"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Jinun's Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
        <!-- Bootstrap Icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic" rel="stylesheet" type="text/css" />
        <!-- SimpleLightbox plugin CSS-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
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
	<header class="masthead">
            <div class="container px-4 px-lg-5 h-100">   
                       <center>
						<hr width='600' size='2' noshade>
						<h2>Simple Board with MVC with Jinun</h2>
						&nbsp;&nbsp;&nbsp;
						<a href='input.jsp'>글쓰기 </a>
						<hr width='600' size='2' noshade>
						<table border='1' width='600' align='center' cellpadding='3'>
					<c:forEach items="${listup}" var="board">
						<tr>
							<td width='100' align='center'>글번호</td>
							<td>${board.seq}</td>
							</tr>
						<tr>
							<td align='center'>글쓴이</td>
							<td>${board.name}</td>
						</tr>
						<tr>
							<td align='center'>이메일</td>
							<td>${board.email}</td>
						</tr>
						<tr>
							<td align='center'>글제목</td>
							<td>${board.subject}</td>
						</tr>
						<tr>
							<td align='center'>글내용</td>
							<td>${board.content}</td>
						</tr>
						</table>
						
						<hr width='600' size='2' noshade>
						<hr width='600' size='2' noshade>
						<b>
						<a  href='board.do?m=update&seq=${board.seq}'>수정</a>
						 | 
						<a href='board.do?m=del&seq=${board.seq}&name=${board.name}'>삭제</a> 
					</c:forEach>
						 | 
						<a href='board.do'>목록!</a>
						</b>
						<hr width='600' size='2' noshade> 
						</center>

            </div>
        </header>
	
</body>

</html>