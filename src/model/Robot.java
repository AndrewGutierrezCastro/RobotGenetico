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
		caracteristicas.Definir();
		comportamientoBasadoHardware();
		System.out.println("********************************");
		System.out.println(this.caracteristicas.Bateria);
		System.out.println(this.caracteristicas.Camara);
		System.out.println(this.caracteristicas.Motor);
		System.out.println(this.comportamiento.avanzar[0]);
		System.out.println(this.comportamiento.avanzar[1]);
		System.out.println(this.comportamiento.avanzar[2]);
		System.out.println(this.comportamiento.esperar[0]);
		System.out.println(this.comportamiento.esperar[1]);
		System.out.println(this.comportamiento.esperar[2]);
		System.out.println(this.comportamiento.observar[0]);
		System.out.println(this.comportamiento.observar[1]);
		System.out.println(this.comportamiento.observar[2]);
		System.out.println("********************************");
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
			case MEDIO:
				System.out.println("No hay cambios");
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
			case BASICO:
				System.out.println("No hay cambios");
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
			case MEDIO:
				System.out.println("No hay cambios");
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
