package com.invoice.application.update_detail_line;

import com.invoice.domain.bus.command.CommandHandler;
import com.invoice.domain.invoice.detail_line.Line;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateDetailLineCommandHandler implements CommandHandler<UpdateDetailLineCommand> {
  private final UpdateDetailLine updateDetailLine;

  @Override
  public UUID handle(UpdateDetailLineCommand command) {
    updateDetailLine.update(command.getDetailLineId(), new Line(command.getProduct(), command.getAmount(), command.getPrice()));
    return command.getDetailLineId();
  }
}
