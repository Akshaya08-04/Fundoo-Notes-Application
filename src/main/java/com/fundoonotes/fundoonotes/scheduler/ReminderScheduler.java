package com.fundoonotes.fundoonotes.scheduler;

import com.fundoonotes.fundoonotes.entity.Reminder;
import com.fundoonotes.fundoonotes.repository.ReminderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final ReminderRepository reminderRepository;

    public ReminderScheduler(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void checkPendingReminders() {
        List<Reminder> reminders = reminderRepository.findBySentFalseAndReminderTimeBefore(LocalDateTime.now());

        for (Reminder reminder : reminders) {
            System.out.println("Processing reminder: " + reminder.getMessage());
            reminder.setSent(true);
            reminderRepository.save(reminder);
        }
    }
}