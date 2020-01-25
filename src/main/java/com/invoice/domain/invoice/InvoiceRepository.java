package com.invoice.domain.invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository {
    void save(Invoice invoice);
    Optional<Invoice> findOne(UUID id);
    Optional<Invoice> findOneWithDetailLine(UUID detailLineId);
    List<Invoice> findAll();
    List<Invoice> findAllWithoutDetailLines();
    UUID generateID();
}
