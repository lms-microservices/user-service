package com.lms.user.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestContext {
    public String getUserId(HttpServletRequest request) {
        return request.getHeader("X-User-Id");
    }

    public String getUserRole(HttpServletRequest request) {
        return request.getHeader("X-User-Role");
    }

    public boolean isAdmin(HttpServletRequest request) {
        return "ADMIN".equals(getUserRole(request));
    }

    public boolean isInstructor(HttpServletRequest request) {
        return "INSTRUCTOR".equals(getUserRole(request));
    }
}
