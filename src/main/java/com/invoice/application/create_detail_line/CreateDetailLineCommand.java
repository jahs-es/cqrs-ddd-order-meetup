package com.invoice.application.create_detail_line;


import com.invoice.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreateDetailLineCommand implements Command {
  private String product;
  private int amount;
  private double price;
  private UUID invoiceId;
}
