package lu.cnfpcfullstackdev.tfl_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lu.cnfpcfullstackdev.tfl_api.entity.TflUser;

@Repository
public interface TflUserRepository extends JpaRepository<TflUser, Long> {

    // Additional query methods can be declared here if needed

    // Find users by role (e.g., BUSINESSES)
    java.util.List<TflUser> findByRole(lu.cnfpcfullstackdev.tfl_api.entity.UserRole role);

    // Existence checks used by service layer
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    java.util.Optional<lu.cnfpcfullstackdev.tfl_api.entity.TflUser> findByUsername(String username);

}
