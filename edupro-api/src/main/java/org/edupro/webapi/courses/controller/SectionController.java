package org.edupro.webapi.courses.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.courses.model.CourseSectionReq;
import org.edupro.webapi.courses.model.CourseSectionRes;
import org.edupro.webapi.courses.service.CourseSectionService;
import org.edupro.webapi.base.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name="keycloak")
@RequestMapping("/api/v1/section")
@RequiredArgsConstructor
public class SectionController extends BaseController<CourseSectionRes> {
    private final CourseSectionService service;

    @GetMapping("/{courseId}")
    private ResponseEntity<Response> getByCourseId(@PathVariable("courseId") String courseId){
        var result = service.getByCourseId(courseId);
        return this.getResponse(result);
    }

    @GetMapping("/{courseId}/topic")
    private ResponseEntity<Response> getTopic(@PathVariable("courseId") String id){
        var result = service.getByTopic(id);
        return getResponse(result);
    }

    @GetMapping("/{id}/detail")
    private ResponseEntity<Response> get(@PathVariable("id") String id){
        var result = service.getById(id);
        return getResponse(result);
    }

    @PostMapping("/{id}")
    private ResponseEntity<Response> save(@PathVariable("id") String id, @RequestBody @Valid CourseSectionReq request){
        var result = service.save(id, request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid CourseSectionReq request, @PathVariable("id") String id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> delete(@PathVariable("id") String id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
