package com.coco.demo.socketdemo.controller;


import com.coco.demo.socketdemo.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangzz
 */
@Controller
public class SocketController {

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("/")
    public String sendMessage() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/send")
    public String send(String msg) {
        webSocket.sendMessage(msg);
        return "消息发送成功！消息：{" + msg + "}";
    }

}
