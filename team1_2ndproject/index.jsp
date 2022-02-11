<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
     <script language="javascript">
       function f_join()
       {
           baby_login = window.open(
           "togather/join.jsp", "join_name", 
                "width=600, height=700, top=100, left=100");
       }
    </script>
    <script language="javascript">
       function f_login()
       {
           baby_login = window.open(
           "togather/login.jsp", "login_name", 
                "width=500, height=300, top=100, left=100");
       }
    </script>
    <script language="javascript">
       function f_fp()
       {
           baby_login = window.open(
           "togather/findpassword.jsp", "login_name", 
                "width=500, height=300, top=100, left=100");
       }
    </script>
    내아이디 : ${userid}
    <link type="text/css" rel="stylesheet" href="pcss/7.css" />
    <link
      rel="stylesheet"
      href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
      integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
      crossorigin="anonymous"
    />
    <div>
      <input id="sf_draw_type" type="hidden" value="pc" /><input
        id="sf_store_name"
        type="hidden"
        value="superstari"
      />
    </div>

    <!-- snap common end -->
    <link type="text/css" rel="stylesheet" href="pcss/6.css" />
    <!-- #wrap -->
    <div id="wrap">
      <link type="text/css" rel="stylesheet" href="pcss/5.css" />
      <span id="sfsnapfit_store_id" style="display: none">ToGather</span>
      <div>
        <input id="sf_draw_type" type="hidden" value="pc" /><input
          id="sf_store_name"
          type="hidden"
          value="superstari"
        />
      </div>

      <!-- #hd -->
      <div id="hd">
        <div class="hd-navi">
          <div class="container">
            <div class="hd-top">
              <h1 class="logo">
                <a href="/index.html"
                  ><img
                    src="./imgs/topLogo.png"
                    alt="ToGather"
                    title="홈으로 이동"
                /></a>
              </h1>
              <ul class="top-menu">
                <!-- snap common end -->

                <li><a id ="login" href="javascript:f_login()">LOGIN</a></li>

                <li>
                  <a id="join" href="javascript:f_join()">JOIN</a>
                  <!-- 회원가입 포인트 -->
                <!-- 
                  <div id="joinpoint" style="top: 25.7163px">
                    <span class="point">회원가입 하기</span>
                  </div>
                 -->
                </li>
                <li><a href="찜목록 링크">CART</a></li>

                <li>
                  <a href="마이페이지 링크">MYPAGE</a>
                </li>
              </ul>
            </div>
            <div class="hd-menu">
              <div id="gnb">
                <ul class="hd-cate">
                  <li><a href="board/board.do">김진운(Board)</a></li>
                  <li><a href="member/login.do">김지수</a></li>
                  <li><a href="">조현기</a></li>
                  <li><a href="">박범수</a></li>
                  <li><a href="">송보석</a></li>
                  <li><a href="">최대현</a></li>
                  <li><a href="">1팀</a></li>
                  <li><a href="">프로젝트</a></li>
                  <li><a href="togather/groupTab.do?m=groupList">모임</a></li>
                  <li><a href="">넣을거</a></li>
                  <li><a href="">없음</a></li>
                  <li><a href="">아무말</a></li>
                  <li><a href="">대잔치</a></li>

                  <!--<li><a href="/shop/shopbrand.html?xcode=010">수트</a></li>-->
                </ul>
              </div>
              <div class="hd-icon-box">
                <div class="hd-search">
                  <form
                    action="/shop/shopbrand.html"
                    method="post"
                    name="search"
                  >
                    <fieldset>
                      <legend>상품 검색 폼</legend>
                      <input
                        name="search"
                        onkeydown="CheckKey_search();"
                        value=""
                        class="MS_search_word input-keyword"
                      />
                      <a href="javascript:search_submit();"
                        ><img
                          src="/images/d3/hot_issue/btn/search_keyword.gif"
                          alt="검색"
                          title="검색"
                      /></a>
                    </fieldset>
                  </form>
                </div>
                <div class="icon-menu-box">
                  <ul class="icon">
                    <li class="btn_menu"><a href="">전체메뉴</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </head>
  <body></body>
</html>