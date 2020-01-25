package com.invoice.domain.invoice.detail_line;

public class InvalidDetailLineAmount extends RuntimeException {
  InvalidDetailLineAmount(String message) {
    super(message);
  }
}
