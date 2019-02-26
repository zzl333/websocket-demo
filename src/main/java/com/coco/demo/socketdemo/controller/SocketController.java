package com.coco.demo.socketdemo.controller;


import com.coco.demo.socketdemo.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzz
 */
@RestController
public class SocketController {

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("/")
    public String sendMessage() {
        return "启动成功！";
    }

    @RequestMapping("send")
    public String send(String msg) {
        webSocket.sendMessage(msg);
        return "消息发送成功！消息：{" + msg + "}";
    }

    @RequestMapping("close")
    public String close() {
        webSocket.onClose();
        return "关闭连接成功！";
    }

}
