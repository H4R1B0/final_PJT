<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/include.css" />
<header>
      <nav>
        <div class="first_header">
          <ul class="logo_menu">
            <li>
              <a href="${pageContext.request.contextPath}"
                ><img
                  src="${pageContext.request.contextPath}/assets/img/logo.png"
                  alt="logo"
                  class="logo_img"
                  width="120"
                />
              </a>
            </li>
            <li>
              <div class="search">
                <input
                  type="text"
                  placeholder="관광지를 검색해보세요"
                  style="background-color: rgb(236, 236, 236)"
                />
                <img
                  id="search_img"
                  src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"
                />
              </div>
            </li>
          </ul>
          <ul class="login_menu">
          <c:choose>
			<c:when test="${empty sessionScope.userInfo}">
				<li><a href="${pageContext.request.contextPath}/user/login.jsp">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/signin.jsp">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/mypage.jsp">마이페이지</a></li>
				<li><a href="${pageContext.request.contextPath}/UserController?action=logout">로그아웃</a></li>
			</c:otherwise>
			</c:choose>
            
            
          </ul>
        </div>
      </nav>
      <nav>
        <ul class="user_menu">
          <li class="user_menu_item">
            <a href="${pageContext.request.contextPath}/user/mypage"
              ><img
                src="${pageContext.request.contextPath}/assets/img/airplane.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              항공권</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/loading.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              숙소</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/tour.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              투어 티켓</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/rentcar.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              국내 렌터카</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#">
              <img
                src="${pageContext.request.contextPath}/assets/img/roulette.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              럭키드로우</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/package.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              패키지</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/ai.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              AI 여행플래너</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/hotel.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              호텔 특가</a
            >
          </li>
          <li class="user_menu_item">
            <a href="#"
              ><img
                src="${pageContext.request.contextPath}/assets/img/insurance.png"
                alt="logo"
                class="nav_icon"
                width="20"
                height="20"
              />
              여행자 보험</a
            >
          </li>
        </ul>
      </nav>
  </header>
  <hr/>

