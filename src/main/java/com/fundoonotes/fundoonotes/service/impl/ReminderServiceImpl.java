package com.fundoonotes.fundoonotes.service.impl;

import com.fundoonotes.fundoonotes.dto.request.ReminderRequestDto;
import com.fundoonotes.fundoonotes.dto.response.ReminderResponseDto;
import com.fundoonotes.fundoonotes.entity.Note;
import com.fundoonotes.fundoonotes.entity.Reminder;
import com.fundoonotes.fundoonotes.entity.User;
import com.fundoonotes.fundoonotes.exception.NoteNotFoundException;
import com.fundoonotes.fundoonotes.exception.UserNotFoundException;
import com.fundoonotes.fundoonotes.messaging.event.ReminderEvent;
import com.fundoonotes.fundoonotes.messaging.producer.ReminderEventProducer;
import com.fundoonotes.fundoonotes.repository.NoteRepository;
import com.fundoonotes.fundoonotes.repository.ReminderRepository;
import com.fundoonotes.fundoonotes.repository.UserRepository;
import com.fundoonotes.fundoonotes.service.ReminderService;
import com.fundoonotes.fundoonotes.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;
    private final ReminderEventProducer reminderEventProducer;

    public ReminderServiceImpl(ReminderRepository reminderRepository,
                               NoteRepository noteRepository,
                               UserRepository userRepository,
                               TokenUtil tokenUtil,
                               ReminderEventProducer reminderEventProducer) {
        this.reminderRepository = reminderRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
        this.reminderEventProducer = reminderEventProducer;
    }

    @Override
    public ReminderResponseDto createReminder(ReminderRequestDto dto, String token) {
        Long userId = tokenUtil.extractUserId(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Note note = noteRepository.findById(dto.getNoteId())
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You cannot add reminder for this note");
        }

        Reminder reminder = new Reminder();
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setMessage(dto.getMessage());
        reminder.setSent(false);
        reminder.setNote(note);

        Reminder savedReminder = reminderRepository.save(reminder);

        ReminderEvent event = new ReminderEvent(
                savedReminder.getId(),
                note.getId(),
                user.getEmail(),
                savedReminder.getMessage()
        );

        reminderEventProducer.publishReminderEvent(event);

        return new ReminderResponseDto(
                savedReminder.getId(),
                note.getId(),
                savedReminder.getReminderTime(),
                savedReminder.getMessage(),
                savedReminder.isSent()
        );
    }
}