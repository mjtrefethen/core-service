package com.egoshard.service.core.locale.web.domain;

import com.egoshard.service.core.web.domain.ModelResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

/**
 * HTTP resource representation of a LocaleType data object.
 */
@ApiModel("Locale Type Resource")
public class LocaleTypeResource extends ModelResource {

  @ApiModelProperty(value = "String display value", required = true)
  private String name;

  public LocaleTypeResource() {
    super();
  }

  /**
   * Constructor used to populate record retrieved from storage.
   *
   * @param key unique public identifier.
   */
  public LocaleTypeResource(String key) {
    super(key);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
