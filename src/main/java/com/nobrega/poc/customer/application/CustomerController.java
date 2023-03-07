package com.nobrega.poc.customer.application;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

import com.nobrega.poc.customer.domain.CustomerCreatorService;
import com.nobrega.poc.customer.domain.CustomerService;
import com.nobrega.poc.customer.commons.ResponseList;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController implements CustomerControllerOpenApi {

    private final CustomerService customerService;

    private final CustomerCreatorService customerCreatorService;

    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody @Valid CustomerRequest request) {

        return ResponseEntity
                .created(fromCurrentRequest()
                        .path("/{uuid}")
                        .buildAndExpand(customerCreatorService.save(request).getUuid())
                        .toUri())
                .build();
    }

    @ResponseStatus(OK)
    @GetMapping("/{uuid}")
    public CustomerResponse get(
            @PathVariable("uuid") String uuid) {
        return customerService.getCustomerByUuid(uuid);
    }

    @ResponseStatus(OK)
    @GetMapping
    public ResponseList<CustomerResponse> get(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "50") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
        return customerService.getAll(PageRequest.of(page, pageSize, Sort.by(DESC, sortBy)));
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{uuid}")
    public void update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid CustomerRequest request) {
        customerService.update(uuid, request);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void delete(
            @PathVariable("uuid") String uuid) {
        customerService.delete(uuid);
    }
}
