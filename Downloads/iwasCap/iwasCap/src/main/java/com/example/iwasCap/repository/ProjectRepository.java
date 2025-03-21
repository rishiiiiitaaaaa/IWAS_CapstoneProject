
package com.example.iwasCap.repository;

import com.example.iwasCap.model.Project;
import com.example.iwasCap.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findByRequiredSkillsContaining(Skill skill);
}
