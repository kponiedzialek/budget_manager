package monday.budget_manager.repository;

import monday.budget_manager.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByUsername(String username);
}
