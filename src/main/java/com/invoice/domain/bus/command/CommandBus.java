package com.invoice.domain.bus.command;

import java.util.UUID;

public interface CommandBus {
  UUID dispatch(Command command);
}
