package com.fundoonotes.fundoonotes.batch.processor;

import com.fundoonotes.fundoonotes.batch.dto.NoteImportRow;
import com.fundoonotes.fundoonotes.entity.Note;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class NoteItemProcessor implements ItemProcessor<NoteImportRow, Note> {

    @Override
    public Note process(NoteImportRow item) {
        Note note = new Note();
        note.setTitle(item.getTitle());
        note.setDescription(item.getDescription());
        note.setPinned(false);
        note.setArchived(false);
        note.setTrashed(false);
        return note;
    }
}