package com.egoshard.service.core.event;

import com.egoshard.service.core.domain.Model;

/**
 * Base return from a request for a single entity from persistence.
 */
public class ModelReadEvent<T extends Model> extends BaseResponseEvent {

  private final T model;

  /**
   * Constructor fo a generic read event.
   *
   * @param model model to return.
   */
  public ModelReadEvent(final T model) {
    this.model = model;
  }

  /**
   * Retrieves the retrieved model from the event.
   *
   * @return populated object extending Model.
   */
  public T getModel() {
    return model;
  }

}
