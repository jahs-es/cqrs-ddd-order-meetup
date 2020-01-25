package com.invoice.application.update_detail_line;


import com.invoice.domain.bus.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class UpdateDetailLineCommand implements Command {
  private String product;
  private double price;
  private int amount;
  private UUID detailLineId;
  private UUID invoiceId;
}
