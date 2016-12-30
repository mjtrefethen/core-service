package com.egoshard.service.core.locale.domain;

import com.egoshard.service.core.domain.Model;

import java.util.Objects;
import java.util.UUID;

/**
 * Data representation of a region type for a locale. <p> This is used to differentiate the various
 * region and sub-region categorization used by respective countries globally. </p>
 */
public class LocaleType extends Model {

  private final String name;

  public LocaleType(String name) {
    this.name = name;
  }

  public LocaleType(UUID key, String name) {
    super(key);
    this.name = name;
  }

  public LocaleType(UUID key, String name, boolean active) {
    super(key, active);
    this.name = name;
  }

  /**
   * Retrieves the type name display value.
   *
   * @return display value.
   */
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    LocaleType that = (LocaleType) obj;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name);
  }

}
