package com.learncinch.service;

import java.io.Serializable;
import java.util.List;

/**
 * Base Service to be implemented by all service
 */
public interface BaseService<T, ID extends Serializable> {

	/**
	 * Find one.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */
	T findOne(ID id);

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	List<T> findAll();

	/**
	 * Save.
	 *
	 * @param entity            the entity
	 * @return the t
	 */
	T save(final T entity);
	
	/**
	 * Save all: saves a list.
	 *
	 * @param aoEntities the ao entities
	 * @return the list
	 */
	List<T>  saveAll(final List<T> aoEntities);

	/**
	 * Delete.
	 * 
	 * @param entity
	 *            the entity
	 */
	void delete(final T entity);

	/**
	 * Delete by id.
	 * 
	 * @param entityId
	 *            the entity id
	 */
	void deleteById(final ID entityId);

}

