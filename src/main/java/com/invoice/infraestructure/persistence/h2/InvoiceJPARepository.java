package com.invoice.infraestructure.persistence.h2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface InvoiceJPARepository extends JpaRepository<InvoiceEntity, UUID> {
  Set<InvoiceWithDetailLine> findByDetailLinesId(UUID detailLineId);
}
