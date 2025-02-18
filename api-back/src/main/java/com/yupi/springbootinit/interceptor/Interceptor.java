package com.yupi.springbootinit.interceptor;

import com.yupi.springbootinit.common.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BaseContext.setCurrentId(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
