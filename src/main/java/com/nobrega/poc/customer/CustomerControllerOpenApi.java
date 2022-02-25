package com.nobrega.poc.customer;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.nobrega.poc.customer.commons.ApplicationErrorResponse;
import com.nobrega.poc.customer.commons.ResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Customers")
public interface CustomerControllerOpenApi {

  @Operation(summary = "Create a customer", description = "Create a customer.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Customer saved successfully", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerResponse.class))
      }),
      @ApiResponse(responseCode = "400", description = "Some argument sent is not valid or is missing", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApplicationErrorResponse.class))
      }),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApplicationErrorResponse.class))
      })
  })
  CustomerResponse create(@RequestBody @Valid CustomerRequest request);

  @Operation(summary = "Get a customer by id", description = "Get a customer by id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Customer found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerResponse.class))
      }),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApplicationErrorResponse.class))
      })
  })
  CustomerResponse get(@PathVariable("uuid") String uuid);


  @Operation(summary = "Get all customers", description = "Get all customers.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Customer found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ResponseList.class))
      })
  })
  ResponseList<CustomerResponse> get(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize,
                                     @RequestParam(name = "sortBy", defaultValue = "id") String sortBy);


  @Operation(summary = "Update a customer by id", description = "Update a customer by id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Customer successfully updated"),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApplicationErrorResponse.class))
      })
  })
  void update(@PathVariable("uuid") String uuid, @RequestBody @Valid CustomerRequest request);

  @Operation(summary = "Delete a customer by id", description = "Delete a customer by id.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Customer successfully deleted", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerResponse.class))
      }),
      @ApiResponse(responseCode = "404", description = "Not Found", content = {
          @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ApplicationErrorResponse.class))
      })
  })
  void delete(@PathVariable("uuid") String uuid);
}
