package com.example.ioc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("normal")
public class ServicioImpl implements Servicio, Grafico {
	@Autowired
	private ComponenteImpl comp;

	@Override
	public ComponenteImpl getComp() {
		return comp;
	}
	
	@PostConstruct
	void ponNombre() {
		comp.setName("version normal");
	}

	@Override
	public void pinta() {
		System.out.println("Ya estoy implementado");
		
	}
}
