/**
 * 
 */
package com.learncinch.serviceImpl;

import java.util.List;

import com.learncinch.dao.GenericRepository;


/**
 * 
 * @author Mandeep
 *
 * @param <T>
 */
public abstract class ServiceBaseImpl<T> {

	// @Autowired
	// GlobalService globalService;

	GenericRepository<T, Long> genericRepository;

	/**
	 * Find one.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 */

	public T findOne(Long id) {
		return genericRepository.findOne(id);
	}

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public List<T> findAll() {
		return genericRepository.findAll();
	}

	/**
	 * Save.
	 * 
	 * @param entity
	 *            the entity
	 */
	public T save(T entity) {
		return genericRepository.saveAndFlush(entity);
	}

	/**
	 * Save all: save a list
	 *
	 * @param aoEntities
	 *            the ao entities
	 */
	public List<T> saveAll(List<T> aoEntities) {
		return genericRepository.save(aoEntities);
	}

	/**
	 * Delete.
	 * 
	 * @param entity
	 *            the entity
	 */
	public void delete(T entity) {
		genericRepository.delete(entity);

	}

	/**
	 * Delete by id.
	 * 
	 * @param entityId
	 *            the entity id
	 */
	public void deleteById(Long entityId) {
		genericRepository.delete(entityId);
	}

	}
