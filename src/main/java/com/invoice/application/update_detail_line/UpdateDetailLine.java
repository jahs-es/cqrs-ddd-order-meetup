package com.invoice.application.update_detail_line;

import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceRepository;
import com.invoice.domain.invoice.detail_line.Line;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateDetailLine {

  private final InvoiceRepository invoiceRepository;

  public void update(UUID detailLineId, Line newDetails) {
    Invoice invoice = invoiceRepository.findOneWithDetailLine(detailLineId).get();

    invoice.modifyLine(newDetails, detailLineId);

    invoiceRepository.save(invoice);
  }
}
