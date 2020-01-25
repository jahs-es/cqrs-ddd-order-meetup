package com.invoice.infraestructure.persistence.h2;

import java.util.UUID;

public interface InvoiceWithDetailLine {
  UUID getId();
  double getTotal();
  DetailLineEntity getDetailLines();
}
