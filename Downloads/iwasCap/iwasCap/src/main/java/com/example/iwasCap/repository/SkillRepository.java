package com.example.iwasCap.repository;

import com.example.iwasCap.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    // Find all skills that an employee has
    @Query("SELECT s FROM Skill s JOIN s.employees e WHERE e.id = :employeeId")
    List<Skill> findSkillsByEmployeeId(Long employeeId);

    // Find all skills required for a specific project
    @Query("SELECT s FROM Skill s JOIN s.projects p WHERE p.id = :projectId")
    List<Skill> findSkillsByProjectId(Long projectId);
}
