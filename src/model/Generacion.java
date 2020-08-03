package model;
import java.util.Random;

public class Generacion {
	public Robot[] robots;
	public Robot mejorRobotActual;
	public Posicion objetivo;
	public double distanciaMinima;
	public int aptitudGeneral;
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
		System.out.println(mejorRobotActual.toString()+"Dist:"+distanciaMinima);		
	}
	public void cruzar() {
		float[] probabilidades = new float[robots.length+1];
		probabilidades[0]=0;
		for(int i=0; i<robots.length; i++) {
			probabilidades[i+1]=(robots[i].valorAptitud/aptitudGeneral)*100;
			if(i>0) {
				probabilidades[i+1]=probabilidades[i+1]+probabilidades[i];
			}
		}
		
	}
	
}
