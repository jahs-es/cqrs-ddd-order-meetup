package com.invoice.infraestructure.bus.query;

import com.invoice.domain.bus.query.Query;
import com.invoice.domain.bus.query.QueryBus;
import com.invoice.domain.bus.query.QueryHandler;
import com.invoice.domain.bus.query.Response;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SyncQueryBus implements QueryBus {
  private final QueryHandlerInspector queryHandlerInspector;
  private final ApplicationContext context;

  @Override
  public Response ask(Query query) {
    Class<? extends QueryHandler> queryHandlerClass = queryHandlerInspector.searchQueryHandlerFor(query.getClass());

    QueryHandler handler = context.getBean(queryHandlerClass);

    return handler.handle(query);
  }
}
