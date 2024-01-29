package com.example.n11meetingapp.service;

import com.example.n11meetingapp.dto.GenericDTO;
import com.example.n11meetingapp.entity.BaseEntity.GenericEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface GenericService<E extends GenericEntity, Q extends GenericDTO, R extends GenericDTO> {


    E update(Q responseDTO) throws Exception;


    void deleteById(E entity) throws Exception;

    E create(Q responseDTO) throws  Exception;

    Optional<E> findById (E entity) throws Exception;

    E newEntity() throws Exception;
}
