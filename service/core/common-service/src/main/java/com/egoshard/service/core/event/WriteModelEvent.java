package com.egoshard.service.core.event;

import com.egoshard.service.core.domain.Model;

import java.util.UUID;

/**
 * Base request event for a write operation on a Model.
 */
public class WriteModelEvent<T extends Model> extends BaseRequestEvent {

  private final T model;

  /**
   * Constructor to create an immutable event object.
   *
   * @param executorKey unique identifier for the user requesting access.
   * @param model       object to be created.
   */
  public WriteModelEvent(final String executorKey, final T model) {
    super(executorKey);
    this.model = model;
  }

  /**
   * Retrieves the model to be created.
   *
   * @return an object that extends from the Model base class.
   */
  public T getModel() {
    return model;
  }

}