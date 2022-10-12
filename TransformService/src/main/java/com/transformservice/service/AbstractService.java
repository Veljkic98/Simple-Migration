package com.transformservice.service;

public interface AbstractService<T> {

    /**
     * Find and return entity if exists, otherwise throws exception.
     *
     * @param id    The id of entity
     * @return      The entity if exists
     */
    T getById(Long id);

    /**
     * Save entity.
     *
     * @param entity    the entity to save
     * @return          the saved entity
     */
    T create(T entity);

    /**
     * Update entity.
     *
     * @param entity    the entity to updated
     * @return          the updated entity
     */
    T update(T entity);

    /**
     * Delete entity.
     *
     * @param id    The id of entity
     */
    void deleteById(Long id);

}
