package com.example.enetrevfinal.configuration;

import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomHttpFirewall implements HttpFirewall {

    private final Set<String> allowedHttpMethods = new HashSet<>(
            Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));
    @Override
    public FirewalledRequest getFirewalledRequest(HttpServletRequest request) throws RequestRejectedException {
        if (containsBlockedCharacters(request)) {
            throw new RequestRejectedException("The request was rejected because the URL contained a potentially malicious String");
        }
        return new FirewalledRequest(request) {
            @Override
            public void reset() {

            }
        };
    }

    @Override
    public HttpServletResponse getFirewalledResponse(HttpServletResponse response) {
        return response;
    }

    private boolean containsBlockedCharacters(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        if (StringUtils.hasLength(requestUri)) {
            int idx = requestUri.indexOf('?');
            if (idx > -1) {
                requestUri = requestUri.substring(0, idx);
            }
            return requestUri.contains("\n") || requestUri.contains("\r") || requestUri.contains("\0")
                    || requestUri.contains("%00");
        }
        return false;
    }

    public boolean isHttpMethodAllowed(String method) {
        return allowedHttpMethods.contains(method);
    }

    public void setAllowedHttpMethods(Set<String> allowedHttpMethods) {
        this.allowedHttpMethods.clear();
        this.allowedHttpMethods.addAll(allowedHttpMethods);
    }

    public Set<String> getAllowedHttpMethods() {
        return new HashSet<>(allowedHttpMethods);
    }
}