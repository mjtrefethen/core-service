package com.egoshard.service.core.locale.domain;

import com.egoshard.service.core.domain.Model;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Data representation of a region type for a locale. <p> This is used to differentiate the various
 * region and sub-region categorization used by respective countries globally. </p>
 */
@Entity
@Table(name="cd_region_type")
public class LocaleType extends Model {

  @Column(name = "name")
  private String name;

  /**
   * Instantiates a new Model object with a random UUID This operation is intended for use when
   * creating new objects that do not yet have data persistence.
   */
  public LocaleType() {
  }

  public LocaleType(String name) {
    this.name = name;
  }

  public LocaleType(String key, String name) {
    super(key);
    this.name = name;
  }

  public LocaleType(String key, String name, boolean isActive) {
    super(key, isActive);
    this.name = name;
  }

  protected LocaleType(Long id, String key, boolean isActive, String name) {
    super(id, key, isActive);
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
