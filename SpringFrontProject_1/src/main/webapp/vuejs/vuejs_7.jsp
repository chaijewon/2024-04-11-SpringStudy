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
</head>
<%--
   v-for="변수 in 배열"   => for-each만 존재 => 어디부터~어디까지는 문법에 존재하지 않는다 
 --%>
<body>
  <div class="container">
    <div class="row">
      Start:<input type=text size=5 class="input-sm" v-model="startPage">
      &nbsp;
      End:<input type=text size=5 class="input-sm" v-model="endPage">
    </div>
    <div class="row">
     <div class="text-center">
      <ul class="pagination">
        <li><a href="#">&laquo</a></li>
        <li v-for="i in range(startPage,endPage)"><a href="#">{{i}}</a></li>
        <li><a href="#">&raquo</a></li>
      </ul>
     </div>
    </div>
  </div>
  <script>
    let app=Vue.createApp({
    	data(){
    		return {
    			startPage:0,
    			endPage:0
    		}
    	},
    	// 페이지 나누서 반복문 수행 
    	methods:{
    		range(start,end){
        		let arr=[]
        		let len=end-start
        		for(let i=0;i<=len;i++)
        		{
        			arr[i]=start
        			start++
        		}
        		return arr
        	}
    	}
    	
    }).mount(".container")
  </script>
</body>
</html>