package com.coco.demo.socketdemo.controller;


import com.coco.demo.socketdemo.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketController {

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("wangzz")
    public String sendMessage() {
        webSocket.sendMessage("hello websocket!");
        return "发送成功！";
    }
}
