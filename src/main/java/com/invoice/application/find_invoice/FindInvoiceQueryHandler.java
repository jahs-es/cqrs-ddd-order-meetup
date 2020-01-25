package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindInvoiceQueryHandler implements QueryHandler<FindInvoiceQuery, FindInvoiceResponse> {
  private final FindInvoice finder;

  @Override
  public FindInvoiceResponse handle(FindInvoiceQuery query) {
   return finder.find(query.getId());
  }
}
