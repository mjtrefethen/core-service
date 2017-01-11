package com.egoshard.service.core.event;

import java.util.UUID;

/**
 * Base read request event.
 */
public class ReadModelEvent extends BaseRequestEvent {

  private final String key;

  /**
   * Constructor
   *
   * @param executorKey unique identifier for the user requesting access.
   * @param key         identifier for the model to be retrieved.
   */
  public ReadModelEvent(final String executorKey, final String key) {
    super(executorKey);
    this.key = key;
  }

  /**
   * Retrieves the unique identifier for the model to be retrieved.
   *
   * @return unique identifier.
   */
  public String getKey() {
    return key;
  }

}