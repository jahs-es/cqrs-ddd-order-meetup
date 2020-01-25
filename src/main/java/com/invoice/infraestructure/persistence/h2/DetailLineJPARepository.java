package com.invoice.infraestructure.persistence.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetailLineJPARepository extends JpaRepository<DetailLineEntity, UUID> {

}
