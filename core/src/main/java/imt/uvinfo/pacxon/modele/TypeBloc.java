package imt.uvinfo.pacxon.modele;

public enum TypeBloc {
	BORDURE(0), VIDE (1), TRACE(2),TRACETOUCHE(3), BLOCNORMAL(4);
	
	private int niveauBloc;
	
	public TypeBloc(int nb) {
		niveauBloc = nb;
	}
	
	public int getNiveauBloc() {
		return niveauBloc;
	}
	
	public String getTypeBloc() {
		switch(this) {
		case BORDURE : 
			return "bordure";
			//break;
		case VIDE : 
			return "vide";
			//break;
		case TRACE : 
			return "trace";
			//break;
		case TRACETOUCHE : 
			return "tracetouche";
			//break;
		case BLOCNORMAL : 
			return "blocnormal";
			//break;
		default : 
			return "erreur";
			//break;
		}
	}
	
}
