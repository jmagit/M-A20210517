package com.example.application.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.entities.dtos.ActorEditDTO;
import com.example.domains.entities.dtos.ActorShortDTO;
import com.example.domains.services.ActorDomainService;
import com.example.exceptions.core.BadRequestException;
import com.example.exceptions.core.DuplicateKeyException;
import com.example.exceptions.core.InvalidDataException;
import com.example.exceptions.core.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/actores")
public class ActorResource {
	@Autowired
	private ActorDomainService srv;
	
	@GetMapping(params = {"mode=long"})
	public List<ActorEditDTO> getAll() {
//		return srv.getAll().stream().map(item -> ActorEditDTO.from(item)).collect(Collectors.toList());
		return srv.getByProjection(ActorEditDTO.class);
	}

	@GetMapping
	public Iterable<ActorShortDTO> getAllShort() {
		return srv.getByProjection(Sort.by("lastName", "firstName"), ActorShortDTO.class);
	}

	@GetMapping(params = {"page"})
	public Page<ActorShortDTO> getAll(Pageable pageable) {
//		return srv.getAll().stream().map(item -> ActorEditDTO.from(item)).collect(Collectors.toList());
		return srv.getByProjection(pageable, ActorShortDTO.class);
	}

	@GetMapping(path = "/{id}")
	public ActorEditDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
		return ActorEditDTO.from(item.get());
	}
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorEditDTO item) throws DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(ActorEditDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody ActorEditDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item == null || item.getActorId() != id)
			throw new BadRequestException("No coincide el identificador");
		srv.modify(ActorEditDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws NotFoundException {
		srv.deleteById(id);
	}
}
