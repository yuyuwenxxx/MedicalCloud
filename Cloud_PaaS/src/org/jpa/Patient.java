package org.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Patient entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "patient", catalog = "cloud")

public class Patient implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;

	// Constructors

	/** default constructor */
	public Patient() {
	}

	/** minimal constructor */
	public Patient(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Patient(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// Property accessors
	@Id

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}