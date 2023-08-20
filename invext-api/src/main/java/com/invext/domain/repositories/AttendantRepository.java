package com.invext.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;

@Repository
public interface AttendantRepository extends
    JpaRepository<Attendant, Long>,
    AttendantCustomRepository
{}
