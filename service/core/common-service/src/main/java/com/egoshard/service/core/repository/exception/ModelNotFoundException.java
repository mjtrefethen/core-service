package com.egoshard.service.core.repository.exception;

import com.egoshard.service.core.exception.DataAccessException;

/**
 * Runtime exception thrown when an entity is expected but cannot be found.
 */
public class ModelNotFoundException extends DataAccessException {

  /**
   * Constructs a new runtime exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   */
  public ModelNotFoundException(String message) {
    super(message);    //To change body of overridden methods use File | Settings | File Templates.
  }

  /**
   * Constructs a new runtime exception with the specified detail message and
   * cause.
   *
   * <p>Note that the detail message associated with
   * {@code cause} is <i>not</i> automatically incorporated in
   * this runtime exception's detail message.
   *
   * @param message the detail message (which is saved for later retrieval
   *                by the {@link #getMessage()} method).
   * @param cause   the cause (which is saved for later retrieval by the
   *                {@link #getCause()} method).  (A <tt>null</tt> value is
   *                permitted, and indicates that the cause is nonexistent or
   *                unknown.)
   * @since 1.4
   */
  public ModelNotFoundException(String message, Throwable cause) {
    super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
  }

}
