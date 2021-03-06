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
    <title>ToGather Q&A</title>
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
            ><img src="../imgs/topLogo.png" alt="ToGather" title="????????? ??????"
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
                >ToGather????</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="notice.jsp">????????????</a>
            </li>
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle"
                id="navbarDropdown"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
                >???????????? ??????</a
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
              class="btn btn-outline-dark"
              type="button"
              onclick="location.href='javascript:f_login()'"
            >
              <i class="bi bi-person-fill"></i>
              ?????????
            </button>
            <% }else {%>
            <button id = "logout" class="btn btn-outline-dark" style="margin-right:10px"type="button" onclick="location.href='javascript:signout()'">
              <i class="bi bi-person-fill"></i>
              ????????????
            </button>
      		<%}%>
            <button
              class="btn btn-outline-primary"
              type="button"
              onclick="location.href='javascript:f_join()'"
            >
              <i class="bi bi-person-plus-fill"></i>
              ????????????
            </button>
            <button
              class="btn btn-outline-dark"
              type="button"
              onclick="location.href='wish.jsp'"
            >
              <i class="bi-cart-fill me-1"></i>
              ???
              <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
            </button>
            <!--???????????? ??????. ??????????????? ??????????????? ????????? alert ?????????-->
            <button
              class="btn btn-outline-danger"
              type="button"
              onclick="location.href='../group/groupInput.jsp'"
            >
              <i class="bi bi-people-fill"></i>
              ?????? ?????????
            </button>
          </form>
        </div>
      </div>
    </nav>
    <section class="vh-100" style="background-color: #eee">
      <div class="container h-75 contents-center">
        <div class="row justify-content-center align-items-center h-75">
          <div class="col-lg-2"></div>
          <div class="col-lg-8">
            <p class="text-center h1 fw-bold mb-1 mx-1 mx-md-4 mt-3">
              Q & A
            </p>
            <!--???????????? ????????? ????????? ??????-->
            <div class="button_group">
              <button
              class="btn btn-outline-primary btn-sm dropdown-toggle mb-1 mx-md-0"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              style="float: left"
            >
              ???????????? ???
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">5</a></li>
              <li><a class="dropdown-item" href="#">10</a></li>
              <li><a class="dropdown-item" href="#">15</a></li>
            </ul>
              <button
                type="button"
                class="btn btn-primary btn-sm mb-1"
                style="float: right"
                onclick="location.href='Q&AInput.jsp'"
              >
                ?????????
              </button>
            </div>
            <table
              class="table table-striped table-hover table-bordered contents-center text-center"
            >
              <thead class="table-warning">
                <tr>
                  <th scope="col" style="width: 20px; height: auto;">#</th>
                  <th scope="col" style="width: 200px; height: auto">??????</th>
                  <th scope="col" style="width: 60px; height: auto">?????????</th>
                  <th scope="col" style="width: 50px; height: auto">?????????</th>
                  <th scope="col" style="width: 10px; height: auto">?????????</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">3</th>
                  <td>
                    <a href="Q&AContent.jsp" style="text-decoration:none; color:black">????????? ????????? ???????????????</a>
                  </td>
                  <td>?????????</td>
                  <td>2021/12/13</td>
                  <td>14</td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td><a href="Q&AContent.jsp" style="text-decoration:none; color:black">?????? ?????? ????????? ????????? ??????????</a></td>
                  <td>?????????</td>
                  <td>2021/12/24</td>
                  <td>52</td>
                </tr>
                <tr>
                  <th scope="row">1</th>
                  <td>
                    <a href="Q&AContent.jsp" style="text-decoration:none; color:black">??????????????? ??????????????????</a>
                  </td>
                  <td>?????????</td>
                  <td>2022/01/23</td>
                  <td>51</td>
                </tr>
              </tbody>
            </table>
            <nav aria-label="Page navigation example">
              <ul
                id="notice_page"
                class="pagination pagination-sm"
                style="justify-content: center"
              >
                <li class="page-item">
                  <a class="page-link" href="#">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                  <a class="page-link" href="#">Next</a>
                </li>
              </ul>
            </nav>
          </div>
        <div class="col-lg-2"></div>
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
