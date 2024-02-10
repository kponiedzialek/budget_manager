package monday.budget_manager.service;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.model.ApplicationUser;
import monday.budget_manager.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {
  private final ApplicationUserRepository applicationUserRepository;

  public ApplicationUser findByUsername(String username) {
    return applicationUserRepository.findByUsername(username).orElseThrow(RuntimeException::new);
  }
}
