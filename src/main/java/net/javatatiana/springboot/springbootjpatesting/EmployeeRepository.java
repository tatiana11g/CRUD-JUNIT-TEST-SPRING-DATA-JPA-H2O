package net.javatatiana.springboot.springbootjpatesting;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

    Optional<Employee> findByEmail(String email);  // se crea este metodo
}
