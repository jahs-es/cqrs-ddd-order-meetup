package com.invoice.application;

import com.invoice.application.create_detail_line.CreateDetailLine;
import com.invoice.application.create_detail_line.CreateDetailLineCommand;
import com.invoice.application.create_detail_line.CreateDetailLineCommandHandler;
import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceRepository;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.DetailLineId;
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
class CreateDetailLineTests {
    @Mock
    private InvoiceRepository invoiceRepository;

    private CreateDetailLineCommandHandler createDetailLineCommandHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        createDetailLineCommandHandler = new CreateDetailLineCommandHandler(new CreateDetailLine(invoiceRepository));
    }

    @Test
    void should_create_detail_line() {
        //data
        UUID invoiceId = UUID.randomUUID();
        UUID detailLineId = UUID.randomUUID();

        CreateDetailLineCommand command = new CreateDetailLineCommand("producto",1,10,invoiceId);

        Invoice invoice = new Invoice(new InvoiceId(invoiceId), new HashSet<>(), 0);

        when(invoiceRepository.findOne(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.generateID()).thenReturn(detailLineId);

        DetailLine expectedDetailLine = new DetailLine(new DetailLineId(detailLineId), new Line("producto", 1, 10));
        expectedDetailLine.invoice(new InvoiceId(invoiceId));

        Invoice expectedInvoice = new Invoice(new InvoiceId(invoiceId), new HashSet<DetailLine>() {{
            add(expectedDetailLine);
        }}, 10);

        //actions
        createDetailLineCommandHandler.handle(command);

        //assert
        verify(invoiceRepository, times(1)).save(expectedInvoice);
    }
}
