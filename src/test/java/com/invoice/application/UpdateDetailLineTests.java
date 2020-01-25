package com.invoice.application;

import com.invoice.application.update_detail_line.UpdateDetailLine;
import com.invoice.application.update_detail_line.UpdateDetailLineCommand;
import com.invoice.application.update_detail_line.UpdateDetailLineCommandHandler;
import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceRepository;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import com.invoice.domain.invoice.detail_line.DetailLineRepository;
import com.invoice.domain.invoice.detail_line.Line;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
class UpdateDetailLineTests {
    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private DetailLineRepository detailLineRepository;

    private UpdateDetailLineCommandHandler updateDetailLineCommandHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        updateDetailLineCommandHandler = new UpdateDetailLineCommandHandler(new UpdateDetailLine(invoiceRepository));
    }

    @Test
    void should_update_line_in_invoice() {
        //data
        UUID invoiceId = UUID.randomUUID();
        UUID detailLineId = UUID.randomUUID();

        UpdateDetailLineCommand command = new UpdateDetailLineCommand("producto",10,2,detailLineId,invoiceId);

        DetailLine detailLine = new DetailLine(new DetailLineId(detailLineId), new Line("producto", 1, 10));
        detailLine.invoice(new InvoiceId(invoiceId));

        Invoice invoice = new Invoice(new InvoiceId(invoiceId), new HashSet<DetailLine>() {{
            add(detailLine);
        }}, 10);

        when(invoiceRepository.findOneWithDetailLine(detailLineId)).thenReturn(Optional.of(invoice));

        DetailLine expectedDetailLine = new DetailLine(new DetailLineId(detailLineId), new Line("producto", 2, 10));
        expectedDetailLine.invoice(new InvoiceId(invoiceId));

        Invoice expectedInvoice = new Invoice(new InvoiceId(invoiceId), new HashSet<DetailLine>() {{
            add(expectedDetailLine);
        }}, 20);

        //actions
        updateDetailLineCommandHandler.handle(command);

        //assert
        verify(invoiceRepository, times(1)).save(expectedInvoice);
    }
}
