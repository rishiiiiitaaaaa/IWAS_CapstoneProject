package com.example.iwasCap.service;

import com.example.iwasCap.model.Employee;
import com.example.iwasCap.model.Project;
import com.example.iwasCap.model.Skill;
import com.example.iwasCap.repository.EmployeeRepository;
import com.example.iwasCap.repository.ProjectRepository;
import com.example.iwasCap.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Projectservice {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    /**
     * Create a new project.
     */
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    /**
     * Get all projects.
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Get a project by ID.
     */
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    /**
     * Update a project.
     */
    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id).map(project -> {
            project.setName(updatedProject.getName());
            project.setRequiredSkills(updatedProject.getRequiredSkills());
            return projectRepository.save(project);
        }).orElse(null);
    }

    /**
     * Delete a project.
     */
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Assign employees to a project.
     */
    public Project assignEmployeesToProject(Long projectId, Set<Long> employeeIds) {
        return projectRepository.findById(projectId).map(project -> {
            Set<Employee> employees = new HashSet<>(employeeRepository.findAllById(employeeIds));
            project.getEmployees().addAll(employees);
            return projectRepository.save(project);
        }).orElse(null);
    }

    /**
     * Find projects that require a specific skill.
     */
    public List<Project> findProjectsBySkill(Long skillId) {
        return skillRepository.findById(skillId)
                .map(projectRepository::findByRequiredSkillsContaining)
                .orElse(List.of());
    }
}
