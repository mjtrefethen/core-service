package com.egoshard.service.core.repository.jdbc.mappers;

import com.egoshard.service.core.domain.Model;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * Base factory for producting parameter sources from a Model and OperationType.
 */
public class ModelParameterFactory {

  /**
   * Creates a new source map.
   *
   * @return an empty SQL parameter source.
   */
  public static MapSqlParameterSource create() {
    return new MapSqlParameterSource();
  }

  /**
   * Creates a source by retrieving a record key from an existing Model.
   *
   * @param model Model object to retrieve a key from.
   * @return SQL parameter source populated with a model key.
   */
  public static MapSqlParameterSource create(Model model) {
    return create(model.getKey().toString());
  }

  /**
   * Creates a source using a record key as a UUID.
   *
   * @param key UUID key to populate.
   * @return SQL parameter source populated with a model key.
   */
  public static MapSqlParameterSource create(String key) {
    return new MapSqlParameterSource("record_key", key);
  }

}
