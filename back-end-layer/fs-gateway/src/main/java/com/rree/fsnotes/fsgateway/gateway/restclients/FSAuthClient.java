package com.rree.fsnotes.fsgateway.gateway.restclients;

import com.rree.fsnotes.fsgateway.model.AuthValidateTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "fs-auth", url = "http://localhost:8760/")
public interface FSAuthClient {
    @RequestMapping(method = RequestMethod.POST, value = "/auth/validateToken")
    String validateToken(AuthValidateTokenRequest token);
}
