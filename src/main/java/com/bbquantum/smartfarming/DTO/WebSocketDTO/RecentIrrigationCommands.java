package com.bbquantum.smartfarming.DTO.WebSocketDTO;

public class RecentIrrigationCommands {

    private String commandTime;

    private String field;

    private String command;

    private String triggerSource;

    public RecentIrrigationCommands(String commandTime, String field, String command, String triggerSource) {
        this.commandTime = commandTime;
        this.field = field;
        this.command = command;
        this.triggerSource = triggerSource;
    }

    public String getCommandTime() {
        return commandTime;
    }

    public String getField() {
        return field;
    }

    public String getCommand() {
        return command;
    }

    public String getTriggerSource() {
        return triggerSource;
    }
}
