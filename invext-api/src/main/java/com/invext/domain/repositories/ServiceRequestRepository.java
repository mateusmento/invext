package com.invext.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {}
