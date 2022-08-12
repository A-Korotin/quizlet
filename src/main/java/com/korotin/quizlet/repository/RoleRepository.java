package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {}
