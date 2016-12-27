package com.egoshard.service.core.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * Base abstract representation of a database record. All extensions of Model will have a UUID
 * identifier and a String representation of a human readable name. Extending classes must implement
 * getters and setters for a Name property.
 */
public class Model {

  protected final UUID key;
  protected final boolean active;
  protected final boolean persisted;

  /**
   * Instantiates a new Model object with a random UUID
   * This operation is intended for use when creating new objects that do not yet have data persistence.
   */
  public Model() {
    this.key = UUID.randomUUID();
    this.active = true;
    this.persisted = false;
  }

  /**
   * Instantiates a new Model object using an existing persistence key.
   * This will default the Model in an active state.
   *
   * @param key existing persistence key
   */
  public Model(UUID key) {
    this.key = key;
    this.active = true;
    this.persisted = true;
  }

  /**
   * Instantiates a new Model object using an existing persistence key and a provided state.
   *
   * @param key    existing persistence key
   * @param active Model state
   */
  public Model(UUID key, boolean active) {
    this.key = key;
    this.active = active;
    this.persisted = true;
  }

  /**
   * Gets the public UUID for this record.
   *
   * @return unique identifier for this record object.
   */
  public UUID getKey() {
    return key;
  }

  /**
   * Gets the active status of a Model object.
   *
   * @return true is the model is active else false.
   */
  public boolean isActive() {
    return active;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Model model = (Model) obj;
    return Objects.equals(active, model.active) && Objects.equals(key, model.key);
  }

  /**
   * Returns a value indicating whether a Model has been persisted to data storage.
   *
   * @return true if the Model has been saved, else false.
   */
  public boolean isPersisted() {
    return persisted;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, active);
  }

}