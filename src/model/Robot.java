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
//		caracteristicas.Definir();
		comportamientoBasadoHardware();
	}
	private void comportamientoBasadoHardware() {
		switch(caracteristicas.Motor) {
			case BASICO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]-20;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]+20;
				comportamiento.esperar[1] = comportamiento.esperar[1]-20;
				comportamiento.esperar[2] = comportamiento.esperar[2]+20;
				comportamiento.observar[1] = comportamiento.observar[1]-20;
				comportamiento.observar[2] = comportamiento.observar[2]+20;
				break;
			case AVANZADO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]+20;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]-20;
				comportamiento.esperar[1] = comportamiento.esperar[1]+20;
				comportamiento.esperar[2] = comportamiento.esperar[2]-20;
				comportamiento.observar[1] = comportamiento.observar[1]+20;
				comportamiento.observar[2] = comportamiento.observar[2]-20;
				break;
			default:
				System.out.println("No Match");
				break;
		}
		switch(caracteristicas.Bateria) {
			case MEDIO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]+15;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]-15;
				comportamiento.esperar[1] = comportamiento.esperar[1]+15;
				comportamiento.esperar[2] = comportamiento.esperar[2]-15;
				comportamiento.observar[1] = comportamiento.observar[1]+15;
				comportamiento.observar[2] = comportamiento.observar[2]-15;
				break;
			case AVANZADO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]+30;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]-30;
				comportamiento.esperar[1] = comportamiento.esperar[1]+30;
				comportamiento.esperar[2] = comportamiento.esperar[2]-30;
				comportamiento.observar[1] = comportamiento.observar[1]+30;
				comportamiento.observar[2] = comportamiento.observar[2]-30;
				break;
			default:
				System.out.println("No Match");
				break;
		}
		switch(caracteristicas.Camara) {
			case BASICO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]+15;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]-15;
				comportamiento.esperar[1] = comportamiento.esperar[1]+15;
				comportamiento.esperar[2] = comportamiento.esperar[2]-15;
				comportamiento.observar[1] = comportamiento.observar[1]+15;
				comportamiento.observar[2] = comportamiento.observar[2]-15;
				break;
			case AVANZADO:
				comportamiento.avanzar[0] = comportamiento.avanzar[0]-30;
				comportamiento.avanzar[1] = comportamiento.avanzar[1]+30;
				comportamiento.esperar[1] = comportamiento.esperar[1]-30;
				comportamiento.esperar[2] = comportamiento.esperar[2]+30;
				comportamiento.observar[1] = comportamiento.observar[1]-30;
				comportamiento.observar[2] = comportamiento.observar[2]+30;
				break;
			default:
				System.out.println("No Match");
				break;
		}
		comportamiento.verificarProba();
		
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
