package com.invoice.domain.invoice.detail_line;

import com.invoice.domain.invoice.InvoiceId;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public class DetailLine {
  private DetailLineId id;
  private Line line;
  private InvoiceId invoiceId;

  public DetailLine(DetailLineId id, Line line){
    this.id = id;
    this.line = line;
  }

  public void invoice(InvoiceId invoiceId) {
    if (Objects.isNull(invoiceId)) {
      throw new DetailLineMustBelongToAInvoiceException("A detail line must have a invoice");
    }
    this.invoiceId = invoiceId;
  }

  public void modify(Line line) {
    this.line = new Line(line.getProduct(), line.getAmount(), line.getPrice());
  }

}
