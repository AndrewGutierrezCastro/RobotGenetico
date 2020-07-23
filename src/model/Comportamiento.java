package model;

import java.util.stream.Stream;

public class Comportamiento implements Genetico {
	private int[] avanzar;
	private int[] esperar;
	private int[] observar;
	private Estado estado;
	private static int numComportamientos = 3;
	
	public Comportamiento() {
		Stream.of(avanzar,
				esperar,
				observar)
		.forEach((c) -> c = new int[numComportamientos]);
		estado = Estado.ESPERANDO;
		Definir();
	}
	
	public void Definir() {
		//TODO generar las probabilidades de markov para los atributos
	}
	public void Cruce(Object obj) {
		//TODO hacer el cruce de comportamiento con otro comportamiento
		Comportamiento comportamiento = (Comportamiento) obj;
	}
	public void Mutar() {
		//TODO llamar este metodo luego de la creacion para mutar
	}
}
