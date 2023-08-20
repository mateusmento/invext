package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

import jakarta.persistence.EntityManager;

interface AttendantCustomRepository {
    Optional<Attendant> findAvailableAttendant(ServiceType serviceType);
}

public class AttendantCustomRepositoryImpl implements AttendantCustomRepository {

    @Autowired
    private EntityManager em;

    public Optional<Attendant> findAvailableAttendant(ServiceType serviceType) {
        Attendant result = em.createQuery("""
            from Attendant att
            where att.serviceType = :serviceType
            and not att in (
                select r.attendant
                from ServiceRequest r
                where r.serviceType = :serviceType
                and r.status = com.invext.domain.values.ServiceRequestStatus.ACCEPTED
                group by r.attendant
                having count(r.id) >= 3
            )
        """, Attendant.class)
        .setParameter("serviceType", serviceType)
        .getSingleResult();

        return Optional.ofNullable(result);
    }
}
