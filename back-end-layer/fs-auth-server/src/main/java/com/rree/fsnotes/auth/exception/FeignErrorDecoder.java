package com.rree.fsnotes.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.ServerErrorException;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        Response.Body body = response.body();
        HttpStatus statusCode = HttpStatus.valueOf(response.status());

        PersistanceErrorBody message = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, PersistanceErrorBody.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case 404 -> new CustomExceptionHandler.EmailNotFoundException(message.message);
            case 401 -> new NotAllowedException(message.message);
            case 403 -> new ForbiddenException(message.message);
            default -> new ServerErrorException(message.message, response.status());
        };
    }

    @Data
    private static class PersistanceErrorBody{
        public String localDateTime;
        public String message;
        public String details;
    }
}
