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
		
		/* Génération des blocs */
		/* TODO : Possibilité d'utiliser une "map" (un fichier de conf) */
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

	public int getLargeur() {
		return largeur;
	}
	
	public int getHauteur() {
		return hauteur;
	}
	
	public int getXint(double Xdouble) {
		return (int)Math.floor(Xdouble * largeur);
	}
	
	public int getYint(double Ydouble) {
		return (int)Math.floor(Ydouble * hauteur);
	}
	
	public TypeBloc getBloc(int x, int y) {
		return blocs[x][y];
	}

	public TypeBloc getBloc(double x, double y) {
		return blocs[getXint(x)][getYint(y)];
	}
	
	protected void setBloc(TypeBloc nouveauBloc, int x, int y) {
		if(blocs[x][y] != TypeBloc.Bordure) { // TODO : Possibilité de vérifier que le nouveau bloc est aussi autre qu'une bordure, mais moins grave
			blocs[x][y] = nouveauBloc;
		}
	}

	protected void setBloc(TypeBloc nouveauBloc, double x, double y) {
		if(blocs[getXint(x)][getYint(y)] != TypeBloc.Bordure) {
			blocs[getXint(x)][getYint(y)] = nouveauBloc;
		}
	}

}
