package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.ActionOperation;
import com.bbquantum.smartfarming.Constants.ActionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class IrrigationAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long actionId;

    @OneToOne(mappedBy = "action")
    private Decisions decision;

    @Enumerated(EnumType.STRING)
    private ActionOperation action;

    private LocalDateTime timeOfExecution;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    private String actionStatusMessage;

    public IrrigationAction() {}

    public long getActionId() {
        return actionId;
    }

    public Decisions getDecision() {
        return decision;
    }

    public void setDecision(Decisions decision) {
        this.decision = decision;
    }

    public ActionOperation getAction() {
        return action;
    }

    public void setAction(ActionOperation action) {
        this.action = action;
    }

    public LocalDateTime getTimeOfExecution() {
        return timeOfExecution;
    }

    public void setTimeOfExecution(LocalDateTime timeOfExecution) {
        this.timeOfExecution = timeOfExecution;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getActionStatusMessage() {
        return actionStatusMessage;
    }

    public void setActionStatusMessage(String actionStatusMessage) {
        this.actionStatusMessage = actionStatusMessage;
    }
}
