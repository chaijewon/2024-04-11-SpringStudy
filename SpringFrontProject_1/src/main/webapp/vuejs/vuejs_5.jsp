<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
    margin: 0px auto;
    width: 960px;
}
.movieTr:hover{
   cursor: pointer
}

</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
  <div class="container">
   <div class="row">
    <div class="col-sm-7">
       <table class="table">
        <thead>
         <tr>
           <th>순위</th>
           <th></th>
           <th>영화명</th>
           <th>감독</th>
           <th>장르</th>
         </tr>
        </thead>
        <tbody>
         <%--
              vo={"rank":1,"movieNm":"aaa"} => JSON
                 vo.rank
              class A
              {
                 int rank;
                 String movieNm;
              }
          --%>
         <tr v-for="vo in movie_list" class="movieTr" v-on:mouseover="detail_data(vo)">
           <td>{{vo.rank}}</td>
           <td>
             <img :src="'https://www.kobis.or.kr'+vo.thumbUrl" style="width: 35px;height: 35px">
           </td>
           <td>{{vo.movieNm}}</td>
           <td>{{vo.genre}}</td>
           <td>{{vo.watchGradeNm}}</td>
         </tr>
        </tbody>
       </table>
    </div>
    <div class="col-sm-5" v-show="isShow">
      <table class="table">
          <tr>
           <td width=30% class="text-center" rowspan="7">
             <img :src="'https://www.kobis.or.kr'+movie_detail.thumbUrl" style="width: 100%">
           </td>
           <td colspan="2">
             <h3>{{movie_detail.movieNm}}</h3>
           </td>
          </tr>
          <tr>
           <td width=20%>감독</td>
           <td width=50%>{{movie_detail.director}}</td>
          </tr>
          <tr>
           <td width=20%>국가</td>
           <td width=50%>{{movie_detail.repNationCd}}</td>
          </tr>
          <tr>
           <td width=20%>장르</td>
           <td width=50%>{{movie_detail.genre}}</td>
          </tr>
          <tr>
           <td width=20%>등급</td>
           <td width=50%>{{movie_detail.watchGradeNm}}</td>
          </tr>
          <tr>
           <td width=20%>개봉일</td>
           <td width=50%>{{movie_detail.startDate}}</td>
          </tr>
          <tr>
           <td width=20%>줄거리</td>
           <td width=50%>{{movie_detail.synop}}</td>
          </tr>
        </table> 
    </div>
   </div>
  </div>
  <script src="js/movie.js"></script>
</body>
</html>