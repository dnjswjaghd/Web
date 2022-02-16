<%@ page contentType="text/html;charset=utf-8" import="team1.togather.domain.Member" %>
<meta charset='utf-8'>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%String getMname=(String)request.getAttribute("getMname"); 
	System.out.println(getMname);
%>
<c:if test ="${empty m}">
			<center>
			<tr>		
			<td colspan="5" style ="text-align:center">아이디가없네요 회원가입해주세요</td>
			<a href='join.jsp'>회원가입</a>
			다시로그인하시겠습니까?<a href='login2.jsp'>로그인</a>
			</tr>
			</center>
</c:if>

<c:choose>	
	<c:when test ="${phone ne m.email}">
			<script>
					alert("아이디가 다릅니다,아이디가없으시면 회원가입해주세요11");
					location.href='login2.jsp';
			</script>			
	</c:when>
	<c:when test ="${pwd ne m.pwd}">
			<script>
					alert("비밀번호가 다릅니다");
					location.href='login2.jsp';
			</script>	
	</c:when>
	<c:when test ="${phone eq m.email  && pwd eq m.pwd }">
			<script>
					alert('${userid}님 환영합니다');	
					opener.parent.location.reload();
					window.close();
					location.href='../';
			</script>			
	</c:when>
</c:choose>





		



