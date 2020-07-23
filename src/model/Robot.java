package model;

import java.util.stream.Stream;

public class Robot implements Genetico {
	private Comportamiento comportamiento;
	private Caracteristicas caracteristicas;
	public Robot() {
		comportamiento = new Comportamiento();
		caracteristicas = new Caracteristicas();
	}
	@Override
	public void Definir() {
		Stream.of(comportamiento,
				  caracteristicas)
				.forEach((R)-> R.Definir());
	}
	@Override
	public void Cruce(Object obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Mutar() {
		Stream.of(comportamiento,
				  caracteristicas)
				.forEach((R)-> R.Mutar());
		
	}
	
	
	
}
