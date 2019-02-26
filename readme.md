# springboot + websocket做的简单聊天工具

### 1. 默认端口号 `9000` 
端口号冲突的话，自行在 `application.yml` 中修改

### 2. 修改 `index.html` 中 `webservice` 地址

把
```js
websocket = new WebSocket('ws://www.coco-z.com:9000/webSocket');
```
修改为自己的地址（本机直接localhost就可以）如：
```js
websocket = new WebSocket('ws://localhost:9000/webSocket');
```


### 3. 启动工程，直接访问 [localhost:9000](localhost:9000) 查看效果


