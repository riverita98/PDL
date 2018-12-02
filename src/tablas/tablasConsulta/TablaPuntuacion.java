package tablas.tablasConsulta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import escritor.Escritor;
import tokens.codigos.CodigoToken;
import utils.Pair;

public final class TablaPuntuacion {

	private static HashMap<String, Pair<Integer, String>> tabla;

	private TablaPuntuacion() {
	}

	private static void anadirOperadores() {
		tabla.put(";", new Pair<Integer, String>(CodigoToken.COD_PUNTOCOMA, CodigoToken.MSG_PUNTOCOMA));
		tabla.put("(", new Pair<Integer, String>(CodigoToken.COD_PAR_ABI, CodigoToken.MSG_PAR_ABI));
		tabla.put(")", new Pair<Integer, String>(CodigoToken.COD_PAR_CERR, CodigoToken.MSG_PAR_CERR));
		tabla.put("[", new Pair<Integer, String>(CodigoToken.COD_COR_AP, CodigoToken.MSG_COR_AP));
		tabla.put("]", new Pair<Integer, String>(CodigoToken.COD_COR_CERR, CodigoToken.MSG_COR_CERR));
		tabla.put("{", new Pair<Integer, String>(CodigoToken.COD_LLAVE_AP, CodigoToken.MSG_LLAVE_AP));
		tabla.put("}", new Pair<Integer, String>(CodigoToken.COD_LLAVE_CERR, CodigoToken.MSG_LLAVE_CERR));
		tabla.put(",", new Pair<Integer, String>(CodigoToken.COD_COMA, CodigoToken.MSG_COMA));
		tabla.put("", new Pair<Integer, String>(CodigoToken.COD_EOF, CodigoToken.MSG_EOF));
	}

	public static Pair<Integer, String> getEntrada(String valor) {
		return tabla.get(valor);
	}

	public static void crearTabla() {
		tabla = new HashMap<String, Pair<Integer, String>>();
		anadirOperadores();
	}

	public static void exportarTabla(String file) {
		Escritor escritor = new Escritor(file);
		Iterator<Entry<String, Pair<Integer, String>>> it = tabla.entrySet().iterator();
		Entry<String, Pair<Integer, String>> entrada;
		while (it.hasNext()) {
			entrada = it.next();
			escritor.escribir("< " + entrada.getKey().toString() + ", " + entrada.getValue().getKey() + " >");
		}
	}
}
