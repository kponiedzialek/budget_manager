package monday.budget_manager.service;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.Transaction;
import monday.budget_manager.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;

  private final BudgetService budgetService;

  @Transactional
  public void createTransactionFromForm(Long budgetId, Transaction transaction) {
    transaction.setBudget(budgetService.findBudgetById(budgetId));
    budgetService.addTransactionValue(transaction);
    transactionRepository.save(transaction);
  }

  @Transactional
  public void deleteTransaction(Long id) {
    // TODO: Do obsłużenia błąd
    Transaction transaction = transactionRepository.findById(id).orElseThrow(RuntimeException::new);
    budgetService.subtractTransactionValue(transaction);
    transactionRepository.delete(transaction);
  }
}
