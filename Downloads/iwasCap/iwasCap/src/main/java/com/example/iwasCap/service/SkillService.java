package com.example.iwasCap.service;

import com.example.iwasCap.model.Skill;
import com.example.iwasCap.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    // Get all skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Get skill by ID
    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    // Add or update a skill
    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Delete a skill by ID
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}
