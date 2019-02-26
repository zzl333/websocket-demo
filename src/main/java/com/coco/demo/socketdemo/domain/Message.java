package com.coco.demo.socketdemo.domain;

import lombok.Data;

/**
 * @author wangzz
 */
@Data
public class Message {

    // socket id
    private String socketId;
    //名字
    private String name;
    // 消息
    private String msg;

}
