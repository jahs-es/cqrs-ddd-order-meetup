package com.invoice.application.create_invoice;

import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateInvoice {
  private final InvoiceRepository invoiceRepository;
  public UUID create(double total) {
    InvoiceId invoiceId = new InvoiceId(invoiceRepository.generateID());
    Invoice invoice = new Invoice(invoiceId, new HashSet<>(), total);

    invoiceRepository.save(invoice);

    return invoiceId.getId();
  }
}
