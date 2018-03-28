package imt.uvinfo.pacxon.modele;

public enum TypeBloc {
	Bordure(0), Vide(1), Trace(2), TraceTouche(3), BlocNormal(4), TmpRempli(5), TmpRempliValide(6), TmpRempliAnnule(7);
	
	private int id;
	
	private TypeBloc(int id) {
		this.id = id;
	}
	
	protected boolean isVide() {
		if((this == Vide) || (this == TmpRempliAnnule)) {
			return true;
		} else {
			return false;
		}
	}
	
}
