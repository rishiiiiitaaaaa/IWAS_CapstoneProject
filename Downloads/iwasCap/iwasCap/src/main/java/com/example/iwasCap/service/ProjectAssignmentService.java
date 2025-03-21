package com.example.iwasCap.service;

import com.example.iwasCap.model.Employee;
import com.example.iwasCap.model.Project;
import com.example.iwasCap.model.ProjectAssignment;
import com.example.iwasCap.repository.EmployeeRepository;
import com.example.iwasCap.repository.ProjectAssignmentRepository;
import com.example.iwasCap.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssignmentService {

    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Get all project assignments
    public List<ProjectAssignment> getAllAssignments() {
        return projectAssignmentRepository.findAll();
    }

    // Get assignment by ID
    public Optional<ProjectAssignment> getAssignmentById(Long id) {
        return projectAssignmentRepository.findById(id);
    }

    // Assign an employee to a project
    public ProjectAssignment assignEmployeeToProject(Long employeeId, Long projectId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        Optional<Project> projectOpt = projectRepository.findById(projectId);

        if (employeeOpt.isPresent() && projectOpt.isPresent()) {
            ProjectAssignment assignment = new ProjectAssignment();
            assignment.setEmployee(employeeOpt.get());
            assignment.setProject(projectOpt.get());

            return projectAssignmentRepository.save(assignment);
        } else {
            throw new RuntimeException("Employee or Project not found");
        }
    }

    // Remove an employee from a project
    public void removeAssignment(Long assignmentId) {
        projectAssignmentRepository.deleteById(assignmentId);
    }
}
