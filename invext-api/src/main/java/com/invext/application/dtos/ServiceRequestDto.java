package com.invext.application.dtos;

import com.invext.domain.entities.Attendant;
import com.invext.domain.values.ServiceType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ServiceRequestDto {
    private Long id;
    private String clientName;
    private ServiceType serviceType;
    private Attendant attendant;
}
