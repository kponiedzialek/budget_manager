package monday.budget_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

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
  private Instant date;

  @Column(nullable = false)
  private boolean isExpense;

  @Column(nullable = false)
  private String category;

  @ManyToOne
  @JoinColumn(name = "budget_id", nullable = false)
  private Budget budget;
}
