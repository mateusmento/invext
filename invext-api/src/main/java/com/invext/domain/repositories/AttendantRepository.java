package com.invext.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invext.domain.entities.Attendant;

@Repository
public interface AttendantRepository extends
    JpaRepository<Attendant, Long>,
    AttendantCustomRepository
{
    List<Attendant> findByNameContains(String name);
}
