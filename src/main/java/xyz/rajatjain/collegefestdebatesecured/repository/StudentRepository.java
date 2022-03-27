package xyz.rajatjain.collegefestdebatesecured.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.rajatjain.collegefestdebatesecured.model.Student;

/**
 * @author rajatjain on - 27-03-2022
 * @project CollegeFestDebateSecured
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
