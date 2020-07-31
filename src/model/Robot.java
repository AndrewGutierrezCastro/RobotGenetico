package model;

import java.util.Random;
import java.util.stream.Stream;

import javax.swing.JLabel;

public class Robot implements Genetico {
	private Comportamiento comportamiento;
	private Caracteristicas caracteristicas;
	public int posicionX, posicionY;
	public JLabel lblRobot;
	Random rand = new Random();
	public Robot() {
		comportamiento = new Comportamiento();
		caracteristicas = new Caracteristicas();
		this.posicionX = 19;
		this.posicionY = 0;
	}
	
	private void Comportarse() {
		
		int valorMaximo = 100,
			valorMinimo = 0,
			numeroRandom = rand.nextInt(100);
		int[] arrayComportamientoAcutal= new int[3];
		switch (comportamiento.estado) {
		case AVANZANDO:
			Avanzar();
			arrayComportamientoAcutal = comportamiento.avanzar;
			break;
		case ESPERANDO:
			Esperar();
			arrayComportamientoAcutal = comportamiento.esperar;
			break;
		case OBSERVANDO:
			Observar();
			arrayComportamientoAcutal = comportamiento.observar;
			break;
		default:
			break;
		}
		valorMaximo = Math.max(arrayComportamientoAcutal[0]
				,Math.max(arrayComportamientoAcutal[1]
						,arrayComportamientoAcutal[2]));
		valorMinimo = Math.min(arrayComportamientoAcutal[0]
						,Math.min(arrayComportamientoAcutal[1]
								,arrayComportamientoAcutal[2]));
		if(numeroRandom > valorMaximo) {
			comportamiento.getNextComportamiento(valorMaximo, comportamiento.avanzar);
		}else if(numeroRandom < valorMinimo) {
			comportamiento.getNextComportamiento(valorMinimo, comportamiento.avanzar);
		}else {
			int valorMedio = 100 -(valorMaximo + valorMinimo);
			comportamiento.getNextComportamiento(valorMedio, comportamiento.avanzar);
		}
			}
	
	private void Observar() {
		// TODO Auto-generated method stub
		
	}

	private void Esperar() {
		// TODO Auto-generated method stub
		
	}

	private void Avanzar() {
		// TODO Auto-generated method stub
		
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
			case BASICO:
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
			case MEDIO:
				break;
		}
		comportamiento.verificarProba();
		
	}

	@Override	
	public void Definir() {
		caracteristicas.Definir();
		comportamientoBasadoHardware();
		lblRobot = new JLabel();
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
