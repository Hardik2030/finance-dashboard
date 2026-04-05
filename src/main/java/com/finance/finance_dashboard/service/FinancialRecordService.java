package com.finance.finance_dashboard.service;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import com.finance.finance_dashboard.model.User;
import com.finance.finance_dashboard.repository.FinancialRecordRepository;
import com.finance.finance_dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;
    private final UserRepository userRepository;
    public FinancialRecord createRecord(FinancialRecord record, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        record.setCreatedBy(user);
        return recordRepository.save(record);
    }
    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }
    public List<FinancialRecord> getRecordsByType(RecordType recordType) {
        return recordRepository.findByRecordType(recordType);
    }
    public FinancialRecord updateRecord(Long recordId, FinancialRecord updatedRecord) {
        FinancialRecord existingRecord = recordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found"));
        existingRecord.setAmount(updatedRecord.getAmount());
        existingRecord.setRecordType(updatedRecord.getRecordType());
        existingRecord.setCategory(updatedRecord.getCategory());
        existingRecord.setDate(updatedRecord.getDate());
        existingRecord.setDescription(updatedRecord.getDescription());
        return recordRepository.save(existingRecord);
    }
    public List<FinancialRecord> filterRecords(
            RecordType type,
            String category,
            LocalDate startDate,
            LocalDate endDate) {
        return recordRepository.findAll().stream()
                .filter(r -> type == null || r.getRecordType() == type)
                .filter(r -> category == null || r.getCategory().equalsIgnoreCase(category))
                .filter(r -> startDate == null || (r.getDate() != null && !r.getDate().isBefore(startDate)))
                .filter(r -> endDate == null || (r.getDate() != null && !r.getDate().isAfter(endDate)))
                .toList();
    }
    public void deleteRecord(Long id) {
        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        recordRepository.delete(record);
    }
    public double getTotalIncome() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getRecordType() == RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }
    public double getTotalExpense() {
        return recordRepository.findAll().stream()
                .filter(r -> r.getRecordType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }
    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }
    public Map<String, Double> getCategoryWiseTotals() {
        return recordRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.summingDouble(FinancialRecord::getAmount)
                ));
    }
    public List<FinancialRecord> getRecentRecords() {
        return recordRepository.findTop5ByOrderByDateDesc();
    }
}