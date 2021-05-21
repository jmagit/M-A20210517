package com.example.application.resources;

import org.springframework.http.MediaType;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

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

}
