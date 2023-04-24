package com.anotaciones.clases;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

import com.anotaciones.anotacion.EdadMinima;

public class Usuario {
	
	private String nombre;
	private String identidad;
	
	@EdadMinima(valor=18)
	private LocalDate fechaNacimiento;
	
	public Usuario(String nom, String id, LocalDate fecha) {
		this.nombre = nom;
		this.identidad = id;
		this.fechaNacimiento = fecha;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getIdentidad() {
		return identidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public boolean usuarioValido(Usuario usuario){
		   if(!usuario.getNombre().matches("[a-zA-Záàâãéèêíïóôõöúçñ\\s]+")){
		      return false;
		   }
		   if(!usuario.getIdentidad().matches("[^0-9]+")){
		      return false;
		   }
		   return Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears() >= 18;
	}
	
	public static void main(String[] args) {
		Usuario usuario = new Usuario("Daniel", "10472182963", LocalDate.of(2009, Month.APRIL, 28));
		System.out.println(validador(usuario));
	}
	
	public static <T> boolean validador(T objeto) {
		Class<?> clase = objeto.getClass();
		for (Field field : clase.getDeclaredFields()) {
			if (field.isAnnotationPresent(EdadMinima.class)) {
				EdadMinima edadMinima = field.getAnnotation(EdadMinima.class);
		        try{
		            field.setAccessible(true);
		            LocalDate fechaNacimiento = (LocalDate) field.get(objeto);
		            return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= edadMinima.valor() ;
		            				
		         } catch (IllegalAccessException e) {
		              e.printStackTrace();
		         }
		    }
		}
		
		return false;
		
	}

}
