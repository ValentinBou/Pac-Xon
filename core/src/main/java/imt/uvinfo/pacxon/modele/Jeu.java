package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Jeu {
	// Statistiques de la partie
	private int score = 0;
	private int nbViesRestantes;
	private int nbViesTotal;
	
	// Niveaux / Niveau actuel
	private int curseurNiveauActuel = 0;
	private ArrayList<Niveau> listeNiveaux;
	
	
	
	public Jeu(int nbVies) {
		nbViesTotal = nbVies;
		nbViesRestantes = nbVies;
		
		listeNiveaux = new ArrayList<Niveau>();

		creerNiveaux();
	}
	
	// Permet de générer les Niveaux
	// Cette fonction est à modifier pour changer les niveaux, ou pour externaliser la définition de niveaux
	private void creerNiveaux() {
		/* TODO : possibilité d'utiliser un fichier de configuration ici */
		ArrayList<Personnage> monstres = new ArrayList<Personnage>();
		int defX = 40;
		int defY = 30;
		double defPourcentObjectif = 0.8;
		
		monstres.add(new MonstreNormal(this));
		listeNiveaux.add(new Niveau(1, defX, defY, new Heros(this), monstres, defPourcentObjectif));
		monstres.clear();
		monstres.add(new MonstreNormal(this));
		monstres.add(new MonstreNormal(this));
		listeNiveaux.add(new Niveau(2, defX, defY, new Heros(this), monstres, defPourcentObjectif));
		monstres.clear();
		monstres.add(new MonstreNormal(this));
		monstres.add(new MonstreNormal(this));
		monstres.add(new MonstreNormal(this));
		listeNiveaux.add(new Niveau(3, defX, defY, new Heros(this), monstres, defPourcentObjectif));
		monstres.clear();
		
		// Init du premier niveau (actuel)
		listeNiveaux.get(0).initier();
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
	
	// Game Over
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
		getNiveauActuel().initier();
	}
}
