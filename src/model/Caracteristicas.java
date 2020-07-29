package model;
import java.util.Random;

public class Caracteristicas implements Genetico {
	public Hardware Bateria;
	public Hardware Camara;
	public Hardware Motor;
	
	public Caracteristicas() {
		Definir();
	}

	@Override
	public void Cruce(Object obj) {
		Caracteristicas caracteristicas = (Caracteristicas) obj;
	}
	@Override
	public void Mutar() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void Definir() {
		Bateria = Hardware.MEDIO;
		Camara = Hardware.MEDIO;
		Motor = Hardware.MEDIO;
		Random rand = new Random();
		int numBat = rand.nextInt((2 - 0) + 1) + 0;
		if(numBat<1)
			Bateria = Hardware.BASICO;
		else if(numBat==1)
			Bateria = Hardware.AVANZADO;
		
		int numCam = rand.nextInt((2 - 0) + 1) + 0;
		if(numCam<1)
			Camara = Hardware.BASICO;
		else if(numCam==1)
			Camara = Hardware.AVANZADO;
		
		int numMot = rand.nextInt((2 - 0) + 1) + 0;
		if(numMot<1)
			Motor = Hardware.BASICO;
		else if(numMot==1)
			Motor = Hardware.AVANZADO;
	}
	
}
