package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceRequestStatus;
import com.invext.domain.values.ServiceType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

interface AttendantCustomRepository {
    Optional<Attendant> findAvailableAttendant(ServiceType serviceType);
}

public class AttendantCustomRepositoryImpl implements AttendantCustomRepository {

    @Value("${MAX_ATTENDANT_SERVICE_REQUESTS}")
    private int MAX_ATTENDANT_SERVICE_REQUESTS;

    @Autowired
    private EntityManager em;

    public Optional<Attendant> findAvailableAttendant(ServiceType serviceType) {
        try {
            var result = (Attendant) em.createNativeQuery("""
                select a.*
                from attendant a
                left join (
                    select attendant_id, count(id) service_requests
                    from service_request
                    where service_type = :serviceType and status = :acceptedStatus
                    group by attendant_id
                ) sr on a.id = sr.attendant_id
                where a.service_type = :serviceType and coalesce(sr.service_requests, 0) < :MAX_ATTENDANT_SERVICE_REQUESTS
                order by coalesce(sr.service_requests, 0)
                limit 1
            """, Attendant.class)
            .setParameter("serviceType", serviceType.ordinal())
            .setParameter("acceptedStatus", ServiceRequestStatus.ACCEPTED.ordinal())
            .setParameter("MAX_ATTENDANT_SERVICE_REQUESTS", MAX_ATTENDANT_SERVICE_REQUESTS)
            .getSingleResult();

            return Optional.of(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
