package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

}
