package com.learncinch.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
/**
 * The Base entity to be extended by all entities
 * @author Imran Khan
 *
 */
@MappedSuperclass
public class Base implements Serializable {

	private static final long serialVersionUID = -1051797122761479444L;

}
