package monday.budget_manager.repository;

import monday.budget_manager.model.ApplicationUser;
import monday.budget_manager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findAllByApplicationUser(ApplicationUser applicationUser);
}
