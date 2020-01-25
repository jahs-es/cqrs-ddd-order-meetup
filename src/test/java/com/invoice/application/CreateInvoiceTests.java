package com.invoice.application;

import com.invoice.application.create_invoice.CreateInvoice;
import com.invoice.application.create_invoice.CreateInvoiceCommand;
import com.invoice.application.create_invoice.CreateInvoiceCommandHandler;
import com.invoice.domain.invoice.Invoice;
import com.invoice.domain.invoice.InvoiceId;
import com.invoice.domain.invoice.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
class CreateInvoiceTests {
  @Mock
  private InvoiceRepository invoiceRepository;

  private CreateInvoiceCommandHandler createInvoiceCommandHandler;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    createInvoiceCommandHandler = new CreateInvoiceCommandHandler(new CreateInvoice(invoiceRepository));
  }

  @Test
  void should_create_invoice() {
    //data
    UUID id = UUID.randomUUID();
    CreateInvoiceCommand command = new CreateInvoiceCommand(0);

    Invoice expextedInvoice = new Invoice(new InvoiceId(id), new HashSet<>(), 0);
    when(invoiceRepository.generateID()).thenReturn(id);

    //actions
    createInvoiceCommandHandler.handle(command);

    //assert
    verify(invoiceRepository, times(1)).save(expextedInvoice);
  }
}
