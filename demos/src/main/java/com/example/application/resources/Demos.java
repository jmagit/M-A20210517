package com.example.application.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.application.proxies.CatalogoProxy;
import com.example.domains.entities.dtos.Pelis;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Value;

@RestController
public class Demos {
	@Value
	static class Respuesta {
		@NotNull
		private String tipo;
		private String msg;
	}
	@GetMapping("/saluda")
	public String saluda(@RequestParam(defaultValue = "mundo") String nom) {
		return "hola " + nom;
	}

	@GetMapping(path = "/saluda", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Respuesta saludaXML(@RequestParam(defaultValue = "mundo") String nom) {
		return new Respuesta("saluda", nom);
	}

	@GetMapping(path = "/despide")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Respuesta despide(@RequestParam(defaultValue = "mundo") String nom) {
		return new Respuesta("despide", nom);
	}
	
	@PostMapping("/saluda")
	public String saludaPost(@Valid @RequestBody Respuesta body) {
		return "hola " + body.getMsg();
	}

	@GetMapping("/params/{id}")
	public String cotilla(
	        @PathVariable String id,
	        @RequestParam String nom,
	        @RequestHeader("Accept-Language") String language, 
	        @CookieValue(value = "JSESSIONID", required = false) String cookie) { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("id: " + id + "\n");
	    sb.append("nom: " + nom + "\n");
	    sb.append("language: " + language + "\n");
	    sb.append("cookie: " + cookie + "\n");
	    return sb.toString();
	}

	@Autowired
	RestTemplate rest;
	
	@Autowired
	CatalogoProxy proxy;
	
	@GetMapping("/cliente/raiz")
	public String clienteRaiz() { 
	    //return rest.getForObject("http://localhost:8010/", String.class);
	    return proxy.getRaiz();
	}
	
	@GetMapping("/cliente/pelis")
	public List<Pelis> clientePelis() { 
//		ResponseEntity<List<Pelis>> response = rest.exchange("http://localhost:8010/peliculas?mode=short", 
//				HttpMethod.GET,
//				HttpEntity.EMPTY, 
//				new ParameterizedTypeReference<List<Pelis>>() {
//				});
//
//	    return response.getBody();
	    return proxy.getPelis();
	}
	@GetMapping("/cliente/pelis/{id}")
	public Pelis clientePelis(@PathVariable int id) { 
	    return proxy.getPeli(id);
	}
	
}
