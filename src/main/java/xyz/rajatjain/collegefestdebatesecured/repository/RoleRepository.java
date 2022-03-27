package xyz.rajatjain.collegefestdebatesecured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.rajatjain.collegefestdebatesecured.model.Role;

import java.util.Optional;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
