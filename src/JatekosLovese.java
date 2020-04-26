public class JatekosLovese {
	
	//Ricsi;26,99;33,00
	private int sorszam;
	private String nev;
	private double xKoordinata;
	private double yKoordinata;
	private double tavolsag;
	private double pontszam;
			
	public JatekosLovese(int sorszam, String nev, double xKoordinata, double yKoordinata, double xCeltabla, double yCeltabla) {		
		this.sorszam = sorszam;
		this.nev = nev;
		this.xKoordinata = xKoordinata;
		this.yKoordinata = yKoordinata;		
		setTavolsag(xCeltabla, yCeltabla); 
		setPontszam(tavolsag);
	}
	
	private void setTavolsag(double xCeltabla, double yCeltabla) {
		double dx = xCeltabla - xKoordinata;
		double dy = yCeltabla - yKoordinata;
		tavolsag = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2)); 
	}
	
	public void setPontszam(double tavolsag) {
		this.pontszam = Math.round((10 - tavolsag)*100.0)/100.0;
		if (pontszam<0) {
			pontszam = 0.0;
		}		
	}
	
	public int getSorszam() {
		return sorszam;
	}
	public void setSorszam(int sorszam) {
		this.sorszam = sorszam;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public double getxKoordinata() {
		return xKoordinata;
	}
	public void setxKoordinata(double xKoordinata) {
		this.xKoordinata = xKoordinata;
	}
	public double getyKoordinata() {
		return yKoordinata;
	}
	public void setyKoordinata(double yKoordinata) {
		this.yKoordinata = yKoordinata;
	}
		
	public double getTavolsag() {
		return tavolsag;
	}
		
	public double getPontszam() {
		return pontszam;
	}

	@Override
	public String toString() {
		return "JatekosLovese [sorszam=" + sorszam + ", nev=" + nev + ", xKoordinata=" + xKoordinata + ", yKoordinata="
				+ yKoordinata + ", tavolsag=" + tavolsag + ", pontszam=" + pontszam + "]";
	}
			
}
