package com.fundoonotes.fundoonotes.service.impl;

import com.fundoonotes.fundoonotes.dto.request.NoteRequestDto;
import com.fundoonotes.fundoonotes.dto.response.NoteResponseDto;
import com.fundoonotes.fundoonotes.entity.Note;
import com.fundoonotes.fundoonotes.entity.User;
import com.fundoonotes.fundoonotes.exception.NoteNotFoundException;
import com.fundoonotes.fundoonotes.exception.UserNotFoundException;
import com.fundoonotes.fundoonotes.repository.NoteRepository;
import com.fundoonotes.fundoonotes.repository.UserRepository;
import com.fundoonotes.fundoonotes.service.NoteService;
import com.fundoonotes.fundoonotes.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger log = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository, TokenUtil tokenUtil) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    private User findUserFromToken(String token) {
        Long userId = tokenUtil.extractUserId(token);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found"));
    }

    private NoteResponseDto mapToResponse(Note note) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.isPinned(),
                note.isArchived(),
                note.isTrashed()
        );
    }

    @Override
    public NoteResponseDto createNote(NoteRequestDto dto, String token) {
        log.info("Creating note");

        User user = findUserFromToken(token);

        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setPinned(false);
        note.setArchived(false);
        note.setTrashed(false);
        note.setUser(user);

        Note savedNote = noteRepository.save(note);
        return mapToResponse(savedNote);
    }

    @Override
    public List<NoteResponseDto> getAllNotes(String token) {
        User user = findUserFromToken(token);

        return noteRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponseDto pinNote(Long noteId, String token) {
        User user = findUserFromToken(token);

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot access this note");
        }

        note.setPinned(true);
        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto archiveNote(Long noteId, String token) {
        User user = findUserFromToken(token);

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot access this note");
        }

        note.setArchived(true);
        return mapToResponse(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto trashNote(Long noteId, String token) {
        User user = findUserFromToken(token);

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot access this note");
        }

        note.setTrashed(true);
        return mapToResponse(noteRepository.save(note));
    }
    @Override
    @Cacheable(value = "notes", key = "#noteId")
    public NoteResponseDto getNoteById(Long noteId, String token) {
        User user = findUserFromToken(token);

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot access this note");
        }

        return mapToResponse(note);
    }
}
