package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.ActionStatus;
import com.bbquantum.smartfarming.Constants.LogActions;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SystemActivityLog {

    @Id
    private String logId;

    private LocalDateTime timeOfActivity;

    @Enumerated(EnumType.STRING)
    private LogActions actionType;

    @Column(length = 1000)
    private String message;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    public SystemActivityLog() {}

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public LocalDateTime getTimeOfActivity() {
        return timeOfActivity;
    }

    public void setTimeOfActivity(LocalDateTime timeOfActivity) {
        this.timeOfActivity = timeOfActivity;
    }

    public LogActions getActionType() {
        return actionType;
    }

    public void setActionType(LogActions actionType) {
        this.actionType = actionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }
}
