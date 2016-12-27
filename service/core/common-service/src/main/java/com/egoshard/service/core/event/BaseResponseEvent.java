package com.egoshard.service.core.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Base response for all service interactions.
 */
public class BaseResponseEvent extends BaseEvent {

  private final List<Exception> exceptions = new ArrayList<>();

  /**
   * Gets a list of exceptions that may have occurred during service operations.
   *
   * @return a populated list of exceptions or an empty list if none occurred.
   */
  public List<Exception> getExceptions() {
    return exceptions;
  }

  /**
   * Gets the general status of the response, indicating if exceptions occurred.
   *
   * @return true if the exception list has been populated, otherwise false.
   */
  public boolean isSuccess() {
    return exceptions.size() == 0;
  }

}