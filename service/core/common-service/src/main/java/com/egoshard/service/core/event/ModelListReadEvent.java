package com.egoshard.service.core.event;

import com.egoshard.service.core.domain.Model;

import java.util.List;

/**
 * Base return from a request for a list of entities from persistence.
 */
public class ModelListReadEvent<T extends Model> extends BaseResponseEvent {

  private final List<T> modelList;

  /**
   * Constructor for a generic list read event.
   *
   * @param modelList list of objects extending from Model.
   */
  public ModelListReadEvent(final List<T> modelList) {
    this.modelList = modelList;
  }

  /**
   * Retrieves a list of returned models.
   *
   * @return list of models.
   */
  public List<T> getModelList() {
    return modelList;
  }

}
