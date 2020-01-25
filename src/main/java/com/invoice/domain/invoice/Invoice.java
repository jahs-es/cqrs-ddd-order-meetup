package com.invoice.domain.invoice;


import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.Line;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;


@Getter
@EqualsAndHashCode
public class Invoice {
  private InvoiceId id;
  private Set<DetailLine> detailLines;
  private double total;

  private static final int MAX_NUMBER_OF_LINES = 5;

  public Invoice(InvoiceId id, Set<DetailLine> detailLines, double total) {
    this.id = id;
    this.detailLines = detailLines;
    total(total);
  }

  public void addDetailLine(DetailLine detailLine) {
    if (detailLines.size() == MAX_NUMBER_OF_LINES) {
      throw new InvoiceIsFullException(String.format("Invoice already has %s lines", MAX_NUMBER_OF_LINES));
    }

    detailLine.invoice(id);
    detailLines.add(detailLine);

    total(getTotalFromDetailLines());
  }

  public void modifyLine(Line newDetails, UUID detailLineId) {
    DetailLine currentDetailLine = detailLines
            .stream()
            .filter(it -> it.getId().getId().equals(detailLineId))
            .findFirst().get();

    currentDetailLine.modify(new Line(newDetails.getProduct(), newDetails.getAmount(), newDetails.getPrice()));
    total(getTotalFromDetailLines());
  }

  private double getTotalFromDetailLines() {
    return detailLines.stream()
            .map(detailLine -> detailLine.getLine().getTotal())
            .reduce(0.0,Double::sum);
  }

  public void total(double total) {
    if (total < 0) {
      throw new InvoiceTotalMustBeEqualOrGreaterThanZero("Invoice total must be greater or equal than 0");
    }

    this.total = total;
  }
}
