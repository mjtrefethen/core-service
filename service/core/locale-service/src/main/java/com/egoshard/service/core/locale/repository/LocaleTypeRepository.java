package com.egoshard.service.core.locale.repository;

import com.egoshard.service.core.locale.domain.LocaleType;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for interacting with LocaleType data access functionality.
 */
public interface LocaleTypeRepository {

  /**
   * Method to retrieve a list of all LocaleTypes.
   *
   * @param includeInactive boolean value indicating that returned data should include inactive models.
   * @return a List of LocaleType objects meeting the specified criteria.
   */
  List<LocaleType> findAll(final boolean includeInactive);

  /**
   * Method to retrieve a specific LocalType model by unique identifier.
   *
   * @param key unique model identifier
   * @return populated LocaleType object
   */
  LocaleType findByKey(final String key);

  /**
   * Method to create or update an existing LocaleType model.
   *
   * @param localeType model object to save
   */
  UUID save(final LocaleType localeType);

  /**
   * Method to deactivate an existing LocaleType model.
   *
   * @param key unique model identifier
   */
  void delete(final String key);

}
