package com.fundoonotes.fundoonotes.dto.response;

import java.time.LocalDateTime;

public class ReminderResponseDto {

    private Long id;
    private Long noteId;
    private LocalDateTime reminderTime;
    private String message;
    private boolean sent;

    public ReminderResponseDto() {
    }

    public ReminderResponseDto(Long id, Long noteId, LocalDateTime reminderTime, String message, boolean sent) {
        this.id = id;
        this.noteId = noteId;
        this.reminderTime = reminderTime;
        this.message = message;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSent() {
        return sent;
    }
}