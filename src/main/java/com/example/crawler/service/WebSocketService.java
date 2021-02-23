package com.example.crawler.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 让Spring创建WebSocket的服务端点，请求地址为"/ws"
 */
@Service
@ServerEndpoint("/ws")
@Slf4j
public class WebSocketService {
    private Session session;

    /**
     * 客户端打开WebSocket服务端点调用方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        log.info("连接建立成功方法调用");
    }

    /**
     * 客户端关闭WebSocket服务端点调用方法
     */
    @OnClose
    public void onClose() {
        log.info("连接关闭方法调用");
    }

    /**
     * 客户端发送消息，WebSocket服务端点调用方法
     *
     * @param msg 来自客户端的信息
     */
    @OnMessage
    public void onMessage(String msg) throws IOException {
        this.session.getBasicRemote().sendText("服务器端发来的消息啦：你好啊");
        log.info("客户端消息:{}", msg);
    }

    /**
     * 客户端请求WebSocket服务端点异常方法
     */
    public void onError() {
        log.info("错误发生时调用");
    }
}
