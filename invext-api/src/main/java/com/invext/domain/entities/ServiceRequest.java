package com.invext.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.invext.domain.values.ServiceRequestStatus;
import com.invext.domain.values.ServiceType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private UUID clientCode;

    @Enumerated(EnumType.ORDINAL)
    private ServiceType serviceType;

    @Enumerated(EnumType.ORDINAL)
    private ServiceRequestStatus status;

    @ManyToOne
    private Attendant attendant;

    private LocalDateTime createdAt;
}
