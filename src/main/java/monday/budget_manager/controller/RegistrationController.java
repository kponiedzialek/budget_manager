package monday.budget_manager.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import monday.budget_manager.dto.RegistrationDto;
import monday.budget_manager.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
  private final RegistrationService registrationService;

  @GetMapping
  public String registerForm(Model model) {
    model.addAttribute("registrationDto", new RegistrationDto());
    return "register";
  }

  @PostMapping
  public String processRegistration(
      @ModelAttribute("registrationDto") @Valid RegistrationDto registrationDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "register";
    }
    registrationService.register(registrationDto);
    return "redirect:/login";
  }
}
