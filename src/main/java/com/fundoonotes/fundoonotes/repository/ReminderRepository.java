package com.fundoonotes.fundoonotes.repository;

import com.fundoonotes.fundoonotes.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findBySentFalseAndReminderTimeBefore(LocalDateTime time);
}

