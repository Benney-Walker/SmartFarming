package com.bbquantum.smartfarming.DTO.WebSocketDTO;

public class AlertData {

    private String alertTime;

    private String alertMessage;

    private String actionSeverity;

    public AlertData(String alertTime, String alertMessage, String actionSeverity) {
        this.alertTime = alertTime;
        this.alertMessage = alertMessage;
        this.actionSeverity = actionSeverity;
    }

    public String getAlertTime() {
        return alertTime;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public String getActionSeverity() {
        return actionSeverity;
    }
}
