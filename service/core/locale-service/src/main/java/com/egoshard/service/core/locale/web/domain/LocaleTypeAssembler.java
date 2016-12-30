package com.egoshard.service.core.locale.web.domain;

import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.locale.web.LocaleTypeController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Assembler to convert object from HTTP resources to Model objects and back.
 */
public class LocaleTypeAssembler {


  /**
   * Converts a model to a resource.
   *
   * @param model object to convert
   * @return HTTP resource
   */
  public static LocaleTypeResource toResource(LocaleType model) {
    LocaleTypeResource resource = new LocaleTypeResource(model.getKey());
    resource.add(linkTo(methodOn(LocaleTypeController.class).getByKey(model.getKey().toString())).withSelfRel());
    resource.setName(model.getName());
    resource.setActive(model.isActive());
    return resource;
  }

  /**
   * Converts a model list to a resource list.
   *
   * @param models object list to convert
   * @return HTTP resource list
   */
  public static List<LocaleTypeResource> toResourceList(List<LocaleType> models) {
    return models.stream().map(LocaleTypeAssembler::toResource).collect(Collectors.toList());
  }

  /**
   * Converts a HTTP resource to a model object.
   *
   * @param resource HTTP resource to convert
   * @return model object
   */
  public static LocaleType toModel(LocaleTypeResource resource) {
    return new LocaleType(
        resource.getKey(),
        resource.getName(),
        resource.isActive());
  }

  /**
   * Converts a list of HTTP resources to a list of model objects.
   *
   * @param resources HTTP resource list to convert.
   * @return list of model objects
   */
  public static List<LocaleType> toModelList(List<LocaleTypeResource> resources) {
    return resources.stream().map(LocaleTypeAssembler::toModel).collect(Collectors.toList());
  }

}
