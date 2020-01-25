package com.invoice.domain.invoice.detail_line;

class DetailLineMustBelongToAInvoiceException extends RuntimeException {
  DetailLineMustBelongToAInvoiceException(String message) {
    super(message);
  }
}
