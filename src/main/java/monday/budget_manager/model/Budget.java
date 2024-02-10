package monday.budget_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Budget {
  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private BigDecimal balance = BigDecimal.valueOf(0);

  @Column(nullable = false)
  private BigDecimal totalRevenues = BigDecimal.valueOf(0);

  @Column(nullable = false)
  private BigDecimal totalExpenses = BigDecimal.valueOf(0);

  @ManyToOne
  @JoinColumn(name = "application_user_id", nullable = false)
  private ApplicationUser applicationUser;

  @OneToMany(mappedBy = "budget")
  private List<Transaction> transactions;
}
