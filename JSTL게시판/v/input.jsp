<%@  page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html lang="ko">
<meta charset='utf-8'>
    <head>
    <title>간단한 게시판</title> 
    <script language="javascript">
	   function check()
	   {
	       for(var i=0; i<document.input.elements.length; i++)
		   {
		      if(document.input.elements[i].value == "")
			  {
			     alert("모든 값을 입력 하셔야 합니다. ");
				 return false;
			  }
		   }
		   document.input.submit();
       }
	</script>
	
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
    <body id="page-top" onload="input.name.focus()">
	
<header class="masthead">
            <div class="container px-4 px-lg-5 h-100">   
            <c:if test="${!empty userId}">
	<h1 align='left'>${userId} 님 환영합니다.</h1>
</c:if>
<c:if test="${empty userId}">
	<h1 align='left'>Guest로 로그인 하셨습니다.</h1>
</c:if>
    <center>
	   <hr width="600" size="2" noshade>
	      <h2>Board Input with JSTL+EL by Jinun</h2>
		  <a href='board.do'>글목록</a>
	   <hr width="600" size="2" noshade>
	</center>
	<form name="input" method="post" action="board.do?m=insert"> 
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
	   <table border="1" width="600" align="center"  cellpadding="3" cellspacing="1">
	      <tr>
		     <td width="30%" align="center">글쓴이</td>
			 <td align="center"><input type="text" name="name" size="60" value="${userId}" readonly></td>
		  </tr>
		  <tr>
		     <td align="center">이메일</td>
			 <td align="center"><input type="text" name="email" size="60"></td>
		  </tr>
          <tr>
		     <td align="center">글제목</td>
			 <td align="center"><input type="text" name="subject" size="60"></td> 
		  </tr>
		  <tr>
		     <td align="center">글내용</td>
			 <td align="center"><textarea name="content" rows="5" cols="53"></textarea></td>
		  </tr>
		  <tr>
		     <td colspan="2" align="center">
			    <input type="button" value="전송" onclick="check()">
				<input type="reset" value="다시입력">
			 </td>
		  </tr>
	   </table>
	   <br>
	   <hr width="600" size="2" noshade>
	</form>
	  </div>
        </header>
        
</body>

</html>