package com.invext.domain.services;

import java.util.List;

import com.invext.application.dtos.FindAttendantsDto;
import com.invext.domain.dtos.CreateAttendantDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;

public interface AttendantService {
    List<Attendant> findAttendants(FindAttendantsDto dto);
    Attendant createAttendant(CreateAttendantDto dto);
    List<ServiceRequest> findAttendantServiceRequests(Long attendantId);
}
