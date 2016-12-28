package com.egoshard.service.core.locale.repository.jdbc.mappers;

import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.repository.jdbc.OperationType;
import com.egoshard.service.core.repository.jdbc.mappers.ModelParameterFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * Factory to create populated MapSqlParameterSource for requested SQL operations.
 */
public class LocaleTypeParameterFactory extends ModelParameterFactory {

  /**
   * Create a new MapSqlParameterSource implementation holding a Map of parameters that match the correct pattern
   * based on the requested operation.
   *
   * @param operation  SQL operation being performed.
   * @param localeType model to construct the parameter map from.
   * @return a populated parameter map.
   */
  public static MapSqlParameterSource create(OperationType operation, LocaleType localeType) {

    MapSqlParameterSource source;
    if (operation.equals(OperationType.CREATE)
        || operation.equals(OperationType.UPDATE)
        || operation.equals(OperationType.DELETE)) {
      source = ModelParameterFactory.create(operation, localeType);
    } else {
      // TODO move messaging to localizable file.
      throw new IllegalArgumentException("An illegal operation type was passed to map parameters.");
    }
    // Common parameters
    source.addValue("name", localeType.getName());
    source.addValue("active_flag", localeType.isActive());
    return source;

  }

}