package com.invext.domain.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

@Repository
public interface AttendantRepository {
    List<Attendant> findAvailableAttendant(ServiceType serviceType);
}
