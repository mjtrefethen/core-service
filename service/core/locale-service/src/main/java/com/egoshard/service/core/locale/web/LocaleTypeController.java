package com.egoshard.service.core.locale.web;

import com.egoshard.service.core.event.ModelListReadEvent;
import com.egoshard.service.core.event.ModelReadEvent;
import com.egoshard.service.core.event.ModelWrittenEvent;
import com.egoshard.service.core.event.ReadModelEvent;
import com.egoshard.service.core.event.ReadModelListEvent;
import com.egoshard.service.core.event.WriteModelEvent;
import com.egoshard.service.core.locale.domain.LocaleType;
import com.egoshard.service.core.locale.service.LocaleTypeService;
import com.egoshard.service.core.locale.web.domain.LocaleTypeAssembler;
import com.egoshard.service.core.locale.web.domain.LocaleTypeResource;
import com.egoshard.service.core.util.UuidUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/**
 * REST controller exposing LocaleType operations.
 */
@Api(value = "LocaleType", description = "Operations on LocaleType.", tags = "Locale Type")
@RestController
public class LocaleTypeController {

  private static Logger LOGGER = LoggerFactory.getLogger(LocaleTypeController.class);

  private final LocaleTypeService localeTypeService;

  /**
   * Constructor for wiring in business logic service implementation.
   *
   * @param localeTypeService business logic service
   */
  @Autowired
  public LocaleTypeController(LocaleTypeService localeTypeService) {
    this.localeTypeService = localeTypeService;
  }

  /**
   * REST method for retrieving all LocalTypes.
   *
   * @return an HTTP response object wrapping a list of LocaletypeResources.
   */
  @ApiOperation(value = "Fetch all LocaleType records",
      notes = "Retrieves all active LocaleType records irrespective of typing and with a default alphabetic ordering.",
      response = LocaleTypeResource.class,
      responseContainer = "List")
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/locales/types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  @ResponseBody
  public ResponseEntity<List<LocaleTypeResource>> getList() {

    ModelListReadEvent<LocaleType> event = localeTypeService.getList(new ReadModelListEvent(UUID.randomUUID().toString()));
    return new ResponseEntity<>(LocaleTypeAssembler.toResourceList(event.getModelList()), HttpStatus.OK);

  }

  /**
   * REST method to retrieve a LocaleTypeResource for its unique identifier.
   *
   * @param key unique identifier
   * @return HTTP resource object
   */
  @ApiOperation(value = "Fetch LocaleType by identifier",
      notes = "Retrieves a specific LocaleType record for a unique record identifier.",
      response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/locales/types/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
  @ResponseBody
  public ResponseEntity<LocaleTypeResource> getByKey(@ApiParam(value = "Unique identifier of the LocaleType to fetch", required = true) @PathVariable String key) {

    if (key == null || !UuidUtils.isStringValidUuid(key)) {
      throw new IllegalArgumentException("The identifier specified appears to be invalid, please verify and resubmit the request.");
    }

    ModelReadEvent<LocaleType> event = localeTypeService.get(new ReadModelEvent(UUID.randomUUID().toString(), key));
    return new ResponseEntity<>(LocaleTypeAssembler.toResource(event.getModel()), HttpStatus.OK);

  }

  /**
   * REST method to create a new LocaleType from a HTTP POST.
   *
   * @param resource LocaleTypeResource
   * @return HTTP response containing only a location header.
   */
  @ApiOperation(value = "Create a new LocaleType by using a HTTP POST operation.", notes = "Creates a new LocaleType record, returning a location header. This method is not idempotent.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Record created successfully, please see location header for new resource URI."),
      @ApiResponse(code = 400, message = "The request has structural issues, the operation cannot be completed without modification.")})
  @RequestMapping(value = "/locales/types", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
  public ResponseEntity createPost(final @RequestBody LocaleTypeResource resource) {
    return ResponseEntity.noContent().location(
        ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{key}")
            .buildAndExpand(create(resource).toString()).toUri()).build();
  }

  @ApiOperation(value = "Create a new LocaleType using an HTTP PUT operation.", notes = "Retrieves a specific LocaleType record for a unique record identifier.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/locales/types", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
  public void createPut(final @RequestBody LocaleTypeResource body) {

  }

  /**
   *
   * @param resource
   */
  private String create(final LocaleTypeResource resource) {
    ModelWrittenEvent<LocaleType> event = localeTypeService.create(new WriteModelEvent<>(UUID.randomUUID().toString(), LocaleTypeAssembler.toModel(resource)));
    return event.getModel().getKey();
  }

  @ApiOperation(value = "Update individual attributes on a LocaleType using the HTTP PATCH operation.", notes = "Retrieves a specific LocaleType record for a unique record identifier.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/locales/types/{key}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON)
  public void updatePatch(@ApiParam(value = "Unique identifier of the LocaleType to update", required = true) @PathVariable String key, @RequestBody HashMap<String, String> body) {

  }

  @ApiOperation(value = "Update a LocaleType using the HTTP POST operation.", notes = "Retrieves a specific LocaleType record for a unique record identifier.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/locales/types/{key}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
  public void updatePost(@ApiParam(value = "Unique identifier of the LocaleType to update", required = true) @PathVariable String key, @RequestBody LocaleTypeResource body) {

  }

  @ApiOperation(value = "Update a LocaleType using the HTTP PUT operation.", notes = "Retrieves a specific LocaleType record for a unique record identifier.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(value = "/locales/types/{key}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
  public void updatePut(@ApiParam(value = "Unique identifier of the LocaleType to update", required = true) @PathVariable String key, @RequestBody LocaleTypeResource body) {

  }

  @ApiOperation(value = "Delete a LocaleType.", notes = "Retrieves a specific LocaleType record for a unique record identifier.", response = LocaleTypeResource.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "The unique record identifier specified is structurally invalid"),
      @ApiResponse(code = 404, message = "A LocaleType could not be found for the specified unique identifier")})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = "/locales/types/{key}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON)
  public void delete(@ApiParam(value = "Unique identifier of the LocaleType to delete", required = true) @PathVariable String key, @RequestBody LocaleTypeResource body) {

  }

  private String update(final LocaleTypeResource resource) {
    return null;
  }

}