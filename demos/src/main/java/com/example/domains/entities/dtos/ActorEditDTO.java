package com.example.domains.entities.dtos;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorEditDTO {
	private int actorId;
	private String firstName;
	private String lastName;

	public static ActorEditDTO from(Actor item) {
		return new ActorEditDTO(
				item.getActorId(),
				item.getFirstName(),
				item.getLastName());
	}
	public static Actor from(ActorEditDTO item) {
		return new Actor(
				item.getActorId(),
				item.getFirstName(),
				item.getLastName());
	}
}
