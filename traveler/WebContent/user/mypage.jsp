<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/user.css"
    />
  </head>
  <%@ include file="/include/header.jsp" %>
  <body>
    <main class="userInfo">
      <div class="content">
        <h1>마이페이지</h1>
        
        <section class="section">
        
          <form
            action="${pageContext.request.contextPath}/UserController"
            method="post"
            id="userInfo"
          >
            <div class="info">
              <input type="hidden" id="pw" value='${userInfo.pwd}' />
              <h2>기존정보 변경하기</h2>
              <p>
                아이디<br /><input
                  type="text"
                  name="id"
                  value = '${userInfo.id}'
                  autofocus
                  readonly
                  id="id"
                  placeholder=" 아이디를 입력하시오"
                  
                />
              </p>
              <p>
                이메일<br /><input
                  type="email"
                  name="email"
                  id="email"
                  value = '${userInfo.email}'
                  placeholder=" 이메일"
                />
              </p>
              
              
        <h2>비밀번호 변경하기</h2>
              <p>
                기존 비밀번호<br /><input
                  type="password"
                  name="oldpwd"
                  id="oldpwd"
                  placeholder="기존 비밀번호"
                />
              </p>
              <p>
                비밀번호<br /><input
                  type="password"
                  name="pwd"
                  id="pwd"
                  placeholder=" 비밀번호"
                />
              </p>
              <p>
                비밀번호 확인<br /><input
                  type="password"
                  name="pwd2"
                  id="pwd2"
                  placeholder=" 비밀번호 확인"
                />
              </p>
            </div>
			<div>
              <p>
                <input
                  type="checkbox"
                  id="advertisement"
                  name="advertisement"
                  checked = ${userInfo.advertisement}
                /><label for="advertisement">
                  특가항공권 및 할인 혜택안내 동의(선택)</label
                >
              </p>
            </div>
            <input type="submit" class="submit" value="회원정보수정" />
            
            <input type="button" id="delete" class="delete" value="회원탈퇴" />
          </form>
        </section>
      </div>
    </main>
  </body>
  <script src="${pageContext.request.contextPath}/assets/js/userInfo.js"></script>
  <%@ include file="/include/footer.jsp" %>
</html>