package monday.budget_manager.controller;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.Transaction;
import monday.budget_manager.service.BudgetService;
import monday.budget_manager.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/budget/{budgetId}")
public class TransactionController {
  private final TransactionService transactionService;

  private final BudgetService budgetService;

  @GetMapping("/add-transaction")
  public String createTransactionForm(Model model, @PathVariable Long budgetId) {
    model.addAttribute("transaction", new Transaction());
    model.addAttribute("budget", budgetService.findBudgetById(budgetId));
    return "add-transaction";
  }

  @PostMapping("/add-transaction")
  public String processCreateTransaction(
      @ModelAttribute("transaction") Transaction transaction, @PathVariable Long budgetId) {
    transactionService.createTransactionFromForm(budgetId, transaction);

    return "redirect:/budget/" + budgetId;
  }

  @PostMapping("/transaction/{id}/delete")
  public String deleteTransaction(@PathVariable("id") Long id, @PathVariable Long budgetId) {
    transactionService.deleteTransaction(id);

    return "redirect:/budget/" + budgetId;
  }
}
