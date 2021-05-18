package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("test")
public class ServicioMockImpl implements Servicio {
	private ComponenteImpl comp;

	public ServicioMockImpl(ComponenteImpl comp) {
		super();
		this.comp = comp;
		comp.setName("es el doble de prueba");
	}

	@Override
	public ComponenteImpl getComp() {
		return comp;
	}
	
}