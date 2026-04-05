package com.finance.finance_dashboard.controller;

import com.finance.finance_dashboard.dto.FinancialRecordRequestDto;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.service.FinancialRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    @PostMapping
    public FinancialRecord createRecord(
            @Valid @RequestBody FinancialRecordRequestDto dto,
            @RequestParam Long userId) {

        FinancialRecord record = FinancialRecord.builder()
                .amount(dto.getAmount())
                .recordType(dto.getRecordType())
                .category(dto.getCategory())
                .date(dto.getDate())
                .description(dto.getDescription())
                .build();

        return recordService.createRecord(record, userId);
    }
    @GetMapping
    public List<FinancialRecord> getAllRecords()
    {
        return recordService.getAllRecords();
    }
    @GetMapping("/type")
    public List<FinancialRecord> getByType(@RequestParam RecordType recordType)
    {
        return recordService.getRecordsByType(recordType);
    }
    @PutMapping("/{id}")
    public FinancialRecord updateRecord(@PathVariable Long id,@RequestBody FinancialRecordRequestDto dto)
    {
        FinancialRecord record=FinancialRecord.builder()
                .amount(dto.getAmount())
                .recordType(dto.getRecordType())
                .category(dto.getCategory())
                .date(dto.getDate())
                .description(dto.getDescription())
                .build();

        return recordService.updateRecord(id,record);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.ok("Record deleted successfully");
    }
    @GetMapping("/dashboard/summary")
    public Map<String, Double> getSummary() {
        double income = recordService.getTotalIncome();
        double expense = recordService.getTotalExpense();
        double net = recordService.getNetBalance();
        return Map.of(
                "totalIncome", income,
                "totalExpense", expense,
                "netBalance", net
        );
    }
    @GetMapping("/filter")
    public List<FinancialRecord> filterRecords(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {
        return recordService.filterRecords(type, category, startDate, endDate);
    }
    @GetMapping("/dashboard/category")
    public Map<String, Double> getCategoryWise() {
        return recordService.getCategoryWiseTotals();
    }
    @GetMapping("/dashboard/recent")
    public List<FinancialRecord> getRecent() {
        return recordService.getRecentRecords();
    }
}
