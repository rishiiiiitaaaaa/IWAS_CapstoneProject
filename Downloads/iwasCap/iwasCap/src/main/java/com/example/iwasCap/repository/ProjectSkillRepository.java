package com.example.iwasCap.repository;

import com.example.iwasCap.model.ProjectSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Long> {

    // Find all skills required for a specific project
    List<ProjectSkill> findByProjectId(Long projectId);

    // Find all projects requiring a specific skill
    @Query("SELECT ps.project FROM ProjectSkill ps WHERE ps.skill.id = :skillId")
    List<ProjectSkill> findProjectsBySkillId(Long skillId);
}
