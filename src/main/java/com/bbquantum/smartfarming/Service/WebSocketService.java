package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.DTO.WebSocketDTO.AlertData;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAlerts(AlertData alertData) {
        messagingTemplate.convertAndSend(
                "/topic/alerts", alertData
        );
    }
}
