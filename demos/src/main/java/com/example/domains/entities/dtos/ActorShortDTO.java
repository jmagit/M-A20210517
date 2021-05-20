package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ActorShortDTO {
	@JsonProperty("id")
	int getActorId();
	@Value("#{target.lastName + ', ' + target.firstName}")
	String getNombre();
}
