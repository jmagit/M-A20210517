package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.domains.core.DomainService;
import com.example.domains.entities.Actor;
import com.example.exceptions.core.DuplicateKeyException;
import com.example.exceptions.core.InvalidDataException;
import com.example.exceptions.core.NotFoundException;
import com.example.infraestructure.repositories.ActorRepository;
import org.springframework.data.domain.Sort;

@Service
public class ActorDomainServiceImpl implements ActorDomainService {
	@Autowired
	private ActorRepository dao;
	
	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}

	@Override
	public Iterable<Actor> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Actor> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		if(item == null || item.isNotValid())
			throw new InvalidDataException(item.getErroString());
		if(dao.findById(item.getActorId()).isPresent())
			throw new DuplicateKeyException();
		return dao.save(item);
	}

	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
		if(item == null || item.isNotValid())
			throw new InvalidDataException();
		if(dao.findById(item.getActorId()).isEmpty())
			throw new NotFoundException();
		return dao.save(item);
	}

	@Override
	public void delete(Actor item) throws NotFoundException {
		if(item == null || dao.findById(item.getActorId()).isEmpty())
			throw new NotFoundException();
		dao.delete(item);
	}

	@Override
	public void deleteById(Integer id) throws NotFoundException {
		if(dao.findById(id).isEmpty())
			throw new NotFoundException();
		dao.deleteById(id);
	}

}
