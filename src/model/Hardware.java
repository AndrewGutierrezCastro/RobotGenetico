package model;

public class Hardware implements Cloneable{
	//Primero la energia que cuesta utilizarlo y luego costo
	public enum TiposHardware{
		/*
		 *Enumerable para los tipos de hardware standard */
		BASICO (0.30, 1000), 
		MEDIO (0.60, 2000), 
		AVANZADO (0.90, 3000);
		
		private double costo;
		private double energia; //La energia se puede traducir a potencia para el motor
								//La distancia que puede ver el robot = energia / 1000 y su gasto de uso energia / 20
								//La capacidad de la bateria en mAh
		TiposHardware(double pCosto, double pEnergia) {
			costo = pCosto ;
			energia = pEnergia;
		}
		public double getEnergia() { return energia;}
		public double getCosto() { return costo; }
		}
	
	private double costo;
	private double energia;
	public TiposHardware tipo;
	public Hardware(TiposHardware tipoHardware) {
		super();
		costo = tipoHardware.getCosto();
		energia = tipoHardware.getEnergia();
		tipo = tipoHardware;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public double getEnergia() {
		return energia;
	}
	public void setEnergia(double energia) {
		this.energia = energia;
	}
	public String getName() {
		return tipo.name();
	}
	public Hardware clone() {
		return new Hardware(this.tipo);
	}
}
