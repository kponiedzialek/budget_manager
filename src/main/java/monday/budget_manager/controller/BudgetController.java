package monday.budget_manager.controller;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.Budget;
import monday.budget_manager.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BudgetController {
  private final BudgetService budgetService;

  @GetMapping("/budgets")
  public String listUserBudgets(Model model, Principal principal) {
    model.addAttribute("budgets", budgetService.getBudgetsForApplicationUser(principal));
    return "budgets";
  }

  @GetMapping("create-budget")
  public String createBudgetForm(Model model) {
    model.addAttribute("budget", new Budget());
    return "create-budget";
  }

  @PostMapping("/create-budget")
  public String processCreateBudget(@ModelAttribute("budget") Budget budget, Principal principal) {
    budgetService.createBudgetFromForm(budget, principal);

    return "redirect:/budgets";
  }

  @GetMapping("/budget/{id}")
  public String viewBudget(@PathVariable Long id, Model model) {
    Budget budget = budgetService.findBudgetById(id);

    model.addAttribute("budget", budget);
    return "budget-details";
  }
}
