package com.hiumx.bookingbackend.service.impl;

import com.corundumstudio.socketio.SocketIOServer;
import com.hiumx.bookingbackend.service.SocketIOService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketIOServiceImpl implements SocketIOService {

//    private final SocketIOServer server;
//
//    @Autowired
//    public SocketIOServiceImpl(SocketIOServer server) {
//        this.server = server;
//    }
//
//    @PostConstruct
//    private void startServer() {
//        server.start();
//    }
//
//    @PreDestroy
//    private void stopServer() {
//        server.stop();
//    }
//    @Override
//    public void sendPaymentResponse(String roomId, Object paymentResponse) {
//        server.getRoomOperations(roomId).sendEvent("paymentResponse", paymentResponse);
//    }
}
