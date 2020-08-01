package model;

import java.util.Random;
import java.util.stream.Stream;

import javax.swing.JLabel;

public class Robot implements Genetico {
	private Comportamiento comportamiento;
	private Caracteristicas caracteristicas;
	public int posicionX, posicionY;
	public JLabel lblRobot;
	public Random rand;
	public Robot() {
		comportamiento = new Comportamiento();
		caracteristicas = new Caracteristicas();
		this.posicionX = 19;
		this.posicionY = 0;
		this.rand=new Random();
	}
	
	private void Comportarse() {
		
		int valorMaximo = 100,
			valorMinimo = 0,
			numeroRandom = rand.nextInt(100);
		int[] arrayComportamientoAcutal= new int[2];
		switch (comportamiento.estado) {
		case AVANZANDO:
			Avanzar();
			arrayComportamientoAcutal = comportamiento.avanzar;
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


	private void Avanzar() {
		// TODO Auto-generated method stub
		
	}


	@Override	
	public void Definir() {
		caracteristicas.Definir();
		comportamiento.Definir();
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
