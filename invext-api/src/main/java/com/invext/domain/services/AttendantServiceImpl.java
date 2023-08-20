package com.invext.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.invext.domain.dtos.CreateAttendantDto;
import com.invext.domain.entities.Attendant;
import com.invext.domain.entities.ServiceRequest;
import com.invext.domain.repositories.AttendantRepository;
import com.invext.domain.repositories.ServiceRequestRepository;

@Service
public class AttendantServiceImpl implements AttendantService {

    private final int MAX_ATTENDANT_SERVICE_REQUESTS = 3;

    @Autowired
    private AttendantRepository attendantRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public Attendant createAttendant(CreateAttendantDto dto) {
        var attendant = Attendant.builder()
            .name(dto.getName())
            .serviceType(dto.getServiceType())
            .build();
        return attendantRepository.save(attendant);
    }

    public List<ServiceRequest> findAttendantServiceRequests(Long attendantId) {
        var pageable = PageRequest.ofSize(MAX_ATTENDANT_SERVICE_REQUESTS);
        return serviceRequestRepository.findAttendantServiceRequests(attendantId, pageable);
    }
}
