package xyz.rajatjain.collegefestdebatesecured.service;

import org.springframework.stereotype.Service;
import xyz.rajatjain.collegefestdebatesecured.model.Student;

import java.util.List;

@Service
public interface StudentService {

	List<Student> findAll();

	Student findById(int id);

	void save(Student student);

	void deleteById(int id);

}
