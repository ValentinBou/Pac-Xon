package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Terrain {

	private TypeBloc blocs[][];
	private int largeur;
	private int hauteur;
	
	public Terrain(int x, int y){
		int i, j;
		
		blocs = new TypeBloc[x][y];
		largeur = x;
		hauteur = y;
		
		/* G�n�ration des blocs */
		/* TODO : Possibilit� d'utiliser une "map" (un fichier de conf) */
		for(i = 0; i < largeur; i++) {
			for(j = 0; j < hauteur; j++) {
				if((i == 0) || (j == 0) || (i == (largeur - 1)) || (j == (hauteur-1))) {
					blocs[i][j] = TypeBloc.Bordure;
				} else {
					blocs[i][j] = TypeBloc.Vide;
				}
			}
		}
	}
	
	// Remplace tous les blocs "Tracage" par des "BlocNormal"
	protected void remplacerTracageParBloc() {
		int i, j;
		
		for(i = 0; i < largeur; i++) {
			for(j = 0; j < hauteur; j++) {
				if(blocs[i][j] == TypeBloc.Trace || blocs[i][j] == TypeBloc.TraceTouche) {
					blocs[i][j] = TypeBloc.BlocNormal;
				}
			}
		}
	}
	
	// Remplace tous les blocs "TmpRempli" par des "TmpRempliValide"
	protected void validerRemplissage() {
		int i, j;
		
		for(i = 0; i < largeur; i++) {
			for(j = 0; j < hauteur; j++) {
				if(blocs[i][j] == TypeBloc.TmpRempli) {
					blocs[i][j] = TypeBloc.TmpRempliValide;
				}
			}
		}
	}
	
	// Remplace tous les blocs "TmpRempli" par des "TmpRempliAnnule"
	protected void annulerRemplissage() {
		int i, j;
		
		for(i = 0; i < largeur; i++) {
			for(j = 0; j < hauteur; j++) {
				if(blocs[i][j] == TypeBloc.TmpRempli) {
					blocs[i][j] = TypeBloc.TmpRempliAnnule;
				}
			}
		}
	}
	
	// Remplace les blocs validés par des blocs normaux et les blocs annulés par du vide
	protected void finaliserRemplissage() {
		int i, j;
		
		for(i = 0; i < largeur; i++) {
			for(j = 0; j < hauteur; j++) {
				if(blocs[i][j] == TypeBloc.TmpRempliValide) {
					blocs[i][j] = TypeBloc.BlocNormal;
				} else if(blocs[i][j] == TypeBloc.TmpRempliAnnule) {
					blocs[i][j] = TypeBloc.Vide;
				}
			}
		}
	}

	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	// Donne la coordonn�e du terrain enti�re correspondante � la coordonn�e flottante
	public int getXint(double Xdouble) {
		return (int)Math.floor(Xdouble * largeur);
	}
	
	// Donne la coordonn�e du terrain enti�re correspondante � la coordonn�e flottante
	public int getYint(double Ydouble) {
		return (int)Math.floor(Ydouble * hauteur);
	}
	
	// R�cup�ration d'un bloc, bloque aux limites du terrain
	public TypeBloc getBloc(int x, int y) {
		if(x < 0) {
			x = 0;
		} else if(x >= largeur) {
			x = largeur - 1;
		}
		
		if(y < 0) {
			y = 0;
		} else if(y >= hauteur) {
			y = hauteur - 1;
		}
		
		return blocs[x][y];
	}

	public TypeBloc getBloc(double x, double y) {
		return getBloc(getXint(x),getYint(y));
	}
	
	// Remplacement d'un bloc, bloque aux limites du terrain
	protected void setBloc(TypeBloc nouveauBloc, int x, int y) {
		if(x < 0) {
			x = 0;
		} else if(x >= largeur) {
			x = largeur - 1;
		}
		
		if(y < 0) {
			y = 0;
		} else if(y >= hauteur) {
			y = hauteur - 1;
		}
		
		if(blocs[x][y] != TypeBloc.Bordure) { // TODO : Possibilit� de v�rifier que le nouveau bloc est aussi autre qu'une bordure, mais moins grave
			blocs[x][y] = nouveauBloc;
		}
	}

	protected void setBloc(TypeBloc nouveauBloc, double x, double y) {
		setBloc(nouveauBloc,getXint(x),getYint(y));
	}

}
