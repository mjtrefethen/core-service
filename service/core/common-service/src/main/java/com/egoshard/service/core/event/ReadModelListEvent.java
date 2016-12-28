package com.egoshard.service.core.event;

import java.util.UUID;

/**
 * Base event to return a list of Model objects.
 */
public class ReadModelListEvent extends BaseRequestEvent {

  private boolean includeInactive = false;

  /**
   * Constructor to create an event with only executor information.
   *
   * @param executorKey unique identifier for the user requesting access.
   */
  public ReadModelListEvent(final UUID executorKey) {
    super(executorKey);
  }

  /**
   * Constuctor to create an event with an executor and a filter for active state.
   *
   * @param executorKey     unique identifier for the user requesting access.
   * @param includeInactive boolean value indicating if inactive Model objects should ne returned.
   */
  public ReadModelListEvent(final UUID executorKey, final boolean includeInactive) {
    super(executorKey);
    this.includeInactive = includeInactive;
  }

  /**
   * Retrieves the inactive filter setting.
   *
   * @return true if event operation should include inactive Model objects, else false.
   */
  public boolean IncludeInactive() {
    return includeInactive;
  }

}