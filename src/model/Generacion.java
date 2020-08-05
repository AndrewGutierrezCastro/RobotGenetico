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
			Random rand = new Random();//Temporal
			robots[i].valorAptitud=rand.nextInt(50-1)+1; //valor de aptitud random temporal para pruebas
		}
		mejorRobotActual = robots[0];
		distanciaMinima = Double.MAX_VALUE;
		seleccionarElegidos();		
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
	public void seleccionarElegidos() {

		for(Robot rob : robots) {
			this.aptitudGeneral=this.aptitudGeneral+rob.valorAptitud; //for temporal para pruebas
		}
		Robot[] robotsElegidos = new Robot[robots.length]; 
		float[] probabilidades = new float[robots.length+1];
		int numeroDeEleccion;
		probabilidades[0]=0;
		Random rand=new Random();
		for(int i=0; i<robots.length; i++) {
			probabilidades[i+1]=((float)robots[i].valorAptitud/this.aptitudGeneral)*100;
			if(i>0) {
				probabilidades[i+1]=probabilidades[i+1]+probabilidades[i];
			}
		}
		for(int j=0; j<robots.length; j++) {
			numeroDeEleccion=rand.nextInt(100-1)+1;
			for(int k=1; k<robots.length; k++) {
				if(probabilidades[k] >= numeroDeEleccion && probabilidades[k-1] <= numeroDeEleccion ){
					robotsElegidos[j]=robots[k];
					break;
				}
			}
		}
		Cruzar(robotsElegidos);
	}
	public void Cruzar(Robot[] robotsElegidos) {
		Random rand = new Random();
		int numOmitido;
		for(int i=0; i < robotsElegidos.length; i=i+2) {
			System.out.println("********************************");
			System.out.println(robotsElegidos[i].caracteristicas.Bateria.tipo);
			System.out.println(robotsElegidos[i].caracteristicas.Motor.tipo);
			System.out.println(robotsElegidos[i].caracteristicas.Bateria.tipo);

			System.out.println(robotsElegidos[i+1].caracteristicas.Bateria.tipo);
			System.out.println(robotsElegidos[i+1].caracteristicas.Motor.tipo);
			System.out.println(robotsElegidos[i+1].caracteristicas.Camara.tipo);
			numOmitido=rand.nextInt(3-0)+0;
			for(int j=0; j<3; j++) {
				if(numOmitido != j) {
					switch(j) {
						case 0:
							Hardware bat = robotsElegidos[i].caracteristicas.Bateria;
							robotsElegidos[i].caracteristicas.Bateria = robotsElegidos[i+1].caracteristicas.Bateria;
							robotsElegidos[i+1].caracteristicas.Bateria = bat;
							break;
						case 1:
							Hardware mot = robotsElegidos[i].caracteristicas.Motor;
							robotsElegidos[i].caracteristicas.Motor = robotsElegidos[i+1].caracteristicas.Motor;
							robotsElegidos[i+1].caracteristicas.Motor = mot;
							break;
						case 2:
							Hardware cam = robotsElegidos[i].caracteristicas.Camara;
							robotsElegidos[i].caracteristicas.Camara = robotsElegidos[i+1].caracteristicas.Camara;
							robotsElegidos[i+1].caracteristicas.Camara = cam;
							break;
					}
				}
			}
//			System.out.println("--------------------------------------------");
//			System.out.println(robotsElegidos[i].caracteristicas.Bateria.tipo);
//			System.out.println(robotsElegidos[i].caracteristicas.Motor.tipo);
//			System.out.println(robotsElegidos[i].caracteristicas.Bateria.tipo);
//
//			System.out.println(robotsElegidos[i+1].caracteristicas.Bateria.tipo);
//			System.out.println(robotsElegidos[i+1].caracteristicas.Motor.tipo);
//			System.out.println(robotsElegidos[i+1].caracteristicas.Camara.tipo);
		}
	}
}