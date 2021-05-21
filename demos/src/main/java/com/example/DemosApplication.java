package com.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorEditDTO;
import com.example.domains.entities.dtos.ActorShortDTO;
import com.example.domains.services.ActorDomainService;
import com.example.domains.services.ActorDomainServiceImpl;
import com.example.infraestructure.repositories.ActorRepository;
import com.example.ioc.ComponenteImpl;
import com.example.ioc.Grafico;
import com.example.ioc.Servicio;
import com.example.ioc.ServicioImpl;


@SpringBootApplication
public class DemosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemosApplication.class, args);
	}
	
	@Autowired
	ActorRepository dao;
	
	@Autowired
	ActorDomainService srv;
	
	@Transactional
	public void run(String... args) throws Exception {
////		var actor = dao.findById(1);
////		if(actor.isPresent()) {
////			actor.get().setFirstName(actor.get().getFirstName().toUpperCase());
////			dao.save(actor.get());
//			var nuevo = new Actor(0, "12345678Z", " ");
//			if(nuevo.isValid())
//				srv.modify(nuevo);
//				//dao.save(nuevo);
//			else {
//				System.out.println(nuevo.getErroString());
//			}
//			srv.modify(nuevo);
//			if(nuevo.isValid())
//				dao.save(nuevo);
//			else {
//				System.out.println(nuevo.getErroString());
//			}
//		} else {
//			System.out.println("no encontrado");
//		}
		
//		dao.deleteById(237);
//		dao.findAll().forEach(item -> System.out.println(item));
//		dao.findByFirstNameStartingWithOrderByLastName("p").forEach(item -> System.out.println(item));
//		dao.findTop10ByActorIdBetween(1, 20).forEach(item -> System.out.println(item));
//		var actor = dao.getByFirstName("kk");
//		if(actor.isPresent()) {
//			System.out.println(actor.get());
//		} else {
//			System.out.println("no encontrado");
//		}
		// dao.laMia(5).forEach(item -> System.out.println(item));
//		var actor = dao.findById(1);
//		actor.get().getFilmActors().forEach(item -> System.out.println(item.getFilm()));
//		 System.out.println(ActorEditDTO.from(actor.get()));
//		miTransaccion();
//		dao.findAll().forEach(item -> System.out.println(item));
//		dao.findByActorIdIsNotNull(ActorEditDTO.class).forEach(item -> System.out.println(item));
//		dao.findAll().forEach(item -> System.out.println(item));
//		dao.findAll().forEach(item -> System.out.println(ActorEditDTO.from(item)));
//		dao.findByActorIdIsNotNull(ActorShortDTO.class).forEach(item -> System.out.println(item.getNombre()));

	}
	
	@Transactional
	void miTransaccion() {
		// begin trans
		try {
			var actor = dao.findById(1);
			if(actor.isPresent()) {
				actor.get().setFirstName(actor.get().getFirstName().toUpperCase());
				dao.save(actor.get());
				dao.save(new Actor(0, "Pepito", "Grillo"));
			} else {
				System.out.println("no encontrado");
			}
			
			dao.deleteById(237);
			// COMMIT
		} catch (Exception e) {
			// ROLLBACK
			throw e;
		}
//		var actor = dao.findById(1);
//		if(actor.isPresent()) {
//			actor.get().setFirstName(actor.get().getFirstName().toUpperCase());
//			dao.save(actor.get());
//			dao.save(new Actor(0, "Pepito", "Grillo"));
//		} else {
//			System.out.println("no encontrado");
//		}
//		
//		dao.deleteById(237);
		
	}
/*
//	@Autowired
//	ComponenteImpl comp;
	
	@Autowired
	@Qualifier("especial")
	Servicio srv;
	
	@Value("${server.port}")
	String puerto;
	
	@Autowired(required = false)
	Grafico grafico;
	
	@Override
	public void run(String... args) throws Exception {
//		comp.setName("Otro nombre");
//		System.out.println(comp.getName());
		System.out.println(srv.getComp().getName());
		System.out.println(puerto);
		if(grafico == null)
			System.out.println("no hay implementacion");
		else {
			grafico.pinta();
		}
		
	}
*/
}
