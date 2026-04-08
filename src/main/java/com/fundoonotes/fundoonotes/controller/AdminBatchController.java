package com.fundoonotes.fundoonotes.controller;

import com.fundoonotes.fundoonotes.dto.request.NoteImportRowDto;
import com.fundoonotes.fundoonotes.dto.response.ApiResponse;
import com.fundoonotes.fundoonotes.service.ExcelReaderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/batch")
public class AdminBatchController {

    private final ExcelReaderService excelReaderService;

    public AdminBatchController(ExcelReaderService excelReaderService) {
        this.excelReaderService = excelReaderService;
    }

    @PostMapping("/import-notes")
    public ApiResponse importNotes(@RequestParam("file") MultipartFile file) {
        List<NoteImportRowDto> rows = excelReaderService.readExcel(file);
        return new ApiResponse("Excel file read successfully", rows);
    }
}