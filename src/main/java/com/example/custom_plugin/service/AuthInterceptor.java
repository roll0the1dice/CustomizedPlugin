package com.example.custom_plugin.service;

import com.example.custom_plugin.model.atest.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
@Aspect
@Component
public class AuthInterceptor {
    /** This is an example service. */
    @Autowired
    private UsersServiceImpl userService;

    /**
    * UseCase: 
    * @WithAuth(mustRole = UserRoleEnum.USER) 
    * public String getMethodName(@RequestParam String param) {
    *        return new String();
    * }
    */
    @Around("@annotation(withAuth)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, WithAuth withAuth) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //if (userService.getCurrent(request) == null)
        //throw new BadRequestException("User session not found");
        UserRoleEnum mustRoleEnum = withAuth.mustRole();
        try {
            if (mustRoleEnum == null) {
                return joinPoint.proceed();
            }
        }catch (Throwable e) {
            throw new RuntimeException("Internal Server Error");
        }
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;
        //userRoleEnum = users.getUserRole();
        if (userRoleEnum == null) {
            throw new BadRequestException("Permission denied");
        }
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BadRequestException("Permission denied");
        }
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BadRequestException("Permission denied");
            }
        }
        try {
            return joinPoint.proceed();
        }catch (Throwable e) {
            throw new RuntimeException("Internal Server Error");
        }
    }
}