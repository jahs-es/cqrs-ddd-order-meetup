package com.invoice.application.create_detail_line;

import com.invoice.domain.bus.command.CommandHandler;
import com.invoice.domain.invoice.detail_line.Line;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateDetailLineCommandHandler implements CommandHandler<CreateDetailLineCommand> {
  private final CreateDetailLine detailLineCreator;

  @Override
  public UUID handle(CreateDetailLineCommand command) {
    return detailLineCreator.create(new Line(command.getProduct(), command.getAmount(), command.getPrice()), command.getInvoiceId());
  }
}
