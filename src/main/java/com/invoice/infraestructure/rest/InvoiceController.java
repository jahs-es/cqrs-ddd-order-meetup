package com.invoice.infraestructure.rest;

import com.invoice.application.create_invoice.CreateInvoiceCommand;
import com.invoice.application.find_invoice.FindInvoiceQuery;
import com.invoice.application.find_invoice.FindInvoicesQuery;
import com.invoice.application.find_invoice.FindInvoicesResponse;
import com.invoice.application.find_invoice.FindInvoicesWithoutDetailLinesQuery;
import com.invoice.domain.bus.command.CommandBus;
import com.invoice.domain.bus.query.QueryBus;
import com.invoice.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class InvoiceController {
  private final QueryBus queryBus;
  private final CommandBus commandBus;

  @GetMapping("/api/invoices")
  public ResponseEntity<FindInvoicesResponse> loadInvoices() {
    log.info("Getting info about invoices without detailLines");

    return ResponseEntity.of(Optional.of(queryBus.ask(new FindInvoicesQuery())));
  }

  @GetMapping("/api/invoices/only")
  public ResponseEntity<FindInvoicesResponse> loadInvoicesWithoutDetailLines() {
    log.info("Getting info about invoices without detailLines");
    return ResponseEntity.of(Optional.of(queryBus.ask(new FindInvoicesWithoutDetailLinesQuery())));
  }

  @GetMapping("/api/invoices/{id}")
  public ResponseEntity<Response> loadInvoice(@PathVariable UUID id) {
    log.info("Getting info about invoice: " + id);
    return ResponseEntity.of(Optional.of(queryBus.ask(new FindInvoiceQuery(id))));
  }

  @PostMapping("/api/invoices")
  public ResponseEntity<UUID> create(@RequestBody InvoiceRequest invoiceRequest) throws URISyntaxException {
    UUID invoiceId = commandBus.dispatch(new CreateInvoiceCommand(invoiceRequest.getTotal()));
    return ResponseEntity.created(new URI("http://localhost:8080/api/invoices/" + invoiceId)).build();
  }
}

@Getter
@AllArgsConstructor
@NoArgsConstructor
final class InvoiceRequest {
  private double total;
}

