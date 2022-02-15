<%@ page contentType="text/html;charset=utf-8" import="java.util.*, team1.togather.domain.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>게시글 제목(받아올 값)</title>
    <!-- Favicon-->
    <link rel="icon" type="../image/x-icon" href="../assets/ToGather.ico" />
    <!-- Bootstrap icons-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />
	<script>
	window.onpageshow = function(event){
		if(event.persisted) || (window.performance && window.performance.naviation.type ==2)){
			location.href='board.do';
		}
	}
	</script>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet" />
    <link href="../css/groupList.css" rel="stylesheet" />
    <script src="../js/main.js" defer></script>
    <script src="../js/splitting.js"></script>
    <script src="../js/typed.js"></script>
  </head>
  <body>

    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light bg-gray">
      <div class="container px-4 px-lg-5">
        <h1 class="logo">
          <a href="../"
            ><img src="imgs/topLogo.png" alt="ToGather" title="홈으로 이동"
          /></a>
        </h1>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
            <li class="nav-item">
              <a
                class="nav-link active"
                aria-current="page"
                href="introduce.html"
                >ToGather란?</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="notice.html">공지사항</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                id="navbarDropdown"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                >자주하는 질문(Q&A 게시판필요)</a
              >
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <a class="dropdown-item" href="FAQ.html">FAQ</a>
                </li>
                <li>
                  <a class="dropdown-item" href="QA.html">Q&A(게시판필요)</a>
                </li>
              </ul>
            </li>
          </ul> 
          <form class="d-flex">
            <!-- 로그인시 보이게하기
            <button class="btn btn-outline-success" type="button" onclick="location.href='logout.html'">
              <i class="bi bi-person-check-fill"></i>
              로그아웃
            </button>
          -->
            <button
              class="btn btn-outline-dark"
              type="button"
              onclick="location.href='login.html'"
            >
              <i class="bi bi-person-fill"></i>
              로그인
            </button>
            <button
              class="btn btn-outline-primary"
              type="button"
              onclick="location.href='join.html'"
            >
              <i class="bi bi-person-plus-fill"></i>
              회원가입
            </button>
            <button
              class="btn btn-outline-dark"
              type="button"
              onclick="location.href='wish.html'"
            >
              <i class="bi-cart-fill me-1"></i>
              찜
              <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
            </button>
            <!--회원전용 메뉴. 비로그인시 로그인먼저 하도록 alert 띄우기-->
            <button
              class="btn btn-outline-danger"
              type="button"
              onclick="location.href='groupCreate.html'"
            >
              <i class="bi bi-people-fill"></i>
              모임 만들기
            </button>
          </form>
        </div>
      </div>
    </nav>

    <section class="vh-100" style="background-color: #eee">
      <div class="container h-75 contents-center">
        <div class="row justify-content-center align-items-center h-75">
            <div class="col-lg-3"></div>
            <div class="col-lg-6 h-50">
              <table class="table table-bordered">
                <thead class="table-info text-center">
                  <tr>
                    <th scope="col" colspan="8">게시글</th>
                  </tr>
                <tbody>
				  <tr class="text-center">
                    <th scope="col" name="ntName" class="table-secondary">글제목</th>
                    <td colspan="7">${board.btitle}</td>   
                  </tr>
                  <tr class="text-center">
                    <th scope="row" class="table-secondary" >카테고리</th> 
                    <td colspan="7">${board.bcategory}</td>   
                  </tr>
                  <tr class="text-center">
                    <th scope="row" class="table-secondary" >작성자</th>
                    <td colspan="8">${board.mname}</td>
                  </tr>
                  <tr>
                    <th scope="row" colspan="8" style="text-align: center;" class="table-secondary">글내용</th>
                  </tr>
                  <tr>
                    <td colspan="8" style="line-height: 70px; min-height: 70px; height: 200px;" >${board.bcontent}
                    </td>
                  </tr>
                </tbody>
                  <tfoot>
                  <tr>
                    <th scope="row" colspan="8" style="text-align: center;" class="table-secondary">댓글</th>
                  </tr>
				  
                  <tr class="text-center">
                    <td>작성자</td>
                    <td colspan="4">내용</td>
                    <td colspan="2">좋아요</td>
					<td colspan="2">작성일</td>
                  </tr>
				  <% long mnum = (Long)session.getAttribute("usermnum");
						   ArrayList<Reply> rlist = (ArrayList<Reply>)request.getAttribute("rlist");
						  
						   for(int i=0; i<rlist.size(); i++){
					%>
                  <tr>
                    <th scope="row"  style="text-align: center;"><%=rlist.get(i).getMname()%></th> 
                    <td colspan="4"><%=rlist.get(i).getContent()%></td>
                    <td colspan="2"><%=rlist.get(i).getR_like()%><i class="bi bi-hand-thumbs-up" align="right" type="button" onclick="location.href='reply.do?m=r_like&rseq=<%=rlist.get(i).getRseq()%>&r_like=<%=rlist.get(i).getR_like()%>&bnum=${board.bnum}'" ></i></td>
					<td colspan="2"><%=rlist.get(i).getRdate()%></td>
                  </tr>
					<%} %>
                </tfoot>
              </table>
              	<form name="input" method="post" action="../board/reply.do?m=insert&bnum=${board.bnum}"> 
              <div class="input-group"> 
                <input type="text" name='rcontent' class="form-control" aria-label="Text input with segmented dropdown button">
                <button type="button" class="btn btn-outline-secondary" onclick="submit()" >댓글등록</button>
              </div>
              </form>
              <br/>
              <div class="d-flex justify-content-center mx-1 mb-lg-4">
                <button
                  type="button"
                  class="btn btn-outline-primary"
                  style="margin: 3px;"
                  onclick="location.href='board.do'"
                >
                  목록으로
                </button>
				
                <button
                type="button"
                class="btn btn-outline-info"
                style="margin: 3px;"
                onclick="location.href='board.do?m=update&bnum=${board.bnum}&mname=${name}'"
              >
                수정하기
              </button>
			  <button
                type="button"
                class="btn btn-outline-info"
                style="margin: 3px;"
                onclick="location.href='board.do?m=del&bnum=${board.bnum}'"
              >
                삭제하기
              </button>

              </div>
            </div>  
            <div class="col-lg-3"></div>
        </div>
    </section>

  </body>
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">
        Copyright &copy; Your Website 2021
      </p>
    </div>
  </footer>
  <!-- Bootstrap core JS-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/scripts.js"></script>
</html>


<!--<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container row">
        <div class="page-hearder col-md-offset-3" style="padding-bottom: 20px">
            <h1>메인화면</h1>
        </div>
        <div class="col-sm-offset-9">
                <form action="" method="get">
                    <select name="search" id="">
                        <option value="writer">작성자</option>
                        <option value="content">내용</option>
                    </select>
                    <input type="text" name="searchkeyword" id="search">
                    <input class="btn btn-sm btn-default" type="submit" value="검색">
                </form>
            </div>
        <div class="col-md-10 col-md-offset-3">
            <table class="table table-striped ">
                <tr class="text-center">
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                    <th>조회</th>
                </tr>
                <tr>
                    <td class="col-sm-1">1</td>
                    <td><a href="#">안녕하세요. 질문있어요.</a></td>
                    <td>아노미7</td>
                    <td>2017.09.01</td>
                    <td class="col-sm-1">10</td>
                </tr>
            </table>
        </div>
        <div class="btn-group col-md-offset-12">
          <a href="#" class="btn btn-md btn-default">글쓰기</a>
        </div>
        <div class="col-md-offset-6">
			<ul class="pagination">
                <li><a href="getList.do?nowpage=0">처음</a></li>
                <li><a href="#">이전</a></li>
                <li><a href="#">1</a></li>
				<li><a href="#">다음</a></li>
				<li><a href="#">마지막</a></li>
                
            </ul>
        </div>
    </div>
</body>

</html>-->