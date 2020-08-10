package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;

public class Terreno implements Runnable{
	public Bloque[][] terreno;
	private JLabel[][] lblMatriz;
	private JLabel[][] lblTerreno;
	private long tiempoEspera = 6;
	public Thread HiloTerreno;
	public final int ancho = 20, alto = 20;
	public final long seed = 13853511;
	public Terreno() {
		terreno =  new Bloque[ancho][alto];
		//GenerarTerrenoRandom();
		LeerNivelTxt();
		HiloTerreno = new Thread(this);
		tiempoEspera = Poblacion.getInstance().tiempoEspera;
		tiempoEspera = (tiempoEspera/2);
	}
	
	private void GenerarTerrenoRandom() {
		Random rand = new Random();
		rand.setSeed(seed);
		Bloque[] bloques = Bloque.values();
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {			
				terreno[i][j] = bloques[rand.nextInt(4)];
			}
		}
	}
	
	private void LeerNivelTxt() {
		String path = System.getProperty("user.dir")+"/src/model/Terreno.txt";
		File archTerr = new File(path);
		try {
			Scanner revisador = new Scanner(archTerr);
			int numeroFila = 0;
			Bloque seccion;
			while(revisador.hasNextLine()) {
				String filaTerr = revisador.nextLine();
				String[] filaSeparada = filaTerr.split(",");
				for(int i = 0; i < filaSeparada.length; i++) {
					switch (filaSeparada[i]) {
					case "0":
						seccion = Bloque.NORMAL;
						break;
					case "1":
						seccion = Bloque.MODERADO;
						break;
					case "2":
						seccion = Bloque.DIFICIL;
						break;
					case "3":
						seccion = Bloque.BLOQUEADO;
						break;
					default:
						seccion = Bloque.NORMAL;
						break;
					}
					terreno[numeroFila][i] = seccion;
				}
				numeroFila++;
			}
			revisador.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Archivo no encontrado");
		}
	}
	
	private void ImprimirTerrenoConsola() {
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				System.out.print(terreno[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	public void run() {
		while(HiloTerreno.isAlive()) {
			MostrarTerreno();
			try {
				HiloTerreno.sleep(this.tiempoEspera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void MostrarTerreno() {
		for (int i = 0; i < lblTerreno.length ; i++) {
			for (int j = 0; j < lblTerreno[0].length; j++) {	
				lblMatriz[i][j].setIcon(lblTerreno[i][j].getIcon());
			}
		}
	}
	
	public JLabel[][] getLblMatriz() {
		return lblMatriz;
	}

	public JLabel[][] getLblTerreno() {
		return lblTerreno;
	}

	public void setLblMatriz(JLabel[][] lblMatriz) {
		this.lblMatriz = lblMatriz;
	}

	public void setLblTerreno(JLabel[][] lblTerreno) {
		this.lblTerreno = lblTerreno;
	}
	
}
