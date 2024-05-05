package com.example.leader;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor :preHandle");
        String uri = request.getRequestURI();

        String[] uriParts = uri.split("/");

        for (String part : uriParts) {
            if (part.startsWith("actionA")) {
                String tipeAction = part.substring("action".length());
                // Do something with the tipeAction
                System.out.println("Found tipeAction: " + tipeAction);

                break;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor :postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor :afterCompletion");
    }
}
