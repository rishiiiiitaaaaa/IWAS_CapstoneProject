package com.example.iwasCap.repository;

import com.example.iwasCap.model.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {

    // Find all employees assigned to a specific project
    @Query("SELECT pa.employee FROM ProjectAssignment pa WHERE pa.project.id = :projectId")
    List<Object> findEmployeesByProjectId(Long projectId);

    // Find all projects assigned to a specific employee
    @Query("SELECT pa.project FROM ProjectAssignment pa WHERE pa.employee.id = :employeeId")
    List<Object> findProjectsByEmployeeId(Long employeeId);

    // Check if an employee is already assigned to a specific project
    boolean existsByEmployeeIdAndProjectId(Long employeeId, Long projectId);

    // Delete an assignment (Remove employee from project)
    void deleteByEmployeeIdAndProjectId(Long employeeId, Long projectId);
}
