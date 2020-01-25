package com.invoice.domain.bus.query;

public interface QueryBus {
  <R extends Response> R ask(Query query);
}
