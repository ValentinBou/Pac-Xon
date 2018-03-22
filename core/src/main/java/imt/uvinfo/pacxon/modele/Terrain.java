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
	
	public TypeBloc getBloc(int x, int y) {
		return blocs[x][y];
	}

	public TypeBloc getBloc(double x, double y) {
		return blocs[(int)Math.floor(x * largeur)][(int)Math.floor(y * hauteur)];
	}



}
