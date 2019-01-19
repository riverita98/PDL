package main;

import java.util.ArrayList;

import simbolos.FirstFollow;
import tablas.tablasConsulta.TablaOperadores;
import tablas.tablasConsulta.TablaPalabrasReservadas;
import tablas.tablasConsulta.TablaPuntuacion;
import tablas.tablasConsulta.noTerminales;
import tablas.tablasDinamicas.TablaErrores;
import tablas.tablasDinamicas.TablaSimbolos;
import tablas.tablasDinamicas.TablaTokens;
import tokens.General;
import tokens.Identificador;
import tokens.Token;
import tokens.codigos.CodigoToken;
import tokens.constantes.ConstanteCadena;
import tokens.constantes.ConstanteEntero;

public class AnalizadorSintactico {

	private String sigT;
	private Token token;
	public AnalizadorLexico lexico;
	public ArrayList<Integer> parse;

	public AnalizadorSintactico(String file, String nombreTS) {
		parse = new ArrayList<Integer>();
		lexico = new AnalizadorLexico(file);

		TablaSimbolos tablaSimbolos = new TablaSimbolos(nombreTS);
		TablaTokens tablaTokens = new TablaTokens();
		TablaErrores tablaErrores = new TablaErrores();

		// Tablas de consulta
		TablaOperadores.crearTabla();
		TablaPalabrasReservadas.crearTabla();
		TablaPuntuacion.crearTabla();

		// Tablas dinamicas
		lexico.setTablaSimbolos(tablaSimbolos);
		lexico.setTablaTokens(tablaTokens);
		lexico.setTablaErrores(tablaErrores);
		FirstFollow.init();
		

	}

	private void pedirToken() {
		token = lexico.generaToken();
		
			
		this.getToken(); //para poder leer el valor
	}

	private void getToken() {
		if (token instanceof ConstanteEntero) {
			sigT = "" + token.getValorToken();
		} else {
			sigT = (String) token.getValorToken();
		}
	}

	public void setSiguienteToken(String s) {
		this.sigT = s;
	}


	private void equipara(String s) {
		if (this.sigT != null && this.sigT.equals(s)) {
			this.pedirToken();
		} else
			this.error();
	}

	private boolean esId() {
		if(token !=null && token instanceof Identificador) {
			return true;
		}
		return false;
	}
	private boolean esEnt() {
		if(token !=null && token instanceof ConstanteEntero) {
			return true;
		}
		return false;
	}
	private boolean esCad() {
		if(token !=null && token instanceof ConstanteCadena) {
			return true;
		}
		return false;
	}


	//--------------------------------------------------------------	
	private void P() {
		this.pedirToken();
		if(FirstFollow.FIRST_B.contains(sigT)) {
			this.parse.add(1);
			B();
			P();
		}else if(FirstFollow.FIRST_F.contains(sigT)) {
			this.parse.add(2);
			F();
			P();
		}else if(1==1) {
			this.parse.add(3);
		} //EOF. HAY QUE DISTINGUIRLO		
	}

	private void B() {
		if (sigT.equals("var")) {
			this.parse.add(4);
			equipara("var");
			T();
			//ID
		} else if (sigT.equals("if")) {
			this.parse.add(5);
			equipara("if");
			equipara("(");
			E();
			equipara(")");
			S();
		} else if (sigT.equals("do")) {
			this.parse.add(6);
			equipara("do");
			equipara("{");
			C();
			equipara("}");
			equipara("while");
			equipara("(");
			equipara(")");
			E();			
		}  else if (FirstFollow.FIRST_S.contains(sigT)) {
			this.parse.add(7);
			S();
		} else {
			this.error();
		}
	}

	// --------------------------------------------------------------

	private void T() {
		if (sigT.equals("int")) {
			this.parse.add(8);
			equipara("int");
		} else if (sigT.equals("bool")) {
			this.parse.add(9);
			equipara("bool");
		} else if (sigT.equals("string")) {
			this.parse.add(10);
			equipara("string");
		}
	}
	

	private void S() {
		if(sigT.equals("print")) {
			this.parse.add(11);
			this.equipara("(");
			E();
			this.equipara(")");
			this.equipara(";");
		}else if(sigT.equals("prompt")) {
			this.parse.add(12);
			this.equipara("prompt");
			this.equipara("(");
			if(this.esId()) {
				this.parse.add(13);
				this.pedirToken();
			}
			this.equipara(")");
			this.equipara(";");
		}else if(sigT.equals("return")) {
			this.parse.add(14);
			G();
		}else if(this.esId()) {
			this.parse.add(15);
			if(this.esId()) {
				this.pedirToken();
			}else {
				this.error();
			}
			H();
		}
	}


	private void I() {
		if(sigT.equals(",")) {
			this.parse.add(16);
			this.equipara(",");
			E();
			I();
		}
		else if(FirstFollow.FOLLOW_I.contains(sigT)) {
			this.parse.add(17);
			
		}else {
			this.error();
		}
	}

	private void G() {
		if(FirstFollow.FIRST_G.contains(sigT)) {
			this.parse.add(18);
			E();
		}else if(FirstFollow.FOLLOW_G.contains(sigT)) {
			this.parse.add(19);
			
		}else {
			this.error();
		}
	}

	private void H() {
		if(sigT.equals("(")) {
			this.parse.add(20);
			this.equipara("(");
			J();
			this.equipara(")");
			this.equipara(";");
		}
		else if(sigT.equals("=")) {
			this.parse.add(21);
			this.equipara("=");
			E();
			this.equipara(";");
		}else {
			this.error();
		}
	}

	private void J() {
		if((FirstFollow.FIRST_J.contains(sigT))) {
			this.parse.add(23);
			E();
			I();
		}
		else if(FirstFollow.FOLLOW_J.contains(sigT)) {
			this.parse.add(24);
		
		}else {
			this.error();
		}
	}

	

	private void F() {
		if(sigT.equals("function")) {
			this.parse.add(25);
			this.equipara("function");
			K();
			//ID
			this.equipara("(");
			M();
			this.equipara(")");
			this.equipara("{");
			C();
			this.equipara("}");
			
		}
		else {
			this.error();
		}
	}

	private void K() {
		if((FirstFollow.FIRST_K.contains(sigT))) {
			this.parse.add(26);
			T();
		}
		else if(FirstFollow.FOLLOW_K.contains(sigT)) {
			this.parse.add(27);
		}
		else {
			this.error();
		}
		
	}

	private void M() {
		if((FirstFollow.FIRST_M.contains(sigT))) {
			this.parse.add(28);
			T();
			//ID
			N();
		}
		else if(FirstFollow.FOLLOW_M.contains(sigT)) {
			this.parse.add(29);	
		}
		else {
			this.error();
		}
	}

	private void N() {
		if(sigT.equals(",")) {
			this.parse.add(30);
			this.equipara(",");
			T();
			//ID
			N();
		}
		else if(FirstFollow.FOLLOW_N.contains(sigT)) {
			this.parse.add(31);
		}
		else {
			this.error();
		}
	}

	private void C() {
		if((FirstFollow.FIRST_C.contains(sigT))) {
			this.parse.add(32);
			B();
			C();
		}
		else if(FirstFollow.FOLLOW_C.contains(sigT)) {
			this.parse.add(33);
		}
		else {
			this.error();
		}
	}

	private void E() {
		if(FirstFollow.FIRST_E.contains(sigT) || this.esEnt() || this.esId()) {
			this.parse.add(34);
			Pp();
			Op();
		}else{
			error();
		}
	}

	

	
	
	//O'
	private void Op() {
		if(sigT.equals("&&")) {
			this.parse.add(35);
			Pp();
			Op();
		}
		else if(FirstFollow.FOLLOW_Op.contains(sigT)){
			this.parse.add(36);	
		}
		else{
			error();
		}
	}

	//P'
	private void Pp() {
		if(FirstFollow.FIRST_Pp.contains(sigT)|| this.esEnt() ) {
			this.parse.add(37);
			Q();
			Ppp();
		}
		else{
			error();
		}
	}

	//P''
	private void Ppp() {
		if(sigT.equals("==")) {
			this.parse.add(38);
			this.equipara("==");
			Q();
			Ppp();
		}
		else if(FirstFollow.FOLLOW_Ppp.contains(sigT)){
			this.parse.add(39);
		}
		else{
			this.error();
		}
	}

	private void Q() {
		if(FirstFollow.FIRST_Q.contains(sigT) || this.esEnt() ) {
			this.parse.add(40);
			Sp();
			Rp();
		}
		else{
			this.error();
		}
	}
	

	//R'
	private void Rp() {
		if(sigT.equals("+")) {
			this.parse.add(41);
			this.equipara("+");
			Sp();
			Rp();
		}
		else if(FirstFollow.FOLLOW_Rp.contains(sigT)){
			this.parse.add(42);
		}
		else{
			this.error();
		}
	}

	//S'
	private void Sp() {
		if(FirstFollow.FIRST_Sp.contains(sigT)|| this.esEnt() ) {
			this.parse.add(43);
			V();
			Up();
		}
		else{
			this.error();
		}
	}

	
	//U'
	private void Up() {
		if(sigT.equals("++")) {
			this.parse.add(44);
			this.equipara("++");
			Up();
		}
		else if(FirstFollow.FOLLOW_Up.contains(sigT)){
			this.parse.add(45);
		}
		else{
			this.error();
		}
	}

	private void V() {
		if(sigT.equals("(")) {
			this.parse.add(46);
			this.equipara("(");
			E();
			this.equipara(")");
		}
		else if(this.esId()) {
			this.parse.add(47);
			this.pedirToken();
			Vp();
		} 
		else if(this.esEnt()){
			this.parse.add(48);
			this.pedirToken();
		}
		else if(this.esCad()){
			this.parse.add(49);
			this.pedirToken();
		}
		else{
			this.error();
		}
	}
	
	//V'
	private void Vp() {
		if(sigT.equals("(")) {
			this.parse.add(50);
			this.equipara("(");
			J();
			this.equipara(")");
		}
		else if(FirstFollow.FOLLOW_Vp.contains(sigT)){
			this.parse.add(51);
		}
		else{
			this.error();
		}
	}
	
	// --------------------------------------------------------------



	// --------------------------------------------------------------



	// --------------------------------------------------------------




	// --------------------------------------------------------------



	// --------------------------------------------------------------

	/*
	 * DEDE COMPARAR EL TOKEN ACTUAL CON EL PREVISTO QUE SEA SE AVANZA AL SIGUIENTE
	 * TOKEN DEJANDO EL LEXEMA DEL TOKEN EN sigT >>>> >>> sigT =
	 * nextToken().getLex(); //Ejemplo pseudocodigo
	 */


	private String error() {//UNA EXCEPCION
		return "";
	}

	private boolean contains(ArrayList<String> first) {
		int p;
		for (p = 0; p < first.size() && !first.get(p).equals(sigT); p++)
			;
		return !(p == first.size());
	}
	public void init(){
		
		this.P();
	}
	
}
