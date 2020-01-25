package com.invoice.infraestructure.bus.query;

import com.invoice.domain.bus.query.Query;
import com.invoice.domain.bus.query.QueryHandler;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Set;

@Service
public class QueryHandlerInspector {

  private HashMap<Class<? extends Query>, Class<? extends QueryHandler>> registeredHandlers;

  public QueryHandlerInspector() {
    Reflections reflections = new Reflections("com.invoice");
    Set<Class<? extends QueryHandler>> queryHandlers = reflections.getSubTypesOf(QueryHandler.class);

    registeredHandlers = inspectQueriesForQueryHandlers(queryHandlers);
  }

  private HashMap<Class<? extends Query>, Class<? extends QueryHandler>> inspectQueriesForQueryHandlers(
      Set<Class<? extends QueryHandler>> queryHandlers
  ) {
    HashMap<Class<? extends Query>, Class<? extends QueryHandler>> inspectedHandlers = new HashMap<>();

    queryHandlers.forEach((handler) -> {
      ParameterizedType parameterizedType = (ParameterizedType) handler.getGenericInterfaces()[0];
      Class<? extends Query> query = (Class<? extends Query>) parameterizedType.getActualTypeArguments()[0];
      inspectedHandlers.put(query, handler);
    });

    return inspectedHandlers;
  }

  public Class<? extends QueryHandler> searchQueryHandlerFor(Class<? extends Query> query) {
    return registeredHandlers.get(query);
  }
}
