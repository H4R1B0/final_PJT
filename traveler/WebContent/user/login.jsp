<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/user.css"
    />
  </head>
  <link rel="icon" href="data:;base64,iVBORw0KGgo=" />
  <%@ include file="/include/header.jsp" %>
  <body>
    <main class="login">
      <div class="content">
        <h1>로그인</h1>
        <section class="section">
          <form
            action="${pageContext.request.contextPath}/UserController"
            method = post
            id="login"
          >
            <div class="info">
              <p>
                <input type="hidden" name="action" value="login" />
              </p>
              <p>
                아이디<br /><input
                  type="text"
                  name="id"
                  autofocus
                  placeholder=" 아이디를 입력하시오"
                  value="${cookie.id.value}"
                />
              </p>
              <p>
                비밀번호<br /><input
                  type="password"
                  name="pwd"
                  placeholder=" 비밀번호"
                />
              </p>
            </div>
            <div class="option">
              <p>
                <input
                  type="checkbox"
                  name="remainLogin"
                  id="remainLogin"
                /><label for="remainLogin"> 아이디 기억하기</label>
              </p>
              <a href="${pageContext.request.contextPath}/user/signin.jsp">
                아직 회원이 아니신가요?</a
              >
              <a href="${pageContext.request.contextPath}/user/signin.jsp"
                >이메일로 회원가입
              </a>
            </div>
            <input type="submit" class="submit" value="로그인" />
          </form>
        </section>
      </div>
    </main>

    <%@ include file="/include/footer.jsp" %>
  </body>
</html>
