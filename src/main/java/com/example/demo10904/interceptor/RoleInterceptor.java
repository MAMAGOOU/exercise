package com.example.demo10904.interceptor;

import com.example.demo10904.api.annotations.Role;
import com.example.demo10904.exception.UnauthorizedException;
import com.example.demo10904.model.User;
import com.example.demo10904.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.lang.reflect.Method;

/**
 * @author 19750
 * @version 1.0
 */
@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Role roleAnnotation = method.getAnnotation(Role.class);

        if (roleAnnotation != null) {
            int requiredRole = roleAnnotation.value();
            User user = userService.getCurrentUser();

            if (user == null) {
                throw new UnauthorizedException("Unauthorized access");
            }

            int currentUserRole = user.getRole();

            if (currentUserRole < requiredRole) {
                throw new UnauthorizedException("Unauthorized access");
            }
        }

        return true;
    }
}
