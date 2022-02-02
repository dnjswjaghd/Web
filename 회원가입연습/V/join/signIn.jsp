<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
					   <h1>
							로그인을 해주세요!
					   </h1>
					   <form name="f" action="member.do?m=login" method="post">
					       <table border="1" width="300" height="200">
						      <tr>
							     <td width="30%" colspan="2" align="center"><h2>로그인</h2></td> 
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
								      <input type="submit" value="join!"/>
				 						<input type="button" value="Sign up" onclick="location.href='member.do?m=signup'"/>
			 
								 </td> 
							  </tr>
						   </table>
					   </form>
					</center>
</body>
</html>