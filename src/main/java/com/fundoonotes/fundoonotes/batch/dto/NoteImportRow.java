package com.fundoonotes.fundoonotes.batch.dto;

public class NoteImportRow {

    private String title;
    private String description;

    public NoteImportRow() {
    }

    public NoteImportRow(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}