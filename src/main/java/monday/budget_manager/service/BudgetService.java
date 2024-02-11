package monday.budget_manager.service;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.Budget;
import monday.budget_manager.model.Transaction;
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
    // TODO: Do obsłużenia błąd
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

  @Transactional
  public void editBudget(Long id, String name) {
    Budget budget = findBudgetById(id);
    budget.setName(name);
    budgetRepository.save(budget);
  }

  @Transactional
  public void deleteBudget(Long id) {
    budgetRepository.delete(findBudgetById(id));
  }

  @Transactional
  public void addTransactionValue(Transaction transaction) {
    Budget budget = transaction.getBudget();
    if (transaction.getIsExpense()) {
      budget.setTotalExpenses(budget.getTotalExpenses().add(transaction.getAmount()));
      budget.setBalance(budget.getBalance().subtract(transaction.getAmount()));
    } else {
      budget.setTotalRevenues(budget.getTotalRevenues().add(transaction.getAmount()));
      budget.setBalance(budget.getBalance().add(transaction.getAmount()));
    }
    budgetRepository.save(budget);
  }

  @Transactional
  public void subtractTransactionValue(Transaction transaction) {
    Budget budget = transaction.getBudget();
    if (transaction.getIsExpense()) {
      budget.setTotalExpenses(budget.getTotalExpenses().subtract(transaction.getAmount()));
      budget.setBalance(budget.getBalance().add(transaction.getAmount()));
    } else {
      budget.setTotalRevenues(budget.getTotalRevenues().subtract(transaction.getAmount()));
      budget.setBalance(budget.getBalance().subtract(transaction.getAmount()));
    }
    budgetRepository.save(budget);
  }
}
