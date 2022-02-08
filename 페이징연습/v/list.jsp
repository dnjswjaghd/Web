<%@ page contentType="text/html;charset=utf-8" import="java.util.*, page.board.domain.PageBoard"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="http://image.lgeshop.com/css/style_2005.css">

<html>
  <head> 
    <title>reboard_list_partSel.jsp</title>
  </head>
  <body>
    <center>
	  <hr width="600" color="Maroon" size="2" noshade>
	    <font size="5" color="Navy">
		  <b>JINUN board</b>
		</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="rb-write.do?method=write">글쓰기</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href='main.do'>메인</a>
		
		<form  name="f" action="board.do">
		  <input type="hidden"  name="m" value="list">
		  <select name="ps" onChange="submit()">

              <option value="3" selected>페이지 SIZE 선택</option>
				<option value=5>페이지 SIZE 5</option>
				<option value=10>페이지 SIZE 10</option>
				<option value=15>페이지 SIZE 15</option>
				<option value=20>페이지 SIZE 20</option>

		  </select>
		</form>
		
	  <hr width="600" color="Maroon" size="2" noshade>

	  <table align="center" cellspacing="1" cellpadding="3" 
	                                   width="600" border="1">
		<tr align="center">
		  <td width="10%">
		    <b>순번</b>
		  </td>
		  <td width="40%"><b>제목</b></td>
		  <td width="15%"><b>글쓴이</b></td>
		  <td width="15%"><b>날짜</b></td>
		  <td width="20%"><b>조회수</b></td>
		</tr>

<c:forEach items="${list}" var="pageboard">
                <tr align="center">
				  <td width="10%">${pageboard.idx}</td>
				  <td width="40%" align="left">
				    
		            <a href="rb-content.do?method=content&idx=9&cp=3">
                      ${pageboard.subject}
                    <a>
				  </td>
				  <td width="15%">
                      ${pageboard.writer}
				  </td>
				  <td width="15%">${pageboard.writedate}</td>
				  <td width="20%">${pageboard.readnum}</td>
				</tr>
</c:forEach>
		  <td colspan="5">
		    <hr width="600" color="Maroon" size="2" noshade>
		  </td>
		</tr>

		<tr>
		  <td colspan="3" align="center">

             <a href="rb-list.do?method=list&cp=2&ps=3">
                ◀이전
			 </a>

           |

             <a href="rb-list.do?method=list&cp=1&ps=3">
1

             </a> 

             <a href="rb-list.do?method=list&cp=2&ps=3">
2

             </a> 

             <a href="rb-list.do?method=list&cp=3&ps=3">
<b>3</b>

             </a> 

             <a href="rb-list.do?method=list&cp=4&ps=3">
4

             </a> 

             <a href="rb-list.do?method=list&cp=5&ps=3">
5

             </a> 

		   |

             <a href="rb-list.do?method=list&cp=4&ps=3">
			    이후▶
			  </a>

			 &nbsp;&nbsp;&nbsp; 
			3page/5pages
		  </td>
		  <td colspan="2" align="center">
		  <% ArrayList<PageBoard> pagelist =(ArrayList<PageBoard>)request.getAttribute("list");
		  	 int pageListSize = pagelist.size();
		  %>
		    총 게시물 수 : <%=pageListSize%>
		  </td>
		</tr>
	  </table>
	  <hr width="600" color="Maroon" size="2" noshade>
	</center>
  </body>
</html>