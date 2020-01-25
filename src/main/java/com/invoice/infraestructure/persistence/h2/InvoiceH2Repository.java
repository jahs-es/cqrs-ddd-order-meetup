package com.invoice.infraestructure.persistence.h2;

import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.Line;
import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceRepository;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@AllArgsConstructor
public class InvoiceH2Repository implements InvoiceRepository {
    private final InvoiceJPARepository invoiceJPARepository;

    @Override
    public void save(Invoice invoice) {
        invoiceJPARepository.save(mapToEntity(invoice));
    }

    @Override
    public Optional<Invoice> findOne(UUID id) {
        Invoice invoice = mapToDomain(invoiceJPARepository.findById(id).orElse(null));

        return Objects.isNull(invoice) ? Optional.empty() : Optional.of(invoice);
    }

    @Override
    public Optional<Invoice> findOneWithDetailLine(UUID detailLineId) {
        InvoiceWithDetailLine invoiceWithDetailLine = new ArrayList<>(invoiceJPARepository.findByDetailLinesId(detailLineId)).get(0);
        DetailLineEntity detailLineEntity = invoiceWithDetailLine.getDetailLines();
        DetailLine detailLine = new DetailLine(new DetailLineId(detailLineEntity.getId()), new Line(detailLineEntity.getProduct(), detailLineEntity.getAmount(), detailLineEntity.getPrice()));
        detailLine.invoice(new InvoiceId(invoiceWithDetailLine.getId()));

        Invoice invoice = new Invoice(new InvoiceId(invoiceWithDetailLine.getId()), Stream.of(detailLine).collect(Collectors.toSet()), invoiceWithDetailLine.getTotal());

        return Optional.of(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceJPARepository.findAll()
            .stream()
            .map(this::mapToDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findAllWithoutDetailLines() {
        return invoiceJPARepository.findAll()
            .stream()
            .map(this::mapToDomainWithoutDetailLines)
            .collect(Collectors.toList());
    }

    @Override
    public UUID generateID() {
        return UUID.randomUUID();
    }


    private Invoice mapToDomain(InvoiceEntity invoice) {
        if (Objects.nonNull(invoice)) {
            Set<DetailLine> detailLines = invoice.getDetailLines().stream()
                .map(it -> new DetailLine(new DetailLineId(it.getId()), new Line(it.getProduct(), it.getAmount(), it.getPrice()))).collect(Collectors.toSet());

            return new Invoice(new InvoiceId(invoice.getId()), detailLines, invoice.getTotal());
        }
       return null;
    }

    private Invoice mapToDomainWithoutDetailLines(InvoiceEntity invoice) {
        if (Objects.nonNull(invoice)) {
            return new Invoice(new InvoiceId(invoice.getId()), new HashSet<>(), invoice.getTotal());
        }
       return null;
    }

    private InvoiceEntity mapToEntity(Invoice invoice) {
        Set<DetailLineEntity> detailLineEntities = invoice.getDetailLines().stream()
            .map(it -> DetailLineEntity.builder()
                .id(it.getId().getId())
                .product(it.getLine().getProduct())
                .price(it.getLine().getPrice())
                .amount(it.getLine().getAmount())
                .invoice(InvoiceEntity.builder().id(invoice.getId().getId()).build())
                .build()
            )
            .collect(Collectors.toSet());

        return InvoiceEntity.builder()
            .id(invoice.getId().getId())
            .total(invoice.getTotal())
            .detailLines(detailLineEntities)
            .build();
    }
}
