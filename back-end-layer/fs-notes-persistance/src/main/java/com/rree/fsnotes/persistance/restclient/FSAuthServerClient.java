package com.rree.fsnotes.persistance.restclient;

import com.rree.fsnotes.persistance.model.AuthValidateTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "fs-gateway", url = "http://localhost:8766/")
public interface FSAuthServerClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth/validateToken")
    String validateToken(@RequestBody AuthValidateTokenRequest validateTokenRequest);
}
