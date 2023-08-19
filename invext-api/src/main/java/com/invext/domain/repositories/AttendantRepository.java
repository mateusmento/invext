package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    @Query("""
        from Attendant att
    """)
    Optional<Attendant> findAvailableAttendant(ServiceType serviceType);
}
