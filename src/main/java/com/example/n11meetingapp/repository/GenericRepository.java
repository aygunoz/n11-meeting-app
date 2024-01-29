package com.example.n11meetingapp.repository;

import com.example.n11meetingapp.entity.BaseEntity.GenericEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GenericRepository<E extends GenericEntity> extends JpaRepository<E,Long> {
    @Override
    Page<E> findAll(Pageable pageable);
}
