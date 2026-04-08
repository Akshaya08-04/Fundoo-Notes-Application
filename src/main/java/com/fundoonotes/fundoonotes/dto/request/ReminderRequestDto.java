package com.fundoonotes.fundoonotes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReminderRequestDto {

    @NotNull(message = "Note id is required")
    private Long noteId;

    @NotNull(message = "Reminder time is required")
    private LocalDateTime reminderTime;

    @NotBlank(message = "Message is required")
    private String message;

    public ReminderRequestDto() {
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(LocalDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
