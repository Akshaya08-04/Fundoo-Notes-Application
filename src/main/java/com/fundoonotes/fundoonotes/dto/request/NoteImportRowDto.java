package com.fundoonotes.fundoonotes.dto.request;

public class NoteImportRowDto {

    private String title;
    private String content;
    private String ownerEmail;

    public NoteImportRowDto() {
    }

    public NoteImportRowDto(String title, String content, String ownerEmail) {
        this.title = title;
        this.content = content;
        this.ownerEmail = ownerEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}