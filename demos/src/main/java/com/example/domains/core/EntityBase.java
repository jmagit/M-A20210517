package com.example.domains.core;

import java.util.Set;

import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class EntityBase {
	@Autowired
	private Validator validator;
	
	@JsonIgnore
	protected Validator getValidator() {
		if(validator == null)
			validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator;
	}
	
	@SuppressWarnings("unchecked")
	@JsonIgnore
	@Transient
	public <T extends EntityBase> Set<ConstraintViolation<T>> getErrors() {
		return getValidator().validate((T)this);
	}
	
	@JsonIgnore
	@Transient
	public boolean isValid() {
		return getErrors().size() == 0;
	}
	
	@JsonIgnore
	@Transient
	public boolean isNotValid() {
		return !isValid();
	}
	
	@JsonIgnore
	@Transient
	public String getErroString() {
		Set<ConstraintViolation<EntityBase>> lst = getErrors();
		if(lst.isEmpty()) return "";
		StringBuilder sb = new StringBuilder("ERRORES:");
		lst.forEach(item -> sb.append(" " + item.getPropertyPath() + ": " + item.getMessage() + "."));
		return sb.toString();
	}
}
