package com.invoice.infraestructure.persistence.h2;

import com.invoice.domain.invoice.detail_line.DetailLine;
import com.invoice.domain.invoice.detail_line.Line;
import com.invoice.domain.invoice.detail_line.DetailLineId;
import com.invoice.domain.invoice.detail_line.DetailLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DetailLineH2Repository implements DetailLineRepository {

  private final DetailLineJPARepository detailLineJPARepository;

  @Override
  public void save(DetailLine detailLine) {
    DetailLineEntity detailLineToSave = DetailLineEntity.builder()
        .id(detailLine.getId().getId())
        .product(detailLine.getLine().getProduct())
        .amount(detailLine.getLine().getAmount())
        .price(detailLine.getLine().getPrice())
        .build();

    detailLineJPARepository.save(detailLineToSave);
  }

  @Override
  public Set<Optional<DetailLine>> find() {
    return null;
  }

  @Override
  public Optional<DetailLine> findOne(UUID id) {
    DetailLineEntity detailLine = detailLineJPARepository.findById(id).get();

    return Optional.of(new DetailLine(new DetailLineId(detailLine.getId()), new Line(detailLine.getProduct(), detailLine.getAmount(), detailLine.getPrice())));
  }

  @Override
  public UUID generateID() {
    return UUID.randomUUID();
  }
}
