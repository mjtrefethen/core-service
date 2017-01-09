package com.egoshard.service.core.locale.repository.jdbc.mappers;

import com.egoshard.service.core.locale.domain.LocaleType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * Object mapper to assemble a LocaleType model from a ResultSet.
 */
public class LocaleTypeRowMapper implements RowMapper<LocaleType> {

  private static Logger LOGGER = LoggerFactory.getLogger(LocaleTypeRowMapper.class);

  /**
   * Implementation of LocaleType specific row mapping.
   *
   * @param rs the ResultSet to map (pre-initialized for the current row)
   * @param rowNum the number of the current row
   * @return the LocaleType result object for the current row
   */
  @Override
  public LocaleType mapRow(ResultSet rs, int rowNum) throws SQLException {

    return new LocaleType(
        UUID.fromString(rs.getString("record_key")),
        rs.getString("name"),
        rs.getBoolean("active_flag")
    );

  }

}