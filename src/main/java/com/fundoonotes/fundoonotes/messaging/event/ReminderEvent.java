package com.fundoonotes.fundoonotes.messaging.event;

public class ReminderEvent {

    private Long reminderId;
    private Long noteId;
    private String email;
    private String message;

    public ReminderEvent() {
    }

    public ReminderEvent(Long reminderId, Long noteId, String email, String message) {
        this.reminderId = reminderId;
        this.noteId = noteId;
        this.email = email;
        this.message = message;
    }

    public Long getReminderId() {
        return reminderId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }
}