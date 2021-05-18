package com.example.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ComponenteImpl {
	int id;
	String name;
	
	public ComponenteImpl() {
		id = 0;
		name = "por defecto";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ComponenteImpl [id=" + id + ", name=" + name + "]";
	}
	
	
}
