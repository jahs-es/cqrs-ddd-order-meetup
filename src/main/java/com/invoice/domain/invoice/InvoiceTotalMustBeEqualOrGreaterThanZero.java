package com.invoice.domain.invoice;

public class InvoiceTotalMustBeEqualOrGreaterThanZero extends RuntimeException {
  InvoiceTotalMustBeEqualOrGreaterThanZero(String message) {
    super(message);
  }
}
