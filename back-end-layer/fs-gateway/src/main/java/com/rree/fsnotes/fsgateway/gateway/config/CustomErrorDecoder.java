package com.rree.fsnotes.fsgateway.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.ServerErrorException;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body body = response.body();
        HttpStatus statusCode = HttpStatus.valueOf(response.status());

        String message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, String.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 404 -> new NotFoundException(message);
            case 401 -> new NotAllowedException(message);
            case 403 -> new ForbiddenException(message);
            default -> new ServerErrorException(message, response.status());
        };
    }
}
