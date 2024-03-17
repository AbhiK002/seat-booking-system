package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.SwapRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapRequestRepository extends JpaRepository<SwapRequest, Integer> {
}
