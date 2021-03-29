package com.nace.controller;

import com.nace.model.NaceEntry;
import com.nace.model.NaceResponse;
import com.nace.service.NaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/nace")
public class NaceController {

    @Autowired
    NaceService service;

    @PostMapping(path="/entry",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<NaceResponse> createNaceEntry(@Validated @RequestBody NaceEntry naceEntry){
        NaceResponse response = service.createNaceEntry(naceEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/entry/{orderId}")
    public ResponseEntity<NaceResponse> getNaceEntry(@PathVariable int orderId){
         NaceResponse naceResponse = service.getNaceEntry(orderId);
         return Optional.ofNullable(naceResponse.getNaceEntries())
                .map(msg-> ResponseEntity.status(HttpStatus.OK).body(naceResponse))
                 .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(naceResponse));

    }

    @GetMapping("/entry")
    public ResponseEntity<NaceResponse> getNaceAllEntries(){
        NaceResponse naceResponse = service.getNaceAllEntries();
        return Optional.ofNullable(naceResponse.getNaceEntries())
                .map(msg-> ResponseEntity.status(HttpStatus.OK).body(naceResponse))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(naceResponse));

    }
}
