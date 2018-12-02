package tablas.tablasDinamicas;

import java.util.ArrayList;

import errores.Error;
import escritor.Escritor;

public class TablaErrores {

	private ArrayList<Error> tabla;

	public TablaErrores() {
		this.tabla = new ArrayList<Error>();
	}

	public void anadirEntrada(Error error) {
		tabla.add(error);
	}

	public void exportarTabla(String file) {
		Escritor escritor = new Escritor(file);
		for (Error error : tabla) {
			escritor.escribir(error.toString());
		}
	}

}
