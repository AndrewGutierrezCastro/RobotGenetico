package model;

public enum Hardware {
	BASICO (0.30, 1000), 
	MEDIO (0.60, 2000), 
	AVANZADO (0.90, 3000);
	
	private final double costo;
	private final double energia;
	private double diferencia = 0.01;
	
	Hardware(double pEnergia, double pCosto) {
		costo = (pCosto*diferencia) + pCosto ;
		energia = (pEnergia*diferencia) + pEnergia;
	}
	public double energia() { return energia;}
	public double costo() { return costo; }
	
	public double getDiferencia() {
		return diferencia;
	}
	public void setDiferencia(double diferencia) {
		this.diferencia = diferencia;
	}
	
	
}
