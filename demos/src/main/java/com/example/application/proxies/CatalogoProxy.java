package com.example.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.domains.entities.dtos.Pelis;

@FeignClient(name = "CATALOGO-SERVICE") 
public interface CatalogoProxy {
	@GetMapping
	String getRaiz();
	
	@GetMapping(path = "/peliculas?mode=short")
	List<Pelis> getPelis();
	
	@GetMapping(path = "/peliculas/{id}?mode=short")
	Pelis getPeli(@PathVariable() int id);
}
