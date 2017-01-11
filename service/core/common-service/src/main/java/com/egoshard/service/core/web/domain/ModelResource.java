package com.egoshard.service.core.web.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Base HTTP Resource object for Model.
 */
@ApiModel(value = "ModelResource", description = "Base representation of a data model identified by a unique key.")
public abstract class ModelResource extends ResourceSupport implements Serializable {

  @ApiModelProperty(name = "Key",
      value = "Unique 128-bit key value identifying a data model element.",
      required = true,
      position = 1)
  private String key;
  @ApiModelProperty(name = "Active",
      value = "Value indicating if a model is in an active state.",
      required = false,
      position = 99)
  private boolean active;

  /**
   * Default constructor for all ModelResource objects.
   */
  public ModelResource() {
  }

  /**
   * Constructor used to populate record retrieved from storage.
   *
   * @param key unique public identifier.
   */
  public ModelResource(final String key) {
    if (key == null) {
      throw new IllegalArgumentException("A null unique identifier cannot be used during Resource creation.");
    }
    this.key = key;
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
   * Gets the active status of a ModelResource object.
   *
   * @return true is the model is active else false.
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the active status of a ModelResource object.
   *
   * @param active the status value to be set.
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ModelResource model = (ModelResource) obj;
    return Objects.equals(active, model.active) && Objects.equals(key, model.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, active);
  }

}
