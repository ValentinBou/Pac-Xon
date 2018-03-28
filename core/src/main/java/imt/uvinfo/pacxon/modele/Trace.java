package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Trace {
	private Jeu jeu;
	
	private ArrayList<int[]> blocs = new ArrayList<int[]>();
	
	// La trace du héros est touchée ?
	private boolean isTouche = false;
	// La trace du héros est touchée aux coordonnées :
	private int curTouche = 0;
	// Configuration de la vitesse de propagation de la "touche" sur la trace
	private double vitesseTouche;
	// Pour garder en mémoire l'avancement précédent (propagation)
	private double progressionTouche = 0.0;
	
	public Trace(Jeu jeu) {
		// TODO Configurable avec conf
		vitesseTouche = 30.0;
		
		this.jeu = jeu;
	}
	
	// Reinitialiser la trace (vider et remise à 0)
	protected void reinit() {
		progressionTouche = 0.0;
		curTouche = 0;
		isTouche = false;
		blocs.clear();
	}
	
	// Mise à jour à chaque frame pour que la touche puisse progresser
	protected void update(float elapsedTime) {
		if(isTouche) { // Est touché
			Terrain terrain = jeu.getNiveauActuel().getTerrain();
			// Progression incrémentée
			progressionTouche += elapsedTime * vitesseTouche;
			int i = 0;
			// Pour chaque bloc à partir du premier bloc touché, dans la progression de la touche
			while((progressionTouche - (double)i) >= 1.0) {
				i++;
				// Si on ne va pas en dessous du premier bloc de la trace
				if(curTouche - i >= 0) {
					// Ce bloc est désormais touché
					terrain.setBloc(TypeBloc.TraceTouche, blocs.get(curTouche - i)[0], blocs.get(curTouche - i)[1]);
				}
				if(curTouche + i < blocs.size()) { // Si on ne va pas au dessus du dernier bloc de la trace
					// Ce bloc est désormais touché
					terrain.setBloc(TypeBloc.TraceTouche, blocs.get(curTouche + i)[0], blocs.get(curTouche + i)[1]);
				} else {
					// Au dessus du dernier bloc, on a atteint le héros, il meurt
					jeu.getNiveauActuel().getHeros().decede();
					return;
				}
			}
		}
	}
	
	// La trace s'est faite toucher, on initialise la propagation
	protected void toucher(int x, int y) {
		if(!isTouche) { // On ne retouche pas la trace si elle est déjà touchée
			curTouche = 0;
			int[] tmpBloc = blocs.get(curTouche);
			// On va chercher le bloc touché
			while((tmpBloc[0] != x) || (tmpBloc[1] != y)) {
				curTouche ++;
				if(curTouche < blocs.size()) {
					tmpBloc = blocs.get(curTouche);
				} else {
					break;
				}
			}
			// Le bloc touché est affiché comme touché
			jeu.getNiveauActuel().getTerrain().setBloc(TypeBloc.TraceTouche, tmpBloc[0], tmpBloc[1]);
			progressionTouche = 0.0;
			// Activation de la progression
			isTouche = true;
		}
	}
	
	// Ajout d'un bloc dans la trace
	protected void tracer(int x, int y) {
		int[] tmpCoords = {x,y};
		blocs.add(tmpCoords);
	}
	
	protected int[] getBloc(int cur) {
		return blocs.get(cur);
	}
	
	protected int getTaille() {
		return blocs.size();
	}
	
}
