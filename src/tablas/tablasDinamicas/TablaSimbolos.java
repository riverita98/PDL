package tablas.tablasDinamicas;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import escritor.Escritor;
import tokens.Identificador;

public class TablaSimbolos {

	private String nombreTabla;
	private LinkedHashMap<Identificador, Integer> tabla;
	private LinkedHashMap<Integer, Identificador> tablaReverso;
	private int entrada=1;
	private int posMemoria;
	
	public TablaSimbolos(String nombreTabla) {
		this.nombreTabla = "TABLA DE SIMBOLOS #" + nombreTabla;
		this.tabla = new LinkedHashMap<Identificador, Integer>();
		this.tablaReverso = new LinkedHashMap<Integer, Identificador>();
	}
	
	public Identificador anadirEntrada(Identificador id) {		
		this.tabla.put(id, entrada);
		this.tablaReverso.put(entrada, id);
		return this.tablaReverso.get(entrada++);
	}
	
	public Integer getPos(Identificador id) {
		return this.tabla.get(id);
	}
	
	public Identificador getIdentificador(Integer pos) {		
		return this.tablaReverso.get(pos);
	}
	//devuelve null si no encuentra
	public Integer getEntrada(Identificador id) {
		return this.tabla.get(id);
	}
	
	public void exportarTabla(String file) {
		Escritor escritor = new Escritor(file);	
		Iterator<Entry<Identificador, Integer>> it = tabla.entrySet().iterator();
		Entry<Identificador, Integer> entrada;
		escritor.escribir(nombreTabla);
		while(it.hasNext()) {
			entrada = it.next();
			escritor.escribir(entrada.getKey().toStringTablaSimbolos());
		}
	}
	
	public int getPosMemoria() {
		return this.posMemoria = this.hashCode();
	}
	
}
