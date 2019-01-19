package tablas.tablasConsulta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import escritor.Escritor;
import tokens.codigos.CodigoPalabraReservada;
import tokens.codigos.CodigoToken;
import utils.Pair;

public final class TablaPalabrasReservadas {

	private static HashMap<String, Pair<Integer, String>> tabla;
	private static int entrada;

	private TablaPalabrasReservadas() {
	}

	private static void anadirPalabrasReservadas() {
		tabla.put("var", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_VAR));
		tabla.put("int", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_INT));
		tabla.put("bool", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_BOOL));
		tabla.put("string", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_STRING));
		tabla.put("print", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_PRINT));
		tabla.put("prompt", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_PROMPT));
		tabla.put("return", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_RETURN));
		tabla.put("if", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_IF));
		tabla.put("do", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_DO));
		tabla.put("while", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_WHILE));
		tabla.put("function", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_FUNCTION));
		tabla.put("true", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_TRUE));
		tabla.put("false", new Pair<Integer, String>(++entrada, CodigoPalabraReservada.MSG_FALSE));
	}

	public static Pair<Integer, String> getEntrada(String palabra) {
		return tabla.get(palabra);
	}

	public static void crearTabla() {
		tabla = new HashMap<String, Pair<Integer, String>>();
		entrada = CodigoToken.COD_PALRES; //desplazamiento respecto a tabla
		anadirPalabrasReservadas();
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
