package imt.uvinfo.pacxon.modele;

public enum TypeBloc {
	Bordure(0), Vide(1), Trace(2), TraceTouche(3), BlocNormal(4);
	
	private int id;
	
	private TypeBloc(int id) {
		this.id = id;
	}
	
}
