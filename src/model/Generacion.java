package model;

public class Generacion {
	public Robot[] robots;

	public Generacion(int size) {
		robots = new Robot[size];
	}
	public void GeneracionAleatoria() {
		for (int i = 0; i < robots.length; i++) {
			robots[i] = new Robot();
			robots[i].Definir();
		}
	}
	
}
