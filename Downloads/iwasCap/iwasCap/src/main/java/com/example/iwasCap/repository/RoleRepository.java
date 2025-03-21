package com.example.iwasCap.repository;

import com.example.iwasCap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Find role by name (e.g., ADMIN, EMPLOYEE)
    Optional<Role> findByName(String name);
}
