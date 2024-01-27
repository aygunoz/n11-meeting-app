package com.example.n11meetingapp.controller;


import com.example.n11meetingapp.dto.GenericDTO;
import com.example.n11meetingapp.entity.GenericEntity;
import com.example.n11meetingapp.service.AbstractService;
import com.example.n11meetingapp.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public abstract class AbstractController<E extends GenericEntity, Q extends GenericDTO, R extends GenericDTO> {

    @Autowired protected GenericService<E,Q,R> service;
    @Autowired protected AbstractService<E,Q,R> abstractService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Q requestDto) throws Exception {
        return new ResponseEntity<>(service.create(requestDto), HttpStatus.CREATED);
    }

    @PostMapping("/create2")
    public ResponseEntity<?> create2(@RequestBody Q requestDto) throws Exception {
        return new ResponseEntity<>(service.create(requestDto), HttpStatus.CREATED);
    }

}
