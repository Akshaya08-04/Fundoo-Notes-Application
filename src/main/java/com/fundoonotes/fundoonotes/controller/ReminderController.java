package com.fundoonotes.fundoonotes.controller;

import com.fundoonotes.fundoonotes.dto.request.ReminderRequestDto;
import com.fundoonotes.fundoonotes.dto.response.ReminderResponseDto;
import com.fundoonotes.fundoonotes.service.ReminderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ResponseEntity<ReminderResponseDto> createReminder(
            @Valid @RequestBody ReminderRequestDto dto,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reminderService.createReminder(dto, token));
    }
}
