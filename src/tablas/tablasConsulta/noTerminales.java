package tablas.tablasConsulta;

import java.util.ArrayList;

public class noTerminales {

	public static String[] FIRST_B_list = { "var", "if", "do", "while" };
	public static String[] FIRST_T_list = { "int", "bool", "String" };
	public static String[] FIRST_L = { "id" };
	public static String[] FIRST_X = { ";", ",", "=" };
	public static String[] FIRST_Y = { ";", "," };
	public static String[] FIRST_S_list = { "print", "prompt", "return", "id" }; // falta meter id bien
	public static String[] FIRST_D = { "print", "prompt", "return", "id", "{" };
	public static String[] FIRST_F_list = { "else" };
	public static String[] FIRST_E = { ";" };
	public static String[] FIRST_H = { "=", "+=", "-=", "!=", "(" };
	public static String[] FIRST_J = { ")" };
	public static String[] FIRST_I = { ",", ")" };
	public static String[] FIRST_M = { "int", "bool", "String", ")" };
	public static String[] FIRST_K = { "int", "bool", "String", "id" };
	public static String[] FIRST_N = { ",", ")" };
	public static String[] FIRST_G = { ";" };

	public static ArrayList<String> FIRST_P;
	public static ArrayList<String> FIRST_B;
	public static ArrayList<String> FIRST_F;
	public static ArrayList<String> FIRST_S;
	public static ArrayList<String> FIRST_T;

	private void crear() {

		// Primero los que solo tienen FIRST de terminales
		// S
		addFirstPropio(FIRST_S, FIRST_S_list);
		// T
		addFirstPropio(FIRST_T, FIRST_T_list);

		// Despues lo que tienen FIRST de otros no terminales
		// P
		addFirstOtro(FIRST_P, FIRST_B);
		addFirstOtro(FIRST_P, FIRST_F);

		// B
		addFirstPropio(FIRST_B, FIRST_B_list);// terminales de B
		addFirstOtro(FIRST_B, FIRST_S);// first de S en B

		// T
		addFirstPropio(FIRST_T, FIRST_T_list);
	}

	// añade FIRST de otro no Terminal
	private void addFirstOtro(ArrayList<String> destino, ArrayList<String> origen) {
		for (String s : origen) {
			destino.add(s);
		}
	}

	// añade los terminales del FIRST del propio simbolo
	private void addFirstPropio(ArrayList<String> destino, String[] origen) {
		for (String s : origen) {
			destino.add(s);
		}
	}
}
