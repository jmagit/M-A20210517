package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	
	public void run(String... args) throws Exception {
		dao.findAll().forEach(item -> System.out.println(item));
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
