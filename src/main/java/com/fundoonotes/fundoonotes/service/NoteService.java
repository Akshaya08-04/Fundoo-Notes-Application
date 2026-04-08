package com.fundoonotes.fundoonotes.service;

import com.fundoonotes.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.fundoonotes.dto.response.NoteResponseDto;

import java.util.List;

public interface NoteService {
    NoteResponseDto createNote(NoteRequestDto dto, String token);
    List<NoteResponseDto> getAllNotes(String token);
    NoteResponseDto getNoteById(Long noteId, String token);
    NoteResponseDto pinNote(Long noteId, String token);
    NoteResponseDto archiveNote(Long noteId, String token);
    NoteResponseDto trashNote(Long noteId, String token);
}