package com.finance.finance_dashboard.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="financial_records")
public class FinancialRecord {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotNull
private Double amount;
@NotNull
@Enumerated(EnumType.STRING)
private RecordType recordType;

private String category;
private LocalDate date;
private String description;
@ManyToOne
@JoinColumn(name="user_id")
private User createdBy;
}
