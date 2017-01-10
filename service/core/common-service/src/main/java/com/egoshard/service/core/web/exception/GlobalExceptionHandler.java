package com.egoshard.service.core.web.exception;

import com.egoshard.service.core.exception.UnauthorizedException;
import com.egoshard.service.core.repository.exception.ModelNotFoundException;
import com.egoshard.service.core.repository.exception.ModelNotUniqueException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Global exception handler to convert caught exceptions to valid HTTP error codes and messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {

  /**
   * Handles all base Exceptions and translates them to Internal Server HTTP errors.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ModelAndView defaultHandler(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex);
  }

  /**
   * Handles thrown IllegalArgumentExceptions and translates then to Bad Request HTTP errors.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ModelAndView illegalArgument(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex.toString());
  }

  /**
   * Handles invalid casts for resource identifiers and translates then to Bad Request HTTP errors.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView illegalArgumentCast(HttpServletRequest request, Exception ex) {
    // TODO Move this to localizable file
    return assembleView(request, "A request parameter contains data of the wrong datatype. Please verify parameters and reattempt.");
  }

  /**
   * Handles exception instances where no resource can be found for specified criteria.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ModelNotFoundException.class)
  public ModelAndView entityMissing(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex.toString());
  }

  /**
   * Handles exception instances where multiple resources are found for criteria that should only
   * return one.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ModelNotUniqueException.class)
  public ModelAndView entityNotUnique(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex.toString());
  }

  /**
   * Handles exception instances where access to a requested resource has been denied.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view containing a generic server error.
   */
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(UnauthorizedException.class)
  public ModelAndView unauthorizedAccess(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex.toString());
  }

  /**
   * Assembles request and exception information into a ModelAndView.
   *
   * @param request HttpServletRequest object
   * @param ex the exception to be handled
   * @return model and view.
   */
  private ModelAndView assembleView(HttpServletRequest request, Exception ex) {
    return assembleView(request, ex.getMessage());
  }

  /**
   * Converts a custom JsonErrorInfo object into a ModelAndView return object.
   *
   * @param request HttpServletRequest object
   * @param message returned message.
   * @return model and view.
   */
  private ModelAndView assembleView(HttpServletRequest request, String message) {
    JsonErrorInfo info = new JsonErrorInfo();
    info.getValues().put("url", request.getRequestURL().toString());
    info.getValues().put("error", message);
    return info.asModelAndView();
  }

}
