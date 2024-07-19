package org.edupro.webapi.academic.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.academic.model.AcademicYearReq;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.base.model.Response;
import org.edupro.webapi.academic.model.AcademicYearRes;
import org.edupro.webapi.academic.service.AcademicYearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/academic")
@RequiredArgsConstructor
@SecurityRequirement(name="keycloak")
public class AcademicController extends BaseController<AcademicYearRes> {
    private final AcademicYearService service;

    @GetMapping
    private ResponseEntity<Response> get(){
        var result = service.get();
        return this.getResponse(result);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);

        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid AcademicYearReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid AcademicYearReq request,
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
