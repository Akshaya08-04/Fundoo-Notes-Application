package com.fundoonotes.fundoonotes.dto.response;

public class NoteResponseDto {

    private Long id;
    private String title;
    private String description;
    private boolean pinned;
    private boolean archived;
    private boolean trashed;

    public NoteResponseDto() {
    }

    public NoteResponseDto(Long id, String title, String description, boolean pinned, boolean archived, boolean trashed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pinned = pinned;
        this.archived = archived;
        this.trashed = trashed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPinned() {
        return pinned;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isTrashed() {
        return trashed;
    }
}