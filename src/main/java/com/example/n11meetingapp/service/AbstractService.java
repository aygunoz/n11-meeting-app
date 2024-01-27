package com.example.n11meetingapp.service;

import com.example.n11meetingapp.common.Mapper;
import com.example.n11meetingapp.dto.GenericDTO;
import com.example.n11meetingapp.entity.GenericEntity;
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


    public R create(Q requestDTO) throws Exception {
        E createdEntity = (E) mapper.convertToType(requestDTO, getSourceTypeEntity());
        return (R) mapper.convertToType(repository.save(createdEntity), getSourceTypeResponse());
    }

    @Override
    public R update(Q requestDTO) throws Exception {
        E createdEntity = (E) mapper.convertToType(requestDTO, getSourceTypeEntity());
        return (R) mapper.convertToType(repository.save(createdEntity), getSourceTypeResponse());
    }

    @Override
    public void deleteById(E entity) throws Exception {
         repository.deleteById(entity.getId());
    }

    @Override
    public Optional<R> findById(E entity) throws Exception {
        return Optional.ofNullable((R) mapper.convertToType(repository.findById(entity.getId()), getSourceTypeResponse()));
    }

    @Override
    public Page<R> findAll(Pageable pageable){
        return null;
    }

    @Override
    public R newEntity() throws Exception {
        E entity = getSourceTypeEntity().getDeclaredConstructor().newInstance();
        return null;
    }
    @SuppressWarnings("unchecked")
    private Class<E> getSourceTypeEntity() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<E>) typeArgs[0];
    }

    @SuppressWarnings("unchecked")
    private Class<Q> getSourceTypeRequest() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<Q>) typeArgs[1];
    }

    @SuppressWarnings("unchecked")
    private Class<R> getSourceTypeResponse() {
        Class<?>[] typeArgs = GenericTypeResolver.resolveTypeArguments(getClass(), GenericService.class);
        assert typeArgs != null;
        return (Class<R>) typeArgs[2];
    }
}
