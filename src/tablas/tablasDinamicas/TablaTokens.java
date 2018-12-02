package tablas.tablasDinamicas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import escritor.Escritor;
import tokens.Token;

public class TablaTokens {

	private ArrayList<Token> tabla;
	private int entrada = 1;

	public TablaTokens() {
		this.tabla = new ArrayList<Token>();
	}

	public void anadirEntrada(Token token) {		
		this.tabla.add(token);
	}

	// devuelve null si no encuentra
	public Integer getEntrada(Token token) {
		Integer i = 0;
		for (; i < this.tabla.size() && !this.tabla.get(i).equals(token); i++) {}		
		return i;
	}

	public void exportarTabla(String file) {
		Escritor escritor = new Escritor(file);
		for (Token token : tabla) {
			escritor.escribir(token.toString());
		}
	}
}
