package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.DTO.WebSocketDTO.ActivityDTO;
import com.bbquantum.smartfarming.DTO.WebSocketDTO.AlertData;
import com.bbquantum.smartfarming.DTO.WebSocketDTO.RecentIrrigationCommands;
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

    public void sendActivities(ActivityDTO activityDTO) {
        messagingTemplate.convertAndSend(
                "/topic/activities", activityDTO
        );
    }

    public void sendRecentIrrigation(RecentIrrigationCommands recentIrrigationCommands) {
        messagingTemplate.convertAndSend(
                "/topic/recent_irrigation", recentIrrigationCommands
        );
    }
}
