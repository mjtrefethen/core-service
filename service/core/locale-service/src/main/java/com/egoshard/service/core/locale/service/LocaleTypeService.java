package com.egoshard.service.core.locale.service;


import com.egoshard.service.core.event.ModelListReadEvent;
import com.egoshard.service.core.event.ModelReadEvent;
import com.egoshard.service.core.event.ModelWrittenEvent;
import com.egoshard.service.core.event.ReadModelEvent;
import com.egoshard.service.core.event.ReadModelListEvent;
import com.egoshard.service.core.event.WriteModelEvent;
import com.egoshard.service.core.locale.domain.LocaleType;

/**
 * Service interface for interacting with LocaleType business logic.
 */
public interface LocaleTypeService {

  /**
   * Method to retrieve an individual LocaleType model.
   *
   * @param event read event containing search criteria for identifying the model to retrieve.
   * @return a return event wrapping a populated model.
   */
  ModelReadEvent<LocaleType> get(final ReadModelEvent event);

  /**
   * Method to retrieve an list of LocaleType model objects.
   *
   * @param event read event containing search criteria for identifying the models to retrieve.
   * @return a return event wrapping a populated model list.
   */
  ModelListReadEvent<LocaleType> getList(final ReadModelListEvent event);

  /**
   * Method to create a new LocaleType model object.
   *
   * @param event write event containing the details of the model to create.
   * @return a return event wrapping a populated model.
   */
  ModelWrittenEvent<LocaleType> create(final WriteModelEvent<LocaleType> event);

  /**
   * Method to update an existing LocaleType model object.
   *
   * @param event write event containing the details of the model to update.
   * @return a return event wrapping a populated model.
   */
  ModelWrittenEvent<LocaleType> update(final WriteModelEvent<LocaleType> event);

  /**
   * Method to delete an existing LocaleType model object.
   *
   * @param event write event containing the details of the model to delete.
   * @return a return event.
   */
  ModelWrittenEvent<LocaleType> delete(final WriteModelEvent<LocaleType> event);

}
