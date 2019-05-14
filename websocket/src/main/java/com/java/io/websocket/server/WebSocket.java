package com.java.io.websocket.server;

import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/webSocket")
public class WebSocket {

    private Session session;

    private  static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){

        this.session = session;
        webSocketSet.add(this);
        System.out.println("[WebSocket消息]有新的连接，总数:"+webSocketSet.size());
    }

    @OnClose
    public  void  onClose(Session session){
        webSocketSet.remove(this);
        System.out.println("[WebSocket消息]连接断开，总数:"+webSocketSet.size());
    }

    @OnMessage
    public void  onMessage(String message){
        if("123456789".equals(message)){
            sendMessage(message);
        }
        System.out.println("[WebSocket消息]接收到客户端的消息:"+message);
    }

    public void sendMessage(String message){

        for (WebSocket webSocket:webSocketSet){
            System.out.println("【websocket消息】广播消息，message=:"+message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

}

