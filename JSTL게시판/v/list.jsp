<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<%
	System.out.println("한글한글");
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
    <header class="masthead">
            <div class="container px-4 px-lg-5 h-100">   
<style>
	table, th, td {
	   border: 2px solid black; 
	   border-collapse: collapse; 
	   border-color: white;
	   
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none } 
</style>

 <%
		 	String id = (String)session.getAttribute("userId");
			if(id == null){
				id="";
			}
			boolean flag = false;
			if(id.length()!=0){
				flag = true;
			}
%>
<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">

            <div class="container px-4 px-lg-5">
           		<c:if test="${empty userId}">
					<a class="navbar-brand" href="../#page-top">Guest</a>
				</c:if>
				<c:if test="${not empty userId}">
               		<a class="navbar-brand" href="../#page-top">${userId}님 접속됨</a>
                </c:if>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul id="friends2" class="navbar-nav ms-auto my-2 my-lg-0">
                        <li class="nav-item"><a class="nav-link" href="../#about">About</a></li>
                        <li class="nav-item"><a class="nav-link" href="../#services">Stacks</a></li>
                        <li class="nav-item"><a class="nav-link" href="../#portfolio">Portfolio</a></li>
                        <li id="friends" class="nav-item"><a class="nav-link" href="../#contact">Contact</a></li>

                        
                        <li id="signin" class="nav-item"><a class="nav-link" href="../join/member.do?m=signIn">Sign In</a></li>
                        <li id="signup" class="nav-item"><a class="nav-link" href="../join/member.do?m=signup">Sign Up</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <script>
                        	if(<%=flag%>){
                        		document.getElementById('signin').remove();
                        		document.getElementById('signup').remove();
                        		let li = document.createElement('li');
                        		let litext = document.createTextNode('Sign Out');
                        		const a = document.createElement("a");
                        		li.id = 'signout';
                        		li.className ='nav-item';
                        		
                        		a.href = '../join/member.do?m=signout&userId=<%=id%>';
                				
                        		a.className ='nav-link';
                        		//a.createTextNode('Sign Out');
                        	
                        		
                        		
                        		li.appendChild(a);
                        		a.appendChild(litext);
                        		let targetul = document.getElementById('friends2');
                        		targetul.appendChild(li);
                        		
                        	}
                        </script>
<center>
	<h1>
		Address List With JSTL+EL
	</h1>
	<a href = "../">인덱스</a>
	<a href = "board.do?m=input">글쓰기</a>
	<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	    <tr class="text-white font-weight-bold">
		    <th class="text-white font-weight-bold">글번호</th>
			<th class="text-white font-weight-bold">작성자</th>
			<th class="text-white font-weight-bold">이메일</th>
			<th class="text-white font-weight-bold">제목</th>
			<th class="text-white font-weight-bold">날짜</th> 
		</tr>
<c:if test="${empty list}">
	<tr>
		<td colspan="5" style="text-align:center">데이터가 하나도 없네요</td>
	</tr>
</c:if>
<c:forEach items="${list}" var="board">
    <tr>
		<td class="text-white font-weight-bold" align='center'>${board.seq}</td>
		<td class="text-white font-weight-bold">${board.name}</td>
		<td class="text-white font-weight-bold">${board.email}</td>
		<td class="text-white font-weight-bold"><a href='board.do?m=content&seq=${board.seq}'>${board.subject}</a></td>
		<td class="text-white font-weight-bold">${board.rdate}</td>
	 </tr>
</c:forEach>

	
	</table>
</center>
   </div>
        </header>
</body>

</html>

