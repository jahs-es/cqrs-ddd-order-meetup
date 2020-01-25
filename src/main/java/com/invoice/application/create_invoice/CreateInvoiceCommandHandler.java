package com.invoice.application.create_invoice;

import com.invoice.domain.bus.command.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateInvoiceCommandHandler implements CommandHandler<CreateInvoiceCommand> {
  private final CreateInvoice creator;

  @Override
  public UUID handle(CreateInvoiceCommand command) {
    return creator.create(command.getTotal());
  }
}
