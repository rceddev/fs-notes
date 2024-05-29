package com.rree.fsnotes.auth.restclients;

import com.rree.fsnotes.auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "fs-persistance", url = "http://localhost:8768/secure")
public interface FSPersistanceClient {
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    User registUser(User userToRegister);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{email}")
    User getUserByEmail(@PathVariable("email") String email);

}
