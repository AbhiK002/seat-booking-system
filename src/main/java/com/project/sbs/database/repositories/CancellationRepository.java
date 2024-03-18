package com.project.sbs.database.repositories;

import com.project.sbs.database.entities.Booking;
import com.project.sbs.database.entities.Cancellation;
import com.project.sbs.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {
    List<Cancellation> getCancellationsByCancellationUserId(User userId);

    @Query("select b from Cancellation b where b.cancellationStatus = 'PENDING'")
    List<Cancellation> findAllPendingCancellations();
}
