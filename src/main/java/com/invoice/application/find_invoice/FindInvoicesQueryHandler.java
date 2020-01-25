package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindInvoicesQueryHandler implements QueryHandler<FindInvoicesQuery, FindInvoicesResponse> {
  private final FindInvoice finder;

  @Override
  public FindInvoicesResponse handle(FindInvoicesQuery query) {
   return new FindInvoicesResponse(finder.find());
  }
}
