package com.example.iwasCap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "project_skills")
public class ProjectSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    // ✅ Add these setter methods
    public void setProject(Project project) {
        this.project = project;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    // Getters
    public Project getProject() {
        return project;
    }

    public Skill getSkill() {
        return skill;
    }
}
