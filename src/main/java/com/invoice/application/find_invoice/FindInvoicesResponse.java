package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.Response;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindInvoicesResponse implements Response {
  private final List<FindInvoiceResponse> invoices;

  public FindInvoicesResponse(List<FindInvoiceResponse> invoices) {
    this.invoices = invoices;
  }
}
