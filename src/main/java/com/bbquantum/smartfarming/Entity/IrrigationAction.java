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
}
