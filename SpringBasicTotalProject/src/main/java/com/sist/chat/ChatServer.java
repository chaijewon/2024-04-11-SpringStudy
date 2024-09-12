package com.sist.chat;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
// => 추가 
@ServerEndpoint("/chat/chat-ws")
public class ChatServer {
    //사용자 저장 공간 
	// synchronizedList : 동기화  
   private static List<Session> users=
		   Collections.synchronizedList(new ArrayList<Session>());
   // 접속시마다 Session => 번호 
   // 1. 접속시에 처리 
   @OnOpen
   // 2. 메세지 전송시 처리 
   public void onOpen(Session session)
   {
	   users.add(session); // 접속자 저장 
	   System.out.println("클라이언트 접속자:"+session.getId());
   }
   @OnMessage
   public void onMessage(String message,Session session)
   throws Exception
   {
	   System.out.println(session.getId()+"님의 메세지:"+message);
	   for(Session s:users)
	   {
		   s.getBasicRemote().sendText(message);
		   // 접속마다 메세지를 전송 
	   }
   }
   // 3. 퇴장시에 처리 
   @OnClose
   public void onClose(Session session)
   {
	   users.remove(session);
	   System.out.println("클라이언트 퇴장:"+session.getId());
   }
}
