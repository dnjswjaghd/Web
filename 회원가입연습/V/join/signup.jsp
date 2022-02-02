<%@  page contentType="text/html; charset=utf-8"%>
<html>
  
<meta charset="utf-8">
<head>
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
</head>
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
<body onload="document.f.name.focus()">
<center>
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
</body>

</html>