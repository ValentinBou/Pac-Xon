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
	
	private boolean fini = false;
	
	public Jeu(int nbVies) {
		nbViesTotal = nbVies;
		nbViesRestantes = nbVies;
		
		listeNiveaux = new ArrayList<Niveau>();

		creerNiveaux();
	}
	
	// Permet de g�n�rer les Niveaux
	// Cette fonction est � modifier pour changer les niveaux, ou pour externaliser la d�finition de niveaux
	private void creerNiveaux() {
		/* TODO : possibilit� d'utiliser un fichier de configuration ici */
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
	
	public void update(float elapsedTime) {
		if(getNiveauActuel().isFini()) {
			// TODO : Possibilité d'afficher un écran "niveau gagné" pendant un temps
			passerNiveau();
		}
		
		getNiveauActuel().update(elapsedTime);
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
	
	// Game Over plus de vies
	public boolean isPerdu() {
		if(nbViesRestantes <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Game Over gagné
	public boolean isFini() {
		return fini;
	}

	public Niveau getNiveauActuel() {
		return listeNiveaux.get(curseurNiveauActuel);
	}
	
	public void passerNiveau() {
		if(curseurNiveauActuel < (listeNiveaux.size() - 1)) {
			curseurNiveauActuel++;
			getNiveauActuel().initier();
		} else {
			fini = true;
		}
	}
}
