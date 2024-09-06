<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.do">SpringMVC</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">레시피
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="../recipe/chef_list.do">쉐프</a></li>
          <%--
              순서 
              1. .do 링크 => 요청   
              2. @Controller 
                  => .do가 들어오면 처리 
              3. 처리 => Mapper => DAO => Service => ServiceImpl
              4. @Controller => DAO연동 => 결과값 => JSP로 전송
              5. JSP에서 화면 출력 
              =================================================
              1. 동작방식 => 실제 동작 확인 
              2. 코딩 순서 
              3. 검색 
           --%>
          <li><a href="../recipe/find.do">레시피 찾기</a></li>
        </ul>
      </li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">맛집
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">전체 맛집</a></li>
          <li><a href="#">맛집 찾기</a></li>
        </ul>
      </li>
      <!-- 작업 -->
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">스토어
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">전체 상품</a></li>
          <li><a href="#">상품 찾기</a></li>
        </ul>
      </li>
      <li><a href="#">답변형 게시판</a></li>
    </ul>
  </div>
</nav>
</body>
</html>