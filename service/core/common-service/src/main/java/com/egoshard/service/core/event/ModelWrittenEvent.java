package com.egoshard.service.core.event;

import com.egoshard.service.core.domain.Model;

/**
 * Base response for a write request on a Model.
 *
 * @param <T> a class type that extends from Model.
 */
public class ModelWrittenEvent<T extends Model> extends BaseResponseEvent {

  private final T model;

  /**
   * Base constructor for an immutable model written event.
   *
   * @param model newly written object.
   */
  public ModelWrittenEvent(final T model) {
    this.model = model;
  }

  /**
   * Retrieves the newly written entity.
   *
   * @return new entity.
   */
  public T getModel() {
    return model;
  }

}