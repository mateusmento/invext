package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Long> {
    @Query("""
        from Attendant att
        where att.group.serviceType = :serviceType
        and not att in (
            select r.attendant
            from ServiceRequest r
            where r.serviceType = :serviceType
            and r.status = com.invext.domain.values.ServiceRequestStatus.ACCEPTED
            group by r.attendant
            having count(r.id) >= 3
        )
    """)
    Optional<Attendant> findAvailableAttendant(@Param("serviceType") ServiceType serviceType);
}
