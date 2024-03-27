package hu.spring.feladat.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class DefaultController {


    @Operation(summary = "Swagger documentation url", hidden = true)
    @GetMapping("/")
    ResponseEntity<Void> redirect() {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("swagger-ui/index.html"))
                .build();
    }

}
