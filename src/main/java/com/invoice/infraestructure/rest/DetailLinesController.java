package com.invoice.infraestructure.rest;

import com.invoice.application.create_detail_line.CreateDetailLineCommand;
import com.invoice.application.update_detail_line.UpdateDetailLineCommand;
import com.invoice.domain.bus.command.CommandBus;
import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.Line;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class DetailLinesController {
  private final CommandBus commandBus;

  @GetMapping("/api/invoices/{invoice}/detailLines")
  public ResponseEntity loadDetailLines(@PathVariable UUID invoice) {
    return ResponseEntity.ok(new DetailLine(new DetailLineId(UUID.randomUUID()), new Line("Messi", 10, 1000)));
  }

  @PostMapping("/api/invoices/{invoice}/detailLines")
  public ResponseEntity addDetailLine(@PathVariable UUID invoice, @RequestBody DetailLineRequest request) throws URISyntaxException {
    log.info("Adding detailLine to invoice: " + invoice);

    UUID detailLineId = commandBus.dispatch(new CreateDetailLineCommand(request.getProduct(), request.getAmount(), request.getPrice(), invoice));
    return ResponseEntity.created(new URI("http://localhost:8080/api/detailLines/"+detailLineId)).build();
  }

  @PutMapping("/api/invoices/{invoice}/detailLines/{detailLine}/update")
  public ResponseEntity modifyDetailLine(@PathVariable UUID invoice, @PathVariable UUID detailLine, @RequestBody DetailLineRequest request) {

    commandBus.dispatch(new UpdateDetailLineCommand(request.getProduct(), request.getPrice(), request.getAmount(), detailLine, invoice));
    return ResponseEntity.noContent().build();
  }

}

@Getter
@AllArgsConstructor
final class DetailLineRequest {
  private String product;
  private int amount;
  private double price;
}
