package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.values.ServiceType;

import jakarta.persistence.EntityManager;

interface ServiceRequestCustomerRepository {
    Optional<ServiceRequest> findNextPendingServiceRequest(ServiceType serviceType);
}

public class ServiceRequestCustomerRepositoryImpl implements ServiceRequestCustomerRepository {

    @Autowired
    private EntityManager em;

    public Optional<ServiceRequest> findNextPendingServiceRequest(ServiceType serviceType) {
        var result = em.createQuery("""
            from ServiceRequest sr
            where sr.serviceType = :serviceType
            and sr.status = com.invext.domain.values.ServiceRequestStatus.PENDING
            order by sr.createdAt
        """, ServiceRequest.class)
        .setParameter("serviceType", serviceType)
        .getSingleResult();

        return Optional.ofNullable(result);
    }
}
