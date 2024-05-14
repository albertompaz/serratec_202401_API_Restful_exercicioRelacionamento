package org.serratec.backend.h2banco.model;

import org.serratec.backend.h2banco.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Categoria {
	HATCH,SEDAN,PICAPE,SUV,CONVERSIVEL,MINIVAN;
	
	@JsonCreator
	public static Categoria verifica(String value) throws EnumValidationException {
		for (Categoria c : values()) {
			if (value.equals(c.name())) {
				return c;
			}
		}
		throw new EnumValidationException("Categoria preenchida incorretamente");
	}
}
