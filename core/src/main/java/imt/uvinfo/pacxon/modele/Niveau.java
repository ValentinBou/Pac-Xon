package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Niveau {
	
	
	private STUB_Terrain terrain;
	
	private Personnage heros;
	private ArrayList<Personnage> personnages;
	
	private double pourcentageObjectif;
	
	private int nbBlocsRemplis = 0;
	private int nbBlocsTotal;
	
	public Niveau(int num, int x, int y, Heros heros, ArrayList<Personnage> monstres, double pourcentObjectif) {
	
		this.heros = heros;
		
		personnages = new ArrayList<Personnage>();
		
		while(monstres.size() > 0) {
			personnages.add(monstres.remove(0));
		}
		
		
		pourcentageObjectif = pourcentObjectif;
		nbBlocsTotal = x*y;
		terrain = new STUB_Terrain(x, y);

	}
	
	protected void initier() {
		int i = 0;
		heros.initier();
		for(i = 0; i < personnages.size(); i++) {
			personnages.get(i).initier();
		}
	}

	public STUB_Terrain getTerrain() {
		return terrain;
	}

	public Personnage getHeros() {
		return heros;
	}
	
	public Personnage getPersonnage(int i) {
		return personnages.get(i);
	}
	
	public int getNbPersonnages() {
		return personnages.size();
	}

	public double getPourcentageObjectif() {
		return pourcentageObjectif;
	}
	
	public double getPourcentageRempli() {
		return nbBlocsRemplis/nbBlocsTotal;
	}
}
