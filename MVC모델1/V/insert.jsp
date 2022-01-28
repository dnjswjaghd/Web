<%@ page contentType="text/html;charset=utf-8" import="java.util.*, mvc.domain.Board"%> 

<%
	  boolean flag = (Boolean)request.getAttribute("flag");
%>
	  <script>
	  if(<%=flag%>){
		  alert("입력성공"); 
	  }else{
		  alert("입력실패");
	  }
	  location.href='board.do'
	  </script>
	  System.out.println("AddrServletIn name: "+name+", email: "+email+"");
