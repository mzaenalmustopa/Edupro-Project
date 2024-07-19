package org.edupro.webapi.building.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.webapi.building.model.RoomRes;
import org.edupro.webapi.building.service.RoomService;
import org.edupro.webapi.base.controller.BaseController;
import org.edupro.webapi.building.model.RoomReq;
import org.edupro.webapi.base.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name="keycloak")
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController extends BaseController<RoomRes> {
    private final RoomService service;

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
    private ResponseEntity<Response> save(@RequestBody @Valid RoomReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Response> update(@RequestBody @Valid RoomReq request,
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
