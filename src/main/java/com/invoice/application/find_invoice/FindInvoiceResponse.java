package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
public class FindInvoiceResponse implements Response {
  private double currentTotal;
  private Set<DetailLineResponse> detailLines;
  private int totalDetailLines;
  private String id;
}

@AllArgsConstructor
@Builder
@Getter
final class DetailLineResponse {
  private String product;
  private int amount;
  private int price;
}
