package com.invoice.application.create_detail_line;
import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceRepository;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import com.invoice.domain.invoice.detail_line.Line;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateDetailLine {
  private final InvoiceRepository invoiceRepository;

  public UUID create(Line detailLineDetails, UUID invoiceId) {
    UUID detailLineId  = invoiceRepository.generateID();

   Invoice  invoice = invoiceRepository.findOne(invoiceId).get();
   DetailLine  detailLine = new DetailLine(new DetailLineId(detailLineId), new Line(detailLineDetails.getProduct(), detailLineDetails.getAmount(), detailLineDetails.getPrice()));

    invoice.addDetailLine(detailLine);
    invoiceRepository.save(invoice);

    return detailLineId;
  }
}
