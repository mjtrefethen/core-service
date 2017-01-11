package com.egoshard.service.core.event;

import java.util.UUID;

/**
 * Base request event for all service interactions.
 * <p>
 * All requests must contain the UUID of the user or system performing the action.
 * </p>
 */
public class BaseRequestEvent extends BaseEvent {

  String executorKey;

  /**
   * Base constructor identifying the UUID of the executor.
   *
   * @param executorKey unique identifier of the user or system requesting access.
   */
  public BaseRequestEvent(String executorKey) {
    this.executorKey = executorKey;
  }

  /**
   * Gets the UUID of the executing user or system.
   *
   * @return UUID key representing the
   */
  public String getExecutorKey() {
    return executorKey;
  }


}
