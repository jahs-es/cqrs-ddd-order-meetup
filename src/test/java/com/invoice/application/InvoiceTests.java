package com.invoice.application;

import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceIsFullException;
import com.invoice.domain.invoice.InvoiceTotalMustBeEqualOrGreaterThanZero;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import com.invoice.domain.invoice.detail_line.Line;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
class InvoiceTests {
  @Test
  void should_launch_exception_when_total_is_negative() {
    //data
    UUID id = UUID.randomUUID();

    Assertions.assertThrows(InvoiceTotalMustBeEqualOrGreaterThanZero.class, () -> {
      Invoice invoice = new Invoice(new InvoiceId(id),new HashSet<>(),-2);
    });
  }

  @Test
  void should_launch_exception_when_detail_lines_is_full_and_adds_new_line() {
    //data
    UUID id = UUID.randomUUID();

    DetailLine detailLine1 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    DetailLine detailLine2 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    DetailLine detailLine3 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    DetailLine detailLine4 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    DetailLine detailLine5 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    DetailLine detailLine6 = new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("producto", 1, 10));

    Invoice invoice = new Invoice(new InvoiceId(id),new HashSet<DetailLine>() {{
      add(detailLine1);
      add(detailLine2);
      add(detailLine3);
      add(detailLine4);
      add(detailLine5);
    }},10);

    Assertions.assertThrows(InvoiceIsFullException.class, () -> {
      invoice.addDetailLine(detailLine6);
    });
  }
}
