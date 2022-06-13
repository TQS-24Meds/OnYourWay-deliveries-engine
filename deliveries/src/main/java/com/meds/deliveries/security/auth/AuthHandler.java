package com.meds.deliveries.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Auth Manager
 *
 * Class to manage the authentication system and perform actions taking into account
 * the current user of the system.
 *
 * @author Hugo1307
 * this file was inherited from PI course and authorized by the author
 */
@Component
public class AuthHandler {

    public Authentication getCurrentAuthInstance() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUsername() {
        return getCurrentAuthInstance().getName();
    }

}
