package com.egoshard.service.core.domain;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base abstract representation of a database record. All extensions of Model will have a UUID
 * identifier and a String representation of a human readable name. Extending classes must implement
 * getters and setters for a Name property.
 */
@MappedSuperclass
public class Model {

  @Id
  @GeneratedValue
  @Column(name = "record_id")
  private Long id;

  @Column(name = "record_key")
  private String key;

  @Column(name = "active_flag")
  private boolean active;

  /**
   * Instantiates a new Model object with a random UUID This operation is intended for use when
   * creating new objects that do not yet have data persistence.
   */
  public Model() {
    this.key = UUID.randomUUID().toString();
    this.active = true;
  }

  /**
   * Instantiates a new Model object using an existing persistence key.
   * This will default the Model in an active state.
   *
   * @param key existing persistence key
   */
  public Model(String key) {
    this.key = key;
    this.active = true;
  }

  /**
   *
   * @param key
   * @param active
   */
  public Model(String key, boolean active) {
    this.key = key;
    this.active = active;
  }

  /**
   *
   * @param id
   * @param key
   * @param isActive
   */
  protected Model(Long id, String key, boolean isActive) {
    this.id = id;
    this.key = key;
    this.active = isActive;
  }

  /**
   * Gets the public UUID for this record.
   *
   * @return unique identifier for this record object.
   */
  public String getKey() {
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
    if (!(obj instanceof Model)) {
      return false;
    }
    Model model = (Model) obj;
    return active == model.active
        && Objects.equals(key, model.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, active);
  }

}