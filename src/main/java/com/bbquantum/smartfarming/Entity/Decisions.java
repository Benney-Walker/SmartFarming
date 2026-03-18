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
    private IrrigationAction irrigationAction;
}
