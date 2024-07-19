package org.edupro.webapi.person.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.base.model.Response;
import org.edupro.webapi.person.model.PersonReq;
import org.edupro.webapi.person.model.PersonRes;
import org.edupro.webapi.person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name="keycloak")
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController extends BaseController<PersonRes> {
    private final PersonService service;

    @GetMapping("")
    private ResponseEntity<Response> get(){
        var result = service.get();

        return this.getResponse(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("")
    private ResponseEntity<Response> save(@RequestBody @Valid PersonReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid PersonReq request,
                                            @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
