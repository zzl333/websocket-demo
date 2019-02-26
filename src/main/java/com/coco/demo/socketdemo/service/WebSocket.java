package com.coco.demo.socketdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wangzz
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private static Map<String, Object> namesMap = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        setname(null);
        webSocketSet.add(this);
        log.error("【websocket消息】 有新的连接，总数：{}", webSocketSet.size());
        sendMessage(namesMap.get(session.getId()) + "来了~ 共：" + webSocketSet.size() + " 人在聊天");
    }

    @OnClose
    public void onClose() {
        sendMessage(namesMap.get(session.getId()) + "跑路了~ 共：" + (webSocketSet.size() - 1) + " 人在聊天");
        webSocketSet.remove(this);
        namesMap.remove(session.getId());
        log.error("【websocket消息】 连接断开，总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        String sp = "::";
        if (message.indexOf(sp) >= 0) {
            String[] split = message.split(sp);
            if (split.length > 0) {
                setname(split[0]);
                log.error("【websocket设置用户名】：{}", split[0]);
            }
            return;
        }
        log.error("【websocket消息】 收到客户端：{}，发来的消息：{}", namesMap.get(session.getId()), message);

        // 接受到消息之后在发送到前台
        try {
            // 给当前websocket链接发送
            //session.getBasicRemote().sendText(namesMap.get(session.getId()) + "：" + message);
            // 广播发送
            sendMessage(namesMap.get(session.getId()) + "：" + message);
        } catch (Exception e) {
            log.error("【websocket消息】发送异常~");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.error("【websocket消息】 广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setname(String name) {
        String id = session.getId();
        if (name == null) {
            name = "【路人" + id + "】";
        } else {
            name = "【" + name + "】";
        }
        namesMap.put(id, name);
        try {
            session.getBasicRemote().sendText("【系统消息】用户名设置成功！用户名：" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
