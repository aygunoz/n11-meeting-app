package com.example.n11meetingapp.service;

import com.example.n11meetingapp.common.Mapper;
import com.example.n11meetingapp.dto.GenericDTO;
import com.example.n11meetingapp.entity.BaseEntity.GenericEntity;
import com.example.n11meetingapp.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class AbstractService<E extends GenericEntity, Q extends GenericDTO, R extends GenericDTO> implements GenericService<E, Q, R> {

    @Autowired
    protected GenericRepository<E> repository;
    Mapper mapper = new Mapper();


    public E create(Q requestDTO) throws Exception {
        return repository.saveAndFlush((E) mapper.convertToType(requestDTO, getSourceTypeEntity()));
    }

    @Override
    public E update(Q requestDTO) throws Exception {
        //        return (R) mapper.convertToType(repository.save(createdEntity), getSourceTypeResponse());
        return repository.saveAndFlush((E) mapper.convertToType(requestDTO, getSourceTypeEntity()));
    }

    @Override
    public void deleteById(E entity) throws Exception {
         repository.deleteById(entity.getId());
    }

    @Override
    public Optional<E> findById(E entity) throws Exception {
//        return Optional.ofNullable((R) mapper.convertToType(repository.findById(entity.getId()), getSourceTypeResponse()));
        return repository.findById(entity.getId());
    }

    @Override
    public E newEntity() throws Exception {
        E entity = getSourceTypeEntity().getDeclaredConstructor().newInstance();
        return null;
    }
    @SuppressWarnings("unchecked")
    public Class<E> getSourceTypeEntity() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<E>) typeArgs[0];
    }

    @SuppressWarnings("unchecked")
    public Class<Q> getSourceTypeRequest() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<Q>) typeArgs[1];
    }

    @SuppressWarnings("unchecked")
    public Class<R> getSourceTypeResponse() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<R>) typeArgs[2];
    }
}
