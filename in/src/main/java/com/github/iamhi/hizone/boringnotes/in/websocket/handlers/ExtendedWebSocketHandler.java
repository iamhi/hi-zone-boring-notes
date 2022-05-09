package com.github.iamhi.hizone.boringnotes.in.websocket.handlers;

import org.springframework.web.reactive.socket.WebSocketHandler;

public interface ExtendedWebSocketHandler extends WebSocketHandler {

    String getPath();
}
