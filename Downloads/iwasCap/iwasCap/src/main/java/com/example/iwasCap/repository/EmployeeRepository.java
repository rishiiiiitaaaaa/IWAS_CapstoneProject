package com.example.iwasCap.repository;

import com.example.iwasCap.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find an employee by email (Used for login)
    Optional<Employee> findByEmail(String email);

    // Find employees by availability status
    List<Employee> findByAvailability(boolean availability);

    // Find employees with a specific skill
    @Query("SELECT e FROM Employee e JOIN e.skills s WHERE s.name = :skillName")
    List<Employee> findBySkillName(String skillName);

}
