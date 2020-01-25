package com.invoice.application.find_invoice;

import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceNotFoundException;
import com.invoice.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindInvoice {
    private final InvoiceRepository invoiceRepository;

    public FindInvoiceResponse find(UUID invoiceId) {
        Invoice invoice = invoiceRepository.findOne(invoiceId).orElseThrow(() -> new InvoiceNotFoundException(String.format("Invoice %s does not exist", invoiceId)));
        return toResponse(invoice);
    }

    public List<FindInvoiceResponse> find() {
        return invoiceRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<FindInvoiceResponse> findWithoutDetailLines() {
        return invoiceRepository.findAllWithoutDetailLines().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private FindInvoiceResponse toResponse(Invoice invoice) {

        return FindInvoiceResponse.builder()
                .id(invoice.getId().getId().toString())
                .currentTotal(invoice.getTotal())
                .totalDetailLines(invoice.getDetailLines().size())
                .detailLines(invoice.getDetailLines()
                        .stream()
                        .map(detailLine -> DetailLineResponse.builder().product(detailLine.getLine().getProduct()).price(detailLine.getLine().getAmount()).build())
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
