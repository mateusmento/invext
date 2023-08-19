package com.invext.domain.dtos;

import com.invext.domain.values.ServiceType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateServiceRequestDto {
    private String clientName;
    private ServiceType serviceType;
}
