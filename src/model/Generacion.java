package model;

public class Generacion {
	public Robot[] robots;
	public Robot mejorRobotActual;
	public Posicion objetivo;
	public double distanciaMinima;
	public Generacion(int size) {
		robots = new Robot[size];
		objetivo = new Posicion(0,19);
	}
	public void GeneracionAleatoria() {
		for (int i = 0; i < robots.length; i++) {
			robots[i] = new Robot();
			robots[i].Definir();			
		}
		mejorRobotActual = robots[0];
		distanciaMinima = Double.MAX_VALUE;
	}
	public void ComportarRobots() {
	
		for (Robot robot : robots) {
			robot.Comportarse();
			if(objetivo.distancia(robot.posicion) < distanciaMinima) {
				mejorRobotActual = robot;
				distanciaMinima = objetivo.distancia(robot.posicion);
			}
		}
		System.out.println(mejorRobotActual.toString()+" Dist:"+distanciaMinima);
		
	}
	
}
