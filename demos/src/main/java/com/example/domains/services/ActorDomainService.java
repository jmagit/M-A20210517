package com.example.domains.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.core.DomainService;
import com.example.domains.entities.Actor;

public interface ActorDomainService extends DomainService<Actor, Integer> {
	<T> List<T> getByProjection(Class<T> type);
	<T> Iterable<T> getByProjection(Sort sort, Class<T> type);
	<T> Page<T> getByProjection(Pageable pageable, Class<T> type);
}
