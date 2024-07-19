package org.edupro.webapi;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
        name = "keycloak",
        openIdConnectUrl = "https://edupro.pass-pdam.com/keycloak/realms/edupro/.well-known/openid-configuration",
        scheme = "bearer",
        type = SecuritySchemeType.OPENIDCONNECT,
        in = SecuritySchemeIn.HEADER
)
public class EduProApp {

    public static void main(String[] args) {
        SpringApplication.run(EduProApp.class, args);
    }

}
