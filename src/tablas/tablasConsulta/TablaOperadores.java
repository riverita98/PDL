package tablas.tablasConsulta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import escritor.Escritor;
import tokens.codigos.CodigoToken;
import utils.Pair;

public final class TablaOperadores {

	private static HashMap<String, Pair<Integer, String>> tabla;

	private TablaOperadores() {
	}

	private static void anadirOperadores() {
		tabla.put("+", new Pair<Integer, String>(CodigoToken.COD_OP_SUMA, CodigoToken.MSG_SUMA));
		tabla.put("-", new Pair<Integer, String>(CodigoToken.COD_OP_RESTA, CodigoToken.MSG_RESTA));
		tabla.put("*", new Pair<Integer, String>(CodigoToken.COD_OP_MUL, CodigoToken.MSG_MUL));
		tabla.put("/", new Pair<Integer, String>(CodigoToken.COD_OP_DIV, CodigoToken.MSG_DIV));
		tabla.put("==", new Pair<Integer, String>(CodigoToken.COD_OP_IGUAL, CodigoToken.MSG_IGUAL));
		tabla.put("!=", new Pair<Integer, String>(CodigoToken.COD_OP_DESIGUAL, CodigoToken.MSG_DESIGUAL));
		tabla.put("<", new Pair<Integer, String>(CodigoToken.COD_OP_MENORQUE, CodigoToken.MSG_MENORQUE));
		tabla.put(">", new Pair<Integer, String>(CodigoToken.COD_OP_MAYORQUE, CodigoToken.MSG_MAYORQUE));
		tabla.put("++", new Pair<Integer, String>(CodigoToken.COD_OP_INC, CodigoToken.MSG_INC));
		tabla.put("--", new Pair<Integer, String>(CodigoToken.COD_OP_DEC, CodigoToken.MSG_DEC));
		tabla.put("=", new Pair<Integer, String>(CodigoToken.COD_OP_ASIG, CodigoToken.MSG_ASIG));
		tabla.put("+=", new Pair<Integer, String>(CodigoToken.COD_OP_ASIGINC, CodigoToken.MSG_ASIGINC));
		tabla.put("-=", new Pair<Integer, String>(CodigoToken.COD_OP_ASIGDEC, CodigoToken.MSG_ASIGDEC));
		tabla.put("*=", new Pair<Integer, String>(CodigoToken.COD_OP_ASIGMUL, CodigoToken.MSG_ASIGMUL));
		tabla.put("/=", new Pair<Integer, String>(CodigoToken.COD_OP_ASIGDIV, CodigoToken.MSG_ASIGDIV));
		tabla.put("&&", new Pair<Integer, String>(CodigoToken.COD_OP_AND, CodigoToken.MSG_AND));
		tabla.put("||", new Pair<Integer, String>(CodigoToken.COD_OP_OR, CodigoToken.MSG_OR));
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
