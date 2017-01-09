package com.egoshard.service.core.locale.service;

import com.egoshard.service.core.event.ModelListReadEvent;
import com.egoshard.service.core.event.ModelReadEvent;
import com.egoshard.service.core.event.ModelWrittenEvent;
import com.egoshard.service.core.event.ReadModelEvent;
import com.egoshard.service.core.event.ReadModelListEvent;
import com.egoshard.service.core.event.WriteModelEvent;
import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.locale.repository.LocaleTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LocaleType service implementation.
 */
@Service
public class LocaleTypeServiceImpl implements LocaleTypeService {

  private static Logger LOGGER = LoggerFactory.getLogger(LocaleTypeServiceImpl.class);

  private final LocaleTypeRepository localeTypeRepository;

  /**
   * Constructor to initialize the service with a wired data access implementation.
   *
   * @param localeTypeRepository data access repository
   */
  @Autowired
  public LocaleTypeServiceImpl(final LocaleTypeRepository localeTypeRepository) {
    this.localeTypeRepository = localeTypeRepository;
  }

  /**
   * Method to retrieve an individual LocaleType model.
   *
   * @param event read event containing search criteria for identifying the model to retrieve.
   * @return a return event wrapping a populated model.
   */
  @Override
  public ModelReadEvent<LocaleType> get(ReadModelEvent event) {
    return new ModelReadEvent<>(localeTypeRepository.findByKey(event.getKey().toString()));
  }

  /**
   * Method to retrieve an list of LocaleType model objects.
   *
   * @param event read event containing search criteria for identifying the models to retrieve.
   * @return a return event wrapping a populated model list.
   */
  @Override
  public ModelListReadEvent<LocaleType> getList(ReadModelListEvent event) {
    return new ModelListReadEvent<>(localeTypeRepository.findAll(true));
  }

  /**
   * Method to create a new LocaleType model object.
   *
   * @param event write event containing the details of the model to create.
   * @return a return event wrapping a populated model.
   */
  @Override
  public ModelWrittenEvent<LocaleType> create(WriteModelEvent<LocaleType> event) {
    return null;
  }

  /**
   * Method to update an existing LocaleType model object.
   *
   * @param event write event containing the details of the model to update.
   * @return a return event wrapping a populated model.
   */
  @Override
  public ModelWrittenEvent<LocaleType> update(WriteModelEvent<LocaleType> event) {
    return null;
  }

  /**
   * Method to delete an existing LocaleType model object.
   *
   * @param event write event containing the details of the model to delete.
   * @return a return event.
   */
  @Override
  public ModelWrittenEvent<LocaleType> delete(WriteModelEvent<LocaleType> event) {
    return null;
  }
}
