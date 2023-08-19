package com.invext.domain.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

@Repository
public interface AttendantRepository {
    Optional<Attendant> findAvailableAttendant(ServiceType serviceType);
}
