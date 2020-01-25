package com.invoice.application.find_invoice;

import com.invoice.domain.bus.query.Query;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class FindInvoiceQuery implements Query {
  UUID id;
}
