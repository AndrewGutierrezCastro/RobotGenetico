package model;

import java.util.Random;

public class Terreno {
	public Bloque[][] terreno;
	public final int ancho = 20, alto = 20;
	public final long seed = 13853511;
	
	public Terreno() {
		terreno =  new Bloque[ancho][alto];
		Random rand = new Random();
		rand.setSeed(seed);
		Bloque[] bloques = Bloque.values();
		
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {			
				terreno[i][j] = bloques[rand.nextInt(4)];
			}
		}
	}
	
	
}
