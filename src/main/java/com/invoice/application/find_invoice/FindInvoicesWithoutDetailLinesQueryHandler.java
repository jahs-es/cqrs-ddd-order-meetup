package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.QueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindInvoicesWithoutDetailLinesQueryHandler implements QueryHandler<FindInvoicesWithoutDetailLinesQuery, FindInvoicesResponse> {
  private final FindInvoice finder;

  @Override
  public FindInvoicesResponse handle(FindInvoicesWithoutDetailLinesQuery query) {
   return new FindInvoicesResponse(finder.findWithoutDetailLines());
  }
}
