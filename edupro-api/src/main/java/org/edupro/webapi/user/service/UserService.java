package org.edupro.webapi.user.service;

import org.edupro.webapi.user.model.ChangePasswordReq;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordReq request, Principal connectedUser);
}
