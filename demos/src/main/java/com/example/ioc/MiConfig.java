package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiConfig {
	@Bean
	@Qualifier("especial")
	public Servicio getEspecial(ComponenteImpl c) {
		Servicio rslt = new ServicioMockImpl(c);
		c.setName("especial");
		return rslt;
	}
}
