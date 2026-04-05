package com.finance.finance_dashboard.repository;
import com.finance.finance_dashboard.model.FinancialRecord;
import com.finance.finance_dashboard.model.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord,Long> {
    List<FinancialRecord> findByRecordType(RecordType recordType);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);
    List<FinancialRecord> findTop5ByOrderByDateDesc();
}
