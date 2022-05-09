package com.github.iamhi.hizone.boringnotes.in.websocket.handlers;

import com.github.iamhi.hizone.boringnotes.core.ImpatientListenerService;
import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

@Service
public record BasicExtendedWebSocketHandler(
    ImpatientListenerService impatientListenerService,
    InnocentSupplierService innocentSupplierService
) implements ExtendedWebSocketHandler {

    private static final String WS_ROUTE = "/basic";

    @Override
    public String getPath() {
        return WS_ROUTE;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        SheepDTO sheepDTO = (SheepDTO) session.getAttributes().get("sheep");

        session.receive()
            .map(webSocketMessage -> new UserInputDTO(
                sheepDTO,
                webSocketMessage.getPayloadAsText()
            ))
            .flatMap(impatientListenerService::handleUserInput).subscribe();

        return session.send(
            innocentSupplierService.getMessages(sheepDTO)
                .doOnError(err -> System.out.println(err.getMessage()))
                .map(session::textMessage)
                .doFinally(sig -> impatientListenerService.disconnect(sheepDTO).subscribe()));

    }
}