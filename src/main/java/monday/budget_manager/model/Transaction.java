package monday.budget_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private Boolean isExpense;

  @ManyToOne
  @JoinColumn(name = "budget_id", nullable = false)
  private Budget budget;
}
