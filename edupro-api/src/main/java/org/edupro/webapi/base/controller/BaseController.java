package org.edupro.webapi.base.controller;

import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.base.model.Response;
import org.edupro.webapi.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseController<T> extends BaseService {
    public ResponseEntity<Response> getObjectResponse(Object result){
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(Constant.SUCCESS_STATUS)
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(T result){
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(Constant.SUCCESS_STATUS)
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(Optional<T> result) {
        return result.<ResponseEntity<Response>>map(res -> ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(Constant.SUCCESS_STATUS)
                        .data(res)
                        .build()
        )).orElseGet(() -> ResponseEntity.internalServerError().body(
                Response.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(Constant.FAILED_STATUS)
                        .data(null)
                        .build()
        ));
    }

    public ResponseEntity<Response> getResponse(List<T> result){
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(Constant.SUCCESS_STATUS)
                        .data(result)
                        .build()
        );
    }

    public String getUserId(){
        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String dob = "";
        String userId = "";

        OidcIdToken token = user.getIdToken();

        Map<String, Object> customClaims = token.getClaims();

        if (customClaims.containsKey("user_id")) {
            userId = String.valueOf(customClaims.get("user_id"));
        }

        if (customClaims.containsKey("DOB")) {
            dob = String.valueOf(customClaims.get("DOB"));
        }

        return userId;
    }
}
