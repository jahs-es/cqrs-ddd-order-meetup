package com.invoice.domain.invoice.detail_line;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DetailLineRepository {
  void save(DetailLine detailLine);
  Set<Optional<DetailLine>> find();
  Optional<DetailLine> findOne(UUID id);
  UUID generateID();
}
