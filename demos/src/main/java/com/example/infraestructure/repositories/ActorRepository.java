package com.example.infraestructure.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findByFirstNameStartingWithOrderByLastName(String prefijo);
	List<Actor> findTop10ByActorIdBetween(int ini, int fin);
	
	Optional<Actor> getByFirstName(String nombre);
	
	@Query("FROM Actor a WHERE a.actorId < ?1")
	List<Actor> laMia(int fin);
	
	<T> List<T> findByActorIdIsNotNull(Class<T> type);
}
