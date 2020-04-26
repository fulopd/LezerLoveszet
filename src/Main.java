import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	private static double xCeltabla = 0;
	private static double yCeltabla = 0;
	
	//Beolvasás
	public static List<JatekosLovese> jatekosLoveseLista() {
		List<JatekosLovese> jatekosLoveseLista = new ArrayList<>();
		int sorszam = 1;
		try {
			List<String> beolvas = Files.readAllLines(Paths.get("lovesek.txt"));
			String[] splitElsoSor = beolvas.get(0).split(";");
			xCeltabla = Double.parseDouble(splitElsoSor[0].replace(',', '.'));
			yCeltabla = Double.parseDouble(splitElsoSor[1].replace(',', '.'));
			for (String sor : beolvas.subList(1, beolvas.size())) {
				String[] split = sor.split(";");
				JatekosLovese o = new JatekosLovese(sorszam++,
													split[0], 
													Double.parseDouble(split[1].replace(',', '.')),
													Double.parseDouble(split[2].replace(',', '.')),
													xCeltabla,
													yCeltabla
													);
				
				jatekosLoveseLista.add(o);
			}
		} catch (IOException e) {
			System.err.println("Hiba a beolvasasnal");
		}
		return jatekosLoveseLista;
	}
	
	
	public static void main(String[] args) {
		//4. feladat:
		List<JatekosLovese> jatekosLoveseLista = jatekosLoveseLista();
		System.out.println("Céltábla koordinátái: " + xCeltabla +" / " + yCeltabla );
		/*for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			System.out.println(jatekosLovese);
		}*/
		
		//5.feladat
		System.out.println("5. feladat: Lövések száma: " + jatekosLoveseLista.size() + " db");
				
		//7.feladat
		double min = Double.MAX_VALUE;
		JatekosLovese minLoves = null;
		for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			if (jatekosLovese.getTavolsag() < min) {
				min = jatekosLovese.getTavolsag();
				minLoves = jatekosLovese;
			}
		}		
		System.out.println("7. feladat: Legpontosabb lövés:\n"
									+ "\t"+
									minLoves.getSorszam() +".; " + 
									minLoves.getNev() +"; x=" + 
									minLoves.getxKoordinata() + "; y=" + 
									minLoves.getyKoordinata() +"; tavolsag: " + 
									minLoves.getTavolsag());
		
		//9.feladat
		int counter = 0;
		for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			if (jatekosLovese.getPontszam() == 0.0) {
				counter++;
			}
		}
		System.out.println("9. feladat: Nulla pontos lövések száma: " + counter + " db");
		
		//10.feladat
		HashSet<String> h = new HashSet<String>(); 
		for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			h.add(jatekosLovese.getNev());
		}
		System.out.println("10. feladat: játékosok száma: " + h.size());
		
		//11.feladat
		Map<String, Integer> lovesekSzama = new TreeMap<>();
		for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			lovesekSzama.merge(jatekosLovese.getNev(), 1, Integer::sum);
		}
		System.out.println("11. feladat: Lövések száma:\t");
		lovesekSzama.forEach((k,v) -> System.out.println("\t"+k +" - " + v + " db"));
		Iterator<Integer> lovSzam = lovesekSzama.values().iterator();
		
		//12.feladat
		Map<String, Double> atlagPontszamok = new TreeMap<>();
		for (JatekosLovese jatekosLovese : jatekosLoveseLista) {
			atlagPontszamok.merge(jatekosLovese.getNev(), jatekosLovese.getPontszam(), Double::sum);
		}
		System.out.println("12. feladat: Átlagpontszámok:\t");	
		double max = Double.MIN_VALUE;
		String nyertes = "";
		for (Map.Entry<String, Double> 
	        entry : atlagPontszamok.entrySet()) {
					double atlag = entry.getValue() / lovSzam.next();
					if(atlag > max) {
						max = atlag;
						nyertes = entry.getKey();
					}
						
					System.out.println( 
					"\t" + entry.getKey() 
					+ " - " + atlag); 	
		}	
		//13. feladat
		System.out.println("13. feladat: A játék nyertese: "+nyertes);
		
	}

}
