package com.invoice.application;

import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceIsFullException;
import com.invoice.domain.invoice.InvoiceTotalMustBeEqualOrGreaterThanZero;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import com.invoice.domain.invoice.detail_line.DetailLinePriceMustBeAboveZeroException;
import com.invoice.domain.invoice.detail_line.InvalidDetailLineAmount;
import com.invoice.domain.invoice.detail_line.Line;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
class DetailLineTests {
  @Test
  void should_launch_exception_when_price_is_negative() {
    //data
    UUID id = UUID.randomUUID();

    Assertions.assertThrows(DetailLinePriceMustBeAboveZeroException.class, () -> {
      DetailLine detailLine = new DetailLine(new DetailLineId(id),new Line("producto",1,-10));
    });
  }

  @Test
  void should_launch_exception_when_amount_is_negative() {
    //data
    UUID id = UUID.randomUUID();

    Assertions.assertThrows(InvalidDetailLineAmount.class, () -> {
      DetailLine detailLine = new DetailLine(new DetailLineId(id),new Line("producto",-5,1));
    });
  }
}
