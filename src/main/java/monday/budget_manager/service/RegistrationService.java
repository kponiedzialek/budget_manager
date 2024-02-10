package monday.budget_manager.service;

import lombok.RequiredArgsConstructor;
import monday.budget_manager.dto.RegistrationDto;
import monday.budget_manager.model.ApplicationUser;
import monday.budget_manager.repository.ApplicationUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final ApplicationUserRepository applicationUserRepository;

  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void register(RegistrationDto registrationDto) {
    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setUsername(registrationDto.getUsername());
    applicationUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
    applicationUserRepository.save(applicationUser);
  }
}
