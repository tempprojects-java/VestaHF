package com.gmail.gak.artem.backend.service;

import com.gmail.gak.artem.backend.data.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

public interface CrudService<T extends AbstractEntity> {
    JpaRepository<T, Integer> getRepository();

    @Transactional
    default T save(T entity) {
        return getRepository().saveAndFlush(entity);
    }

    @Transactional
    default void delete(T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        getRepository().delete(entity);
    }

    @Transactional
    default void delete(int id) {
        delete(load(id));
    }

    default int count() {
        return (int) getRepository().count();
    }

    default T load(int id) {
        T entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    T createNew();
}