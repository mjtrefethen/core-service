package com.egoshard.service.core.repository.jdbc.mappers;

import com.egoshard.service.core.domain.Model;
import com.egoshard.service.core.repository.jdbc.OperationType;
import com.egoshard.service.core.util.UuidUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.UUID;

/**
 *
 */
public class ModelParameterFactory {

  /**
   * @param operation
   * @param model
   * @return
   */
  public static MapSqlParameterSource create(OperationType operation, Model model) {
    return create(UuidUtils.toBytes(model.getKey()));
  }

  /**
   * @param operation
   * @param key
   * @return
   */
  public static MapSqlParameterSource create(OperationType operation, UUID key) {
    return create(UuidUtils.toBytes(key));
  }

  /**
   * @param keyBytes
   * @return
   */
  private static MapSqlParameterSource create(byte[] keyBytes) {
    return new MapSqlParameterSource("record_key", keyBytes);
  }


}
