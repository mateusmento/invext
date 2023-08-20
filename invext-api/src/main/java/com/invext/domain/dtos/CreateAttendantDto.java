package com.invext.domain.dtos;

import com.invext.domain.values.ServiceType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAttendantDto {
    private String name;
    private ServiceType serviceType;
}
