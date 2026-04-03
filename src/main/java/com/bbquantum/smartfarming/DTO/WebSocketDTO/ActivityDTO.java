package com.bbquantum.smartfarming.DTO.WebSocketDTO;

public class ActivityDTO {

    private String activityTime;

    private String actionType;

    private String message;

    private String status;

    public ActivityDTO(String activityTime, String actionType, String message, String status) {
        this.activityTime = activityTime;
        this.actionType = actionType;
        this.message = message;
        this.status = status;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public String getActionType() {
        return actionType;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
