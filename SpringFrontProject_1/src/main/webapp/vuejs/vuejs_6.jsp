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
    width: 500px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
</head>
<body>
  <%--
       조건문 
         v-if => 단일 조건문 
         
         v-if ~ v-else => true/false를 나눠서 처리 
         
         v-if ~ v-else-if ~ v-else-if ~ v-else => 다중 조건문 
         
         MVC => 요청 => 데이터 갱신 => 뷰로 전송 
                서버를 거친는 경우마다 새로운 JSP가 생성 
                => init => service => 출력 
                => 새로고침 => 출력된 내용(메모리에서 삭제) => destory 
         MVVM : View 패턴 
                =======================
                |                     |
           => Model + ViewModel + View(HTML)
                |        |
               data(){}  |
                        mounted / methods => 이벤트 처리 
   --%>
  <div class="container" id="app1">
    <h3 class="text-center">조건문(v-if,v-if~v-else)</h3>
    <div class="row">
      <input type="button" value="로그인" class="btn btn-sm btn-danger"
       @click="login2(true)"
      >
      <input type="button" value="로그아웃" class="btn btn-sm btn-danger"
       @click="login2(false)"
      >
      <div v-if="login===true">
        로그인되었습니다
      </div>
      <div v-else>
        로그아웃되었습니다
      </div>
      <!-- <div v-if="login===false">
       
      </div> -->
    </div>
  </div>
  <div class="container" id="app2">
    <div class="row">
     <input type="button" value="한식" class="btn btn-sm btn-danger"
      @click="select(1)"
     >
     <input type="button" value="양식" class="btn btn-sm btn-success"
      @click="select(2)"
     >
     <input type="button" value="중식" class="btn btn-sm btn-primary"
      @click="select(3)"
     >
     <input type="button" value="일식" class="btn btn-sm btn-warning"
      @click="select(4)"
     >
     <input type="button" value="분식" class="btn btn-sm btn-info"
       @click="select(5)"
     >
    </div>
    <div style="height: 10px"></div>
    <div class="row">
     <div v-if="type===1">
       한식을 선택하셨습니다
     </div>
     <div v-else-if="type===2">
       양식을 선택하셨습니다
     </div>
     <div v-else-if="type===3">
       중식을 선택하셨습니다
     </div>
     <div v-else-if="type===4">
       일식을 선택하셨습니다
     </div>
     <div v-else-if="type===5">
       분식을 선택하셨습니다
     </div>
    </div>
  </div>
  <script>
   let app=Vue.createApp({
	   data(){
		  return {
			  login:false
		  }
		   
	   },
	   methods:{
		   login2(log){
			   this.login=log
		   }
	   }
   }).mount('#app1')
   
   let app1=Vue.createApp({
	   data(){
		   return {
			   type:0
		   }
	   },
	   methods:{
		   select(no){
			   this.type=no
		   }
	   }
   }).mount('#app2')
  </script>
</body>
</html>