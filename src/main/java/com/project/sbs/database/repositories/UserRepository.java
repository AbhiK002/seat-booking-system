package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUserEmail(String userEmail);
}
