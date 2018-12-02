package main;

import java.io.IOException;

import accionesSemanticas.Automata;
import lector.Lector;
import tablas.tablasConsulta.TablaOperadores;
import tablas.tablasConsulta.TablaPalabrasReservadas;
import tablas.tablasConsulta.TablaPuntuacion;
import tablas.tablasDinamicas.TablaErrores;
import tablas.tablasDinamicas.TablaSimbolos;
import tablas.tablasDinamicas.TablaTokens;
import tokens.General;
import tokens.Token;
import errores.Error;

public class AnalizadorLexico {

	private Lector lector;
	private TablaSimbolos tablaSimbolos;
	public TablaTokens tablaTokens;
	private TablaErrores tablaErrores;
	private Integer lineaInicio;
	private long contador=0;
	
	public AnalizadorLexico(String file) {
		this.lector = new Lector(file);
		this.lineaInicio = 1;
	}
	
	public void setTablaTokens(TablaTokens tablaTokens) {
		this.tablaTokens = tablaTokens;
	}
	
	public void setTablaSimbolos(TablaSimbolos tablaSimbolos) {
		this.tablaSimbolos = tablaSimbolos;
	}
	
	public void setTablaErrores(TablaErrores tablaErrores) {
		this.tablaErrores = tablaErrores;
	}	
	
	public Token generaToken() {			
		
		Automata.setTablaSimbolos(tablaSimbolos);
		Automata.resetAutomata();
		
		Token token = null;
		Error error = null;
		
		int car=0;
		while (token == null && error ==null) {
			try {
				
				car = lector.readChar();
				contador++;
				
				if (car==10) lineaInicio++;				

				if ((token = Automata.automata(car))!=null) {
					System.out.println(token.toString());
					token.anadirToken(tablaTokens);
				}
				
				Integer errorGenerado;
				if((errorGenerado = Automata.error()) != null) {
					error = new Error(errorGenerado, this.lineaInicio);
					this.tablaErrores.anadirEntrada(error);
					if(errorGenerado!=0) lector.seek(--contador);
				}
				
				int caracter;
				if((caracter = Automata.noLeer()) != 0) {
					lector.seek(--contador);
				}		
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
		return token;
	}	
}
