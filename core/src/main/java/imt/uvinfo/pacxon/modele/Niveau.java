package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Niveau {
	
	
	private STUB_Terrain terrain;
	
	private ArrayList<Personnage> personnages;
	
	private float pourcentageObjectif;
	
	private int nbBlocsRemplis = 0;
	private int nbBlocsTotal;
	

	
	public Niveau(int num, int x, int y, ArrayList<Personnage> monstres, float pourcentObjectif) {
		int i = 0;
		
		personnages.add(new Heros());
		
		while(monstres.size() > 0) {
			personnages.add(monstres.remove(0));
		}
		
		
		pourcentageObjectif = pourcentObjectif;
		nbBlocsTotal = x*y;
		terrain = new STUB_Terrain(x, y);

	}
	
	
}
