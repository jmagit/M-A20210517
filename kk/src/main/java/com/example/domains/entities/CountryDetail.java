package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the country_details database table.
 * 
 */
@Entity
@Table(name="country_details")
@NamedQuery(name="CountryDetail.findAll", query="SELECT c FROM CountryDetail c")
public class CountryDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id")
	private short countryId;

	private short poblacion;

	public CountryDetail() {
	}

	public short getCountryId() {
		return this.countryId;
	}

	public void setCountryId(short countryId) {
		this.countryId = countryId;
	}

	public short getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(short poblacion) {
		this.poblacion = poblacion;
	}

}