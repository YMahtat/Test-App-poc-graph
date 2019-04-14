package com.skafandria.pocgraphUITA.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class PocProperties {

    @Value("${poc-test.geckodriver}")
    private String geckodriverPath;

    @Value("${auth.username}")
    private String usernameAuth;

    @Value("${auth.password}")
    private String passwordAuth;

    @Value("${poc-test.base-url}")
    private String baseUrl;

    public String getGeckodriverPath() {
        return geckodriverPath;
    }

    public String getUsernameAuth() {
        return usernameAuth;
    }

    public String getPasswordAuth() {
        return passwordAuth;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
