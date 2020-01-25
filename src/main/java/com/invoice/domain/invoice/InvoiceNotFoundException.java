package com.invoice.domain.invoice;

public class InvoiceNotFoundException extends RuntimeException {
  public InvoiceNotFoundException(String message) {
    super(message);
  }
}
