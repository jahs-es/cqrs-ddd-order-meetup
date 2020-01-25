package com.invoice.domain.invoice.detail_line;

public class DetailLinePriceMustBeAboveZeroException extends RuntimeException {
  DetailLinePriceMustBeAboveZeroException(String message) {
    super(message);
  }
}
