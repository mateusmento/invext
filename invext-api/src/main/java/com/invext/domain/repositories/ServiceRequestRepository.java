package com.invext.domain.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends
    JpaRepository<ServiceRequest, Long>,
    ServiceRequestCustomerRepository
{
    @Query("""
        from ServiceRequest sr
        where sr.attendant.id = :attendantId
        and sr.status = com.invext.domain.values.ServiceRequestStatus.ACCEPTED
    """)
    List<ServiceRequest> findAttendantServiceRequests(@Param("attendantId") Long attendantId, Pageable pageable);
}
