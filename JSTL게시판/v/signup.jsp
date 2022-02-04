<%@  page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<br/><br/><br/><br/><br/>
					   <h1>
							회원가입
					   </h1>
					   <form name="input" action="member.do?m=insert" method="post">
					       <table border="1" width="300" height="200">
						      <tr>
							     <td width="30%" colspan="2" align="center"><h2>회원가입</h2></td> 
							  </tr>
							  <tr>
							     <th width="30%">Name</th> 
								 <td><input name="name" align="center" size="20" align="center"></td>
							  </tr>
							  <tr>
							     <th width="30%">ID</th> 
								 <td><input name="id" align="center" size="20" align="center"></td>
							  </tr>
							  <tr>
							     <th width="30%">PASSWORD</th> 
								 <td><input name="pwd" size="20" align="center"></td>
							  </tr>
							  <tr>
							     <td colspan="2" align="center">
								     <input type="submit" value="Sign Up" onclick="check()"/>
								 </td> 
							  </tr>
						   </table>
					   </form>
					</center>
   </div>
        </header>
</body>

</html>

