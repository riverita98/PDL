package accionesSemanticas;

import tablas.tablasConsulta.TablaPalabrasReservadas;
import tablas.tablasDinamicas.TablaSimbolos;
import tokens.General;
import tokens.Identificador;
import tokens.Operador;
import tokens.PalabraReservada;
import tokens.Token;
import tokens.constantes.ConstanteCadena;
import tokens.constantes.ConstanteEntero;

public final class Automata {

	private static Integer entero;
	private static String acum;
	private static int previo;
	private static Token token;
	private static Integer estado = 0;
	private static TablaSimbolos tablaSimbolos;
	private static Integer error;

	private Automata() {
	}

	public static void setTablaSimbolos(TablaSimbolos tabla) {
		tablaSimbolos = tabla;
	}

	public static void setEstado(Integer estadoAtrib) {
		estado = estadoAtrib;
	}

	public static void resetAutomata() {
		entero = 0;
		acum = "";
		previo = 0;
		token = null;
		error = null;
		estado = 0;
	}

	public static int noLeer() {
		return previo;
	}

	public static Integer error() {
		return error;
	}

	public static Token automata(int c) {

		switch (estado) {
		case 0:
			estado0(c);
			break;
		case 2:
			estado2(c);
			break;
		case 6:
			estado6(c);
			break;
		case 10:
			estado10(c);
			break;
		case 13:
			estado13(c);
			break;
		case 16:
			estado16(c);
			break;
		case 22:
			estado22(c);
			break;
		case 24:
			estado24(c);
			break;
		case 26:
			estado26(c);
			break;
		case 27:
			estado27(c);
			break;
		case 30:
			estado30(c);
			break;
		case 31:
			estado31(c);
			break;
		case 33:
			estado33(c);
			break;
		case 35:
			estado35(c);
			break;
		case 36:
			estado36(c);
			break;
		case 37:
			estado37(c);
			break;
		default:
			break;
		}

		return token;

	}

	private static void estado0(int c) {
		if (c == '\n' || c == ' ' || c == '\t' || c == '\r') {
			return;
		} else if (c == -1) {
			token = new General("");//EOF
		} else if (c == '+') {
			estado = 2;
			acum = "+";
		} else if (c == '-') {
			estado = 6;
			acum = "-";
		} else if (c == '*') {
			estado = 10;
			acum = "*";
		} else if (c == '=') {
			estado = 13;
			acum = "=";
		} else if (c == '!') {
			estado = 16;
			acum = "!";
		} else if (c == '(') { // GENERAR
			token = new General("(");
		} else if (c == ')') { // GENERAR
			token = new General(")");
		} else if (c == '{') {
			token = new General("{");
		} else if (c == '}') {
			token = new General("}");
		} else if (c == '<') { // GENERAR
			token = new Operador("<");
		} else if (c == '>') {// GENERAR
			token = new Operador(">");
		} else if (c == ';') {
			token = new General(";");
		} else if (c == ',') {
			token = new General(",");
		} else if (c == '"') {
			estado = 24;
			acum = "\"";
		} else if (c == '/') {
			estado = 27;
			acum = "/";
		} else if (Character.isDigit(c)) {
			entero = Character.getNumericValue(c);
			estado = 22;
		} else if (Character.isAlphabetic(c)) {
			acum += Character.toString((char) c);
			estado = 33;
		} else if (c == '&') {
			estado = 36;
			acum = "&";
		} else if (c == '|') {
			estado = 37;
			acum = "|";
		} else {
			error=0;
		}
	}

	private static void estado2(int c) {
		if (c == '+') {
			acum += "+";
		} else if (c == '=') {
			acum += "=";
		} else {
			previo = c;
		}
		token = new Operador(acum);
	}

	private static void estado6(int c) {
		if (c == '-') {
			acum += "-";
		} else if (c == '=') {
			acum += "=";
		} else {
			previo = c;
		}
		token = new Operador(acum);
	}

	private static void estado10(int c) {
		if (c == '=') {
			acum += "=";
		} else {
			previo = c;
		}
		token = new Operador(acum);
	}

	private static void estado13(int c) {
		if (c == '=') {
			acum += "=";
		} else {
			previo = c;
		}
		token = new Operador(acum);
	}

	private static void estado16(int c) {
		if (c == '=') {
			acum += "=";
			token = new Operador(acum);
		} else {
			error = 16;
		}
	}

	private static void estado22(int c) {
		if (Character.isDigit(c)) {
			entero = entero * 10 + Character.getNumericValue(c);
		} else if (entero <= Math.pow(2, 15)) {
			token = new ConstanteEntero(entero);
			previo = c;
		} else {
			error = 22;
		}
	}

	private static void estado24(int c) {
		if (c == '"') {
			acum += '"';
			token = new ConstanteCadena(acum);
		} else if (c == '\\') {
			acum += Character.toString((char) c);
			estado = 26;
		} else if (c == 10 | c == 13) {
//			acum += "\\";
//			acum += "n";
		} else if (c != -1) {
			acum += Character.toString((char) c);
		} else {
			error = 24;
		}
	}

	private static void estado26(int c) {
		if (c == 'n') {
			acum += 'n';
			estado = 24;
		} else if (c == 't') {
			acum += 't';
			estado = 24;
		} else if (c == '"') {
			acum += '"';
			estado = 24;
		} else if (c == '\'') {
			acum += '\'';
			estado = 24;
		} else if (c == '\\') {
			acum += '\\';
			estado = 24;
		} else {
			error = 26;
		}
	}

	private static void estado27(int c) {
		if (c == '=') {
			acum += "=";
			token = new Operador(acum);
		} else if (c == '*') {
			acum += "*";
			estado = 30;
		} else {
			token = new Operador(acum);
			previo = c;
		}
	}

	private static void estado30(int c) {
		if (c == '*') {
			acum += "*";
			estado = 31;
		} else if (c != -1) {
			acum += Character.toString((char) c);
		} else {
			error = 30;
		}
	}

	private static void estado31(int c) {
		if (c == '/') {
			acum+="/";
			resetAutomata();
		} else if (c != -1) {
			acum += Character.toString((char) c);
			estado = 30;
		} else {
			error = 31;
		}
	}

	private static void estado33(int c) {
		if (Character.isDigit(c) || c == '_') {
			acum += Character.toString((char) c);
			estado = 35;
		} else if (Character.isLetter(c)) {
			acum += Character.toString((char) c);
		} else {
			if (TablaPalabrasReservadas.getEntrada(acum) != null) {// se encuentra, es valor
				token = new PalabraReservada(acum);
			} else {
				Identificador id = new Identificador(acum);
				Integer pos = tablaSimbolos.getPos(id);
				if (pos != null) {
					token = tablaSimbolos.getIdentificador(pos);
				} else {
					token = tablaSimbolos.anadirEntrada(id);
				}
			}
			previo = c;
		}

	}

	private static void estado35(int c) {
		if (Character.isDigit(c) || Character.isLetter(c) || c == '_') {
			acum += Character.toString((char) c);
		} else {
			Identificador id = new Identificador(acum);
			Integer pos = tablaSimbolos.getPos(id);
			if (pos != null) {
				token = tablaSimbolos.getIdentificador(pos);
			} else {
				token = tablaSimbolos.anadirEntrada(id);
			}
			previo = c;
		}
	}

	private static void estado36(int c) {
		if (c == '&') {
			acum += "&";
			token = new Operador(acum);
		} else {
			error = 36;
		}
	}

	private static void estado37(int c) {
		if (c == '|') {
			acum += "|";
			token = new Operador(acum);
		} else {
			error = 37;
		}
	}

}
