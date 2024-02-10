package monday.budget_manager.service;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.Budget;
import monday.budget_manager.repository.BudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {
  private final BudgetRepository budgetRepository;

  private final ApplicationUserService applicationUserService;

  public Budget findBudgetById(Long id) {
    // do obsłużenia
    return budgetRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  public List<Budget> getBudgetsForApplicationUser(Principal principal) {
    return budgetRepository.findAllByApplicationUser(
        applicationUserService.findByUsername(principal.getName()));
  }

  @Transactional
  public void createBudgetFromForm(Budget budget, Principal principal) {
    budget.setApplicationUser(applicationUserService.findByUsername(principal.getName()));
    budgetRepository.save(budget);
  }
}
