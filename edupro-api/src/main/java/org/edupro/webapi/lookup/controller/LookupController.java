package org.edupro.webapi.lookup.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.base.model.Response;
import org.edupro.webapi.constant.Constant;
import org.edupro.webapi.lookup.model.LookupReq;
import org.edupro.webapi.lookup.model.LookupRes;
import org.edupro.webapi.lookup.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name="keycloak")
@RequestMapping("/api/v1/lookup")
@RequiredArgsConstructor
public class LookupController extends BaseController<LookupRes> {
    private final LookupService service;

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

    @GetMapping("/group/{group}")
    private ResponseEntity<Response> getGroup(@PathVariable("group") String group){
        var result = service.getByGroup(group);
        return getResponse(result);
    }

    @PostMapping
    private ResponseEntity<Response> save(@RequestBody @Valid LookupReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid LookupReq request,
                                            @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }

    @GetMapping("/group")
    private ResponseEntity<Response> getGroup(){
        var result = service.getByGroup(Constant.GROUP_MAIN);
        return getResponse(result);
    }
}
