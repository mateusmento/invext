package com.invext.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.values.ServiceType;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    @Query("""
        from ServiceRequest sr
        where sr.attendant.id = :attendantId
        and sr.status = com.invext.domain.ServiceRequestStatus.ACCEPTED
    """)
    List<ServiceRequest> findAttendantServiceRequests(@Param("attendantId") Long attendant);

    @Query("""
        from ServiceRequest sr
        where sr.serviceType = :serviceType
        and sr.status = com.invext.domain.ServiceRequestStatus.PENDING
        order by sr.createdBy
    """)
    Optional<ServiceRequest> findNextPendingServiceRequest(@Param("serviceType") ServiceType serviceType);
}
