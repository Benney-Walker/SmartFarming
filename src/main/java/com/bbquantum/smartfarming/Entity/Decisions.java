package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.Anomaly;
import com.bbquantum.smartfarming.Constants.IrrigationDecision;
import com.bbquantum.smartfarming.Constants.Models;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Decisions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long decisionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Fields_fieldId")
    private Fields field;

    private LocalDateTime decisionTime;

    @Enumerated(EnumType.STRING)
    private IrrigationDecision irrigationDecision;

    private String decisionMessage;

    private String decisionReason;

    @Enumerated(EnumType.STRING)
    private Anomaly anomaly;

    @Enumerated(EnumType.STRING)
    private Models model;

    @OneToOne(mappedBy = "decision")
    private IrrigationAction action;

    public Decisions() {}

    public long getDecisionId() {
        return decisionId;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
    }

    public LocalDateTime getDecisionTime() {
        return decisionTime;
    }

    public void setDecisionTime(LocalDateTime decisionTime) {
        this.decisionTime = decisionTime;
    }

    public IrrigationDecision getIrrigationDecision() {
        return irrigationDecision;
    }

    public void setIrrigationDecision(IrrigationDecision irrigationDecision) {
        this.irrigationDecision = irrigationDecision;
    }

    public String getDecisionMessage() {
        return decisionMessage;
    }

    public void setDecisionMessage(String decisionMessage) {
        this.decisionMessage = decisionMessage;
    }

    public String getDecisionReason() {
        return decisionReason;
    }

    public void setDecisionReason(String decisionReason) {
        this.decisionReason = decisionReason;
    }

    public Anomaly getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(Anomaly anomaly) {
        this.anomaly = anomaly;
    }

    public Models getModel() {
        return model;
    }

    public void setModel(Models model) {
        this.model = model;
    }

    public IrrigationAction getAction() {
        return action;
    }

    public void setAction(IrrigationAction irrigationAction) {
        this.action = irrigationAction;
    }
}
