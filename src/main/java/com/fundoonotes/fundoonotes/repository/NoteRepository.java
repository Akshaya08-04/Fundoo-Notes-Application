package com.fundoonotes.fundoonotes.repository;

import com.fundoonotes.fundoonotes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
    List<Note> findByUserIdAndArchivedFalseAndTrashedFalse(Long userId);
}