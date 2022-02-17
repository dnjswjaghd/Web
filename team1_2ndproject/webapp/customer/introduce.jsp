<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
    <title>ToGather</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/ToGather.ico" />
    <!-- Bootstrap icons-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />

    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet" />
    <link href="../css/groupList.css" rel="stylesheet" />
    <script src="../js/main.js" defer></script>
    <script src="../js/splitting.js"></script>
    <script src="../js/typed.js"></script>
  </head>
  <script >
       function f_login()
       {
           baby_login = window.open(
           "../member/login2.jsp", "login_name", 
                "width=600, height=900, top=100, left=100");
       }
    </script>


<script>
       function f_join()
       {
           baby_login = window.open(
           "../member/join2.jsp", "join2_name", 
                "width=600, height=1100, top=100, left=100");
       }
    </script>

<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
      <script>
      Kakao.init('11400a9267d93835389eb9255fcaad0b');
      function signout(){
        if(Kakao.Auth.getAccessToken() != null){
    	  Kakao.Auth.logout(function(){
    	    setTimeout(function(){
              location.href="../member/sessionLogout.jsp";
           },500);
         });
        }else{
        	location.href="../member/sessionLogout.jsp";
        }
      }
      </script>
  <body>
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light bg-gray">
      <div class="container px-4 px-lg-5">
        <h1 class="logo">
          <a href="../"
            ><img src="../imgs/topLogo.png" alt="ToGather" title="홈으로 이동"
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
                href="introduce.jsp"
                >ToGather란?</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="notice.jsp">공지사항</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                id="navbarDropdown"
                href="Q&A.jsp"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                >자주하는 질문</a
              >
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <a class="dropdown-item" href="FAQ.jsp">FAQ</a>
                </li>
                <li>
                  <a class="dropdown-item" href="Q&A.jsp">Q&A</a>
                </li>
              </ul>
            </li>
          </ul>
           <form class="d-flex">
            <% 
            String userid=(String)session.getAttribute("userid");
            if(userid==null){    
            %>
            <button
              class="btn btn-outline-dark mx-1"
              type="button"
              onclick="location.href='javascript:f_login()'">
              <i class="bi bi-person-fill"></i>
              로그인
            </button>
            <button
              class="btn btn-outline-primary mx-1"
              type="button"
              onclick="location.href='javascript:f_join()'">
              <i class="bi bi-person-plus-fill"></i>
              회원가입
            </button>
            <% }else {%>
            <button id = "logout" class="btn btn-outline-dark" style="margin-right:10px"type="button" onclick="location.href='javascript:signout()'">
              <i class="bi bi-person-fill"></i>
              로그아웃
            </button>
            <button
              class="btn btn-outline-dark mx-1"
              type="button"
              onclick="location.href='wish.jsp'"
            >
              <i class="bi-cart-fill me-1 mx-1"></i>
              찜
              <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
            </button>
            <button
              class="btn btn-outline-danger mx-1"
              type="button"
              onclick="location.href='../group/groupInput.jsp'"
            >
              <i class="bi bi-people-fill"></i>
              모임 만들기
            </button>
			<%}%>
          </form>
        </div>
      </div>
    </nav>
    <div class="contents_center">
        <h1 class="contents_logo">
                <img src="../imgs/topLogoBig.png" alt="ToGather" />
          </h1>
    <div class="frame-71-C61RwL anima-animate-enter4">
        <div class="no1-cwimim-aeb-somoim-Qtr366 font-class-3">No.1 취미모임 웹페이지, ToGather!</div>
        <div class="somoimeun--ssseubnida-Qtr366 font-class-1">
          ToGather는 지역별 관심사별 오프라인 모임을 할 수 있는 웹 커뮤니티 플랫폼서비스입니다. <br>함께 책을 읽고
          토론하는 독서모임부터, 서핑을 사랑하는 서퍼모임, UMF·Spectrum·WDF등 <br>페스티벌 마니아 모임, 함께 모여
          배우는 공예 핸드메이드 모임, 주말엔 봉사활동하는 봉사모임, <br>서로 배우고 시너지를 내는 창업모임 같은
          다양한 모임들이 활동하고 있습니다.<br><br>소모임 크루와 함께 취미생활을 하며 워라밸을 찾고, 소소하지만
          확실한 행복을 경험해보세요. <br>ToGather 팀은&nbsp;&nbsp;‘커뮤니티가 많아질수록 세상은 더 행복해진다’는 확고한
          VISION을 갖고, <br>누구나 쉽게 자신의 지역과 관심사에 맞는 모임을 찾을 수 있도록 최선을 다하고 있습니다.
        </div>
      </div>
    </div>
    </div>

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


