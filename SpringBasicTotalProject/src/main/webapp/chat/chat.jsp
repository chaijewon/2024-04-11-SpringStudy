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
  margin-top: 50px
}
.row {
   margin: 0px auto;
   width: 600px
}
#chatArea{
  height:500px;
  overflow: auto;
  border:1px solid black;
}
</style> 
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
let name;
function connection()
{
	name=$('#name').val()
	if(name.trim()==="")
	{
		alert("이름을 입력하세요")
		$('#name').focus()
		return
	}
	websocket=new WebSocket("ws://localhost:8080/web/chat/chat-ws");
	websocket.onopen=onOpen
	websocket.onclose=onClose
	websocket.onmessage=onMessage
}
function onOpen(event)
{
	alert("채팅 서버와 연결되었습니다")
}
function onClose(event)
{
	alert("채팅 서버와 연결이 종료가 되었습니다")
}
function onMessage(event)
{
	let data=event.data;
	if(data.substring(0,4)==="msg:") // 100 200 300
	{
		appendMessage(data.substring(4))
	}
}
function appendMessage(msg)
{
	$('#recvMsg').append(msg+"<br>")
	let ch=$('#chatArea').height()
	let m=$('#recvMsg').height()-ch
	$('#chatArea').scrollTop(m)
}
function send()
{
	let msg=$('#sendMsg').val()
	if(msg.trim()==="")
	{
		$('#sendMsg').focus()
		return
	}
	websocket.send('msg:['+name+']'+msg)
	$('#sendMsg').val("")
	$('#sendMsg').focus()
}
$(function(){
	$('#inputBtn').click(function(){
		connection();
	})
	$('#outputBtn').click(function(){
		websocket.close();
	})
	$('#sendBtn').click(function(){
		send()
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode===13) //13=>enter
		{
		   send() 	
		}
	})
})
</script>
</head>
<body>
  <div class="container">
    <h1 class="text-center">실시간 접속자 채팅</h1>
    <div class="row">
      <table class="table">
        <tr>
         <td> 
           <input type=text id="name" size="20" class="input-sm">
           <input type=button value="입장" class="btn btn-sm btn-primary"
             id="inputBtn">
           <input type=button value="퇴장" class="btn btn-sm btn-primary"
             id="outputBtn">  
         </td>
        </tr>
        <tr>
          <td>
            <div id="chatArea">
              <div id="recvMsg"></div>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <input type=text id="sendMsg" size=75 class="input-sm">
            <input type=button value="전송" class="btn btn-sm btn-success"
             id="sendBtn">
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>