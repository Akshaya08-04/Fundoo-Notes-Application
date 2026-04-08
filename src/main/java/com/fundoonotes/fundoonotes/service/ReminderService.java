package com.fundoonotes.fundoonotes.service;

import com.fundoonotes.fundoonotes.dto.request.ReminderRequestDto;
import com.fundoonotes.fundoonotes.dto.response.ReminderResponseDto;

public interface ReminderService {
    ReminderResponseDto createReminder(ReminderRequestDto dto, String token);
}