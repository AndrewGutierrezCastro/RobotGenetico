package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Terreno {
	public Bloque[][] terreno;
	public final int ancho = 20, alto = 20;
	public final long seed = 13853511;
	
	public Terreno() {
		terreno =  new Bloque[ancho][alto];
		//TODO Leer nivel JSON 
		GenerarTerrenoRandom();
		LeerNivelJson();
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
	private void LeerNivelJson() {
		File archTerr = new File("F:/Archivos Tec/Tercer semestre/Proyectos/Analisis/AnalisisP3/RobotGenetico/src/model/Terreno.txt");
		try {
			Scanner revisador = new Scanner(archTerr);
			int filTerr=0;
			while(revisador.hasNextLine()) {
				String filaTerr=revisador.nextLine();
				String[] filaSeparada=filaTerr.split(",");
				for(int i=0;i<filaSeparada.length-1;i++) {
					if(filaSeparada[i].equals("0")){
						Bloque seccion = Bloque.NORMAL;
						this.terreno[filTerr][i]=seccion;
					}
					if(filaSeparada[i].equals("1")) {
						Bloque seccion = Bloque.MODERADO;
						this.terreno[filTerr][i]=seccion;
					}
					if(filaSeparada[i].equals("2")) {
						Bloque seccion = Bloque.DIFICIL;
						this.terreno[filTerr][i]=seccion;
					}
					if(filaSeparada[i].equals("3")) {
						Bloque seccion = Bloque.BLOQUEADO;
						this.terreno[filTerr][i]=seccion;
					}
				}
				filTerr++;
			}
			revisador.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("Archivo no encontrado");
		}
	}
	
}
