package com.egoshard.service.core.web.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom error object for returning JSON representations of server errors.
 */
public class JsonErrorInfo {

  private final Map<String, Object> values;

  /**
   * Instantiates initial error object.
   */
  public JsonErrorInfo() {
    values = new HashMap<>();
  }

  /**
   * Instantiates error object with a map of key value objects.
   *
   * @param values key model map of error information.
   */
  public JsonErrorInfo(Map<String, Object> values) {
    this.values = values;
  }

  /**
   * Retrieves the key value map of error information.
   *
   * @return key model map object.
   */
  public Map<String, Object> getValues() {
    return values;
  }

  /**
   * Converts key value error information into JSON format.
   *
   * @return JSON model object.
   */
  public ModelAndView asModelAndView() {
    MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
    return new ModelAndView(jsonView, values);
  }

}