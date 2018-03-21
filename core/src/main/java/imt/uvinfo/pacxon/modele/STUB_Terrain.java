package imt.uvinfo.pacxon.modele;

public class STUB_Terrain {
	
	private int stub_blocs[][] = new int[40][30];
	
	public STUB_Terrain(int x, int y) {
		int i, j;
		for(i = 0; i < 40; i++) {
			for(j = 0; j < 30; j++) {
				if((i == 0) || (j == 0) || (i == 39) || (j == 29)) {
					stub_blocs[i][j] = 1;
				} else {
					stub_blocs[i][j] = 0;
				}
			}
		}
	}
	
	public int getLargeur() {
		return 40;
	}
	
	public int getHauteur() {
		return 30;
	}
	
	public int getBloc(int x, int y) {
		return stub_blocs[x][y];
	}
}
