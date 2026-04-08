package com.fundoonotes.fundoonotes.batch.writer;

import com.fundoonotes.fundoonotes.entity.Note;
import com.fundoonotes.fundoonotes.repository.NoteRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class NoteItemWriter implements ItemWriter<Note> {

    private final NoteRepository noteRepository;

    public NoteItemWriter(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void write(Chunk<? extends Note> chunk) {
        noteRepository.saveAll(chunk.getItems());
    }
}
