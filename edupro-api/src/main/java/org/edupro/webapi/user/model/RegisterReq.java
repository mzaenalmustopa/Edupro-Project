package org.edupro.webapi.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<String> roles;
}