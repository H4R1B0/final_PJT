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
      href="${pageContext.request.contextPath}/assets/css/error.css"    />
  </head>
  <%@ include file="/include/header.jsp" %>
  <body>
    <main class="error">
     <h1>오류가 발생하였습니다.</h1>
     <div>
     	<h3>자세한 내용: ${errMsg}</h3>
     	<a></a>
     </div>
     
    </main>
  </body>
  <%@ include file="/include/footer.jsp" %>
</html>