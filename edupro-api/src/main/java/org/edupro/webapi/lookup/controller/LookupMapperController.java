package org.edupro.webapi.lookup.controller;

import lombok.RequiredArgsConstructor;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.base.model.Response;
import org.edupro.webapi.lookup.service.LookupMapperService;
import org.edupro.webapi.lookup.model.LookupRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/lookup")
@RequiredArgsConstructor
public class LookupMapperController extends BaseController<LookupRes> {
    private final LookupMapperService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response> lookupGetById(@PathVariable("id") String id) {
        return getResponse(service.getById(id));
    }

    @GetMapping("/group/{group}")
    public ResponseEntity<Response> lookupByGroup(@PathVariable("group") String group) {
        return getResponse(service.getByGroup(group));
    }
}
