package com.egoshard.service.core.repository.jdbc.mappers;

import com.egoshard.service.core.domain.Model;
import com.egoshard.service.core.repository.jdbc.OperationType;
import com.egoshard.service.core.util.UuidUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.UUID;

/**
 * Base factory for producting parameter sources from a Model and OperationType.
 */
public class ModelParameterFactory {

  /**
   * Creates a source by retrieving a record key from an existing Model.
   *
   * @param operation data operation to be performed.
   * @param model Model object to retrieve a key from.
   * @return SQL paramter source populated with a model key.
   */
  public static MapSqlParameterSource create(OperationType operation, Model model) {
    return create(UuidUtils.toBytes(model.getKey()));
  }

  /**
   * Creates a source using a record key as a UUID.
   *
   * @param operation data operation to be performed.
   * @param key UUID key to populate.
   * @return SQL paramter source populated with a model key.
   */
  public static MapSqlParameterSource create(OperationType operation, UUID key) {
    return create(UuidUtils.toBytes(key));
  }

  /**
   * Method to return a new MapSqlParameterSource from a UUID byte array.
   *
   * @param keyBytes key bytes.
   * @return SQL paramter source.
   */
  private static MapSqlParameterSource create(byte[] keyBytes) {
    return new MapSqlParameterSource("record_key", keyBytes);
  }


}
