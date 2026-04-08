package com.fundoonotes.fundoonotes.service;

import com.fundoonotes.fundoonotes.dto.request.NoteImportRowDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderService {

    public List<NoteImportRowDto> readExcel(MultipartFile file) {
        List<NoteImportRowDto> rows = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String title = row.getCell(0) != null ? row.getCell(0).toString() : "";
                String content = row.getCell(1) != null ? row.getCell(1).toString() : "";
                String ownerEmail = row.getCell(2) != null ? row.getCell(2).toString() : "";

                rows.add(new NoteImportRowDto(title, content, ownerEmail));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }

        return rows;
    }
}
