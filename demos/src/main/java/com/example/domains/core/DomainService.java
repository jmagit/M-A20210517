package com.example.domains.core;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.exceptions.core.DuplicateKeyException;
import com.example.exceptions.core.InvalidDataException;
import com.example.exceptions.core.NotFoundException;
import org.springframework.data.domain.Sort;

public interface DomainService<T, K> {
	List<T> getAll();
	Iterable<T> getAll(Sort sort);
	Page<T> getAll(Pageable pageable);
	
	Optional<T> getOne(K id);
	
	T add(T item) throws DuplicateKeyException, InvalidDataException;
	T modify(T item) throws NotFoundException, InvalidDataException;
	void delete(T item) throws NotFoundException;
	void deleteById(K id) throws NotFoundException;
	
}
