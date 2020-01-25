package com.invoice.domain.invoice;

public class InvoiceIsFullException extends RuntimeException {
  InvoiceIsFullException(String message) {
    super(message);
  }
}
