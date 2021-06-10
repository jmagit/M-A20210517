package com.example.application.resources;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.exceptions.core.BadRequestException;
import com.example.exceptions.core.InvalidDataException;
import com.example.exceptions.core.NotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials="true", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
//@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials="false")
public class ContactosResources {
	@Data @AllArgsConstructor @NoArgsConstructor
	public static class Contacto {
		@NotNull
		@Min(0)
		private int id;
		private String tratamiento;
		@NotBlank
		@Size(min = 2, max = 50)
		private String nombre;
		@Size(min = 2, max = 50)
		private String apellidos;
		@Size(max = 11)
		private String telefono;
		@Email
		private String email;
		@Pattern(regexp = "[HM]")
		private String sexo;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date nacimiento;
		@Size(max = 500)
		private String avatar;
		private boolean conflictivo = false;
	}
	private static List<Contacto> store = new ArrayList<>();
	static {
		try {
			store = (new ObjectMapper()).readValue(
					new URL("classpath:contactos.json"), 
					new TypeReference<List<Contacto>>() {});
			store.sort((a, b) -> a.getId() - b.getId());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Contacto> getAll() {
		return store;
	}

	@GetMapping(path = "/{id}")
	public Contacto getOne(@PathVariable int id) throws NotFoundException {
		var item = store.stream().filter(f -> f.getId() == id).findFirst();
		if (item.isEmpty())
			throw new NotFoundException();
		return item.get();
	}

	@PostMapping
	public ResponseEntity<Contacto> create(@Valid @RequestBody Contacto item)
			throws BadRequestException, InvalidDataException {
		int id = store.size() == 0 ? 1 : store.get(store.size() - 1).getId() + 1;
		item.setId(id);
		store.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody Contacto item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (item.getId() != id)
			throw new BadRequestException("Invalid identifier");
		var original = getOne(id);
		var index = store.indexOf(original);
		store.set(index, item);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws NotFoundException {
		store.remove(getOne(id));
	}
}
