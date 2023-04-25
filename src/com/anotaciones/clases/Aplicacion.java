package com.anotaciones.clases;

import java.time.LocalDate;
import java.time.Month;

public class Aplicacion {

	public static void main(String[] args) {
		Usuario usuario = new Usuario("Daniel", "10472182963", LocalDate.of(1987, Month.APRIL, 28));
		System.out.println("Nombre :"+usuario.getNombre());
		System.out.println("Id :"+usuario.getIdentidad());
		System.out.println("Fecha :"+usuario.getFechaNacimiento());
		
		
	}
}
