package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Trace {
	private ArrayList<int[]> blocs = new ArrayList<int[]>();
	
	// La trace du héros est touchée ?
	private boolean isTouche = false;
	// La trace du héros est touchée aux coordonnées :
	private int toucheX = 0;
	private int toucheY = 0;
	// Configuration de la vitesse de propagation de la "touche" sur la trace
	private double vitesseTouche;
	// Pour garder en mémoire l'avancement précédent (propagation)
	private double bufferTouche = 0.0;
	
	public Trace() {
		// TODO Configurable avec conf
		vitesseTouche = 0.5;
	}
	
	protected void init() {
		bufferTouche = 0.0;
		toucheX = 0;
		toucheY = 0;
		isTouche = false;
		blocs.clear();
	}
	
	protected void update(float elapsedTime) {
		if(isTouche) {
			bufferTouche += elapsedTime * vitesseTouche;
			while(bufferTouche >= 1.0) { // Ne devrait être vérifiée qu'une fois
				bufferTouche -= 1.0;
				
			}
		}
	}
	
	// La trace s'est faite toucher, on initialise la propagation
	protected void toucherTrace(int x, int y) {
		if(!isTouche) { // On ne retouche pas la trace si elle est déjà touchée
			toucheX = x;
			toucheY = y;
			bufferTouche = 0.0;
		}
	}
	
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
