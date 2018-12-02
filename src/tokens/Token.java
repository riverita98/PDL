package tokens;

import tablas.tablasDinamicas.TablaTokens;

public interface Token {

	public void anadirToken(TablaTokens tabla);
	
	public Object getValorToken(); 
	
}
