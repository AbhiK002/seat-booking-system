package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Cancellation;
import com.project.sbs.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {
    List<Cancellation> getCancellationsByCancellationUserId(User userId);
}
