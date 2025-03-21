package com.example.iwasCap.service;

import com.example.iwasCap.model.Project;
import com.example.iwasCap.model.ProjectSkill;
import com.example.iwasCap.model.Skill;
import com.example.iwasCap.repository.ProjectRepository;
import com.example.iwasCap.repository.ProjectSkillRepository;
import com.example.iwasCap.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectSkillService {

    @Autowired
    private ProjectSkillRepository projectSkillRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SkillRepository skillRepository;

    // Get all project-skill relationships
    public List<ProjectSkill> getAllProjectSkills() {
        return projectSkillRepository.findAll();
    }

    // Get project-skill by ID
    public Optional<ProjectSkill> getProjectSkillById(Long id) {
        return projectSkillRepository.findById(id);
    }

    // Assign a skill to a project
    public ProjectSkill assignSkillToProject(Long projectId, Long skillId) {
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        Optional<Skill> skillOpt = skillRepository.findById(skillId);

        if (projectOpt.isPresent() && skillOpt.isPresent()) {
            ProjectSkill projectSkill = new ProjectSkill();
            projectSkill.setProject(projectOpt.get());
            projectSkill.setSkill(skillOpt.get());

            return projectSkillRepository.save(projectSkill);
        } else {
            throw new RuntimeException("Project or Skill not found");
        }
    }

    // Remove a skill from a project
    public void removeSkillFromProject(Long projectSkillId) {
        projectSkillRepository.deleteById(projectSkillId);
    }
}
