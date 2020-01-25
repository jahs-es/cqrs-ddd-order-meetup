package com.invoice.application.create_invoice;


import com.invoice.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateInvoiceCommand implements Command {
  private double total;
}
