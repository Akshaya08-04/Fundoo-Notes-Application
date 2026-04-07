package com.fundoonotes.fundoonotes.dto.request;

import jakarta.validation.constraints.NotBlank;

public class NoteRequestDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    public NoteRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
