package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Jeu {
	private int score = 0;
	private int nbViesRestantes;
	private int nbViesTotal;
	
	private int curseurNiveauActuel = 0;
	private ArrayList<Niveau> listeNiveaux;
	
	public Jeu(int nbVies) {
		nbViesTotal = nbVies;
		nbViesRestantes = nbVies;
		
		listeNiveaux = new ArrayList<Niveau>();

		creerNiveaux();
	}
	
	private void creerNiveaux() {
		/* TODO : possibilité d'utiliser un fichier de configuration ici */
		ArrayList<Personnage> monstres = new ArrayList<Personnage>();
		int defX = 20;
		int defY = 10;
		float defPourcentObjectif = (float) 0.8;
		
		monstres.add(new MonstreNormal());
		listeNiveaux.add(new Niveau(1, defX, defY, monstres, defPourcentObjectif));
		monstres.clear();
		monstres.add(new MonstreNormal());
		monstres.add(new MonstreNormal());
		listeNiveaux.add(new Niveau(2, defX, defY, monstres, defPourcentObjectif));
		monstres.clear();
		monstres.add(new MonstreNormal());
		monstres.add(new MonstreNormal());
		monstres.add(new MonstreNormal());
		listeNiveaux.add(new Niveau(3, defX, defY, monstres, defPourcentObjectif));
		monstres.clear();
	}
	
	public int getScore() {
		return score;
	}

	public void addToScore(int score) {
		this.score += score;
	}

	public int getNbViesRestantes() {
		return nbViesRestantes;
	}

	public void perdreVie() {
		this.nbViesRestantes--;
	}

	public int getNbViesTotal() {
		return nbViesTotal;
	}
	
	public boolean isFini() {
		if(nbViesRestantes <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public Niveau getNiveauActuel() {
		return listeNiveaux.get(curseurNiveauActuel);
	}
	
	public void passerNiveau() {
		curseurNiveauActuel++;
	}
}
