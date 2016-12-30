package com.egoshard.service.core.locale.repository.jdbc;

import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.locale.repository.LocaleTypeRepository;
import com.egoshard.service.core.locale.repository.jdbc.mappers.LocaleTypeRowMapper;
import com.egoshard.service.core.repository.exception.ModelNotFoundException;
import com.egoshard.service.core.repository.jdbc.BaseJdbcRepository;
import com.egoshard.service.core.repository.jdbc.mappers.ModelParameterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository implementation for persisting LocaleType models.
 */
@Repository
public class LocaleTypeJdbcRepository extends BaseJdbcRepository implements LocaleTypeRepository {

  private static final String RETURN_TYPE = "LocaleType";
  private static Logger LOGGER = LoggerFactory.getLogger(LocaleTypeJdbcRepository.class);

  /**
   * Method to retrieve a list of all LocaleTypes.
   *
   * @param includeInactive boolean value indicating that returned data should include inactive models.
   * @return a List of LocaleType objects meeting the specified criteria.
   */
  @Override
  public List<LocaleType> findAll(boolean includeInactive) {

    String sqlSelect = "SELECT record_key, name, active_flag FROM locale_type %s ORDER BY name";
    String sqlWhere = "where active_flag = true";

    List<LocaleType> localeTypeList = jdbcTemplate.query(
        String.format(sqlSelect, includeInactive ? "" : sqlWhere),
        new LocaleTypeRowMapper());
    if (localeTypeList == null || localeTypeList.size() == 0) {
      LOGGER.error(ERROR_MODELLIST_EMPTY);
      throw new ModelNotFoundException(ERROR_MODELLIST_EMPTY);
    }
    return localeTypeList;

  }

  /**
   * Method to retrieve a specific LocalType model by unique identifier.
   *
   * @param key unique model identifier
   * @return populated LocaleType object
   */
  @Override
  public LocaleType findByKey(UUID key) {

    if (key == null) {
      LOGGER.error(ERROR_MODEL_KEY_NULL);
      throw new IllegalArgumentException(ERROR_MODEL_KEY_NULL);
    }

    String sqlSelect = "SELECT record_key, name, active_flag FROM locale_type WHERE record_key = :record_key ORDER BY name";
    SqlParameterSource parameters = ModelParameterFactory.create(key);
    List<LocaleType> localeTypeList = jdbcTemplate.query(sqlSelect, parameters, new LocaleTypeRowMapper());
    if (localeTypeList == null || localeTypeList.size() == 0) {
      String message = String.format(ERROR_MODEL_NOT_FOUND, RETURN_TYPE, key.toString());
      LOGGER.error(message);
      throw new ModelNotFoundException(message);
    }
    return localeTypeList.get(0);

  }

  /**
   * Method to create or update an existing LocaleType model.
   *
   * @param localeType model object to save
   */
  @Override
  public void save(LocaleType localeType) {

    if (localeType == null) {
      LOGGER.error(ERROR_MODEL_ARG_NULL);
      throw new IllegalArgumentException(ERROR_MODEL_ARG_NULL);
    }

    if (localeType.isPersisted()) {
      update(localeType);
    } else {
      insert(localeType);
    }

  }

  /**
   * Method to deactivate an existing LocaleType model.
   *
   * @param key unique model identifier
   */
  @Override
  public void delete(UUID key) {

    if (key == null) {
      LOGGER.error(ERROR_MODEL_KEY_NULL);
      throw new IllegalArgumentException(ERROR_MODEL_KEY_NULL);
    }

    String sqlDelete = "update locale_type set active_flag = false WHERE record_key = :record_key";
    SqlParameterSource parameters = ModelParameterFactory.create(key);
    jdbcTemplate.update(sqlDelete, parameters);

  }

  /**
   * Method to insert a new LocaleType.
   *
   * @param localeType model object to insert
   */
  private void insert(LocaleType localeType) {

    if (localeType == null) {
      LOGGER.error(ERROR_MODEL_ARG_NULL);
      throw new IllegalArgumentException(ERROR_MODEL_ARG_NULL);
    }

    String sqlInsert = "INSERT into locale_type (record_key, name, active_flag) VALUES (:record_key, :name, :active_flag)";
    SqlParameterSource parameters = ModelParameterFactory.create(localeType);
    jdbcTemplate.update(sqlInsert, parameters);

  }

  /**
   * Method to update an existing LocaleType.
   *
   * @param localeType model object to update
   */
  private void update(LocaleType localeType) {

    if (localeType == null) {
      LOGGER.error(ERROR_MODEL_ARG_NULL);
      throw new IllegalArgumentException(ERROR_MODEL_ARG_NULL);
    }

    String sqlUpdate = "UPDATE locale_type set name = :name, active_flag = active_flag where record_key = :record_key";
    SqlParameterSource parameters = ModelParameterFactory.create(localeType);
    jdbcTemplate.update(sqlUpdate, parameters);

  }

}
