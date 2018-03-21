package imt.uvinfo.pacxon.modele;

import java.util.ArrayList;

public class Terrain {

	private int blocs;
	private int largeur;
	private int hauteur;
	
	// private iconesBlocs  icones;
		
	// Constructeur
	public Terrain(int x, int y){
		largeur = x;
		hauteur = y;
		blocs = largeur * hauteur;
		// icones = 
	}
	
	// Fonction
	public String getIconeBloc(){
		return TypeBloc;
	}
	public TypeBloc getTypeBloc(int X, int Y){
		return blocs = X * Y;
	}
}
