package com.egoshard.service.core.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * Base repository containing common data repository functionality.
 */
public abstract class BaseJdbcRepository {

  // TODO Move this to localizable file
  protected static final String ERROR_MODELLIST_EMPTY = "A data access request to retreive all %s objects returned an empty list. This is an unexpected outcome and might represent a more serious problem with the database.";
  protected static final String ERROR_MODEL_NOT_FOUND = "A %s model could not be found for the specified key, %s.";
  protected static final String ERROR_MODEL_KEY_NULL = "A null model key was passed to a findByKey data access method. A model key is required for this method to function.";
  protected static final String ERROR_MODEL_ARG_NULL = "A null model was passed to a data access method. A model is required for this method to function.";

  private NamedParameterJdbcTemplate jdbcTemplate;

  /**
   *
   * @return
   */
  public NamedParameterJdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  /**
   *
   * @param jdbcTemplate
   */
  protected void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

}
