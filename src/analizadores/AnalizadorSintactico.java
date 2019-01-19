package analizadores;

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
import tokens.PalabraReservada;
import tokens.Token;
import tokens.codigos.CodigoToken;
import tokens.constantes.ConstanteCadena;
import tokens.constantes.ConstanteEntero;
import tokens.constantes.ConstanteLogica;

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
	
	public void init() {
		this.pedirToken();		
		P();
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
		return token !=null && token instanceof Identificador;
	}
	private boolean esEnt() {
		return token !=null && token instanceof ConstanteEntero;
	}
	private boolean esCad() {
		return token !=null && token instanceof ConstanteCadena;
	}
	
	private boolean esLog() {
		return token !=null && token instanceof PalabraReservada && (token.getValorToken().equals("true") || token.getValorToken().equals("false"));
	}
	
	private boolean esEOF() {
		return token !=null && token instanceof General && token.getValorToken().equals("");
	}


	//--------------------------------------------------------------	
	private void P() {
		if(FirstFollow.FIRST_B.contains(sigT) || this.esId()) {
			this.parse.add(1);
			B();
			P();
		}else if(FirstFollow.FIRST_F.contains(sigT)) {
			this.parse.add(2);
			F();
			P();
		}else if(this.esEOF()) {
			this.parse.add(3);
		} //EOF. HAY QUE DISTINGUIRLO		
	}

	private void B() {
		if (sigT.equals("var")) {
			this.parse.add(4);
			equipara("var");
			T();
			L();
		} else if (sigT.equals("if")) {
			this.parse.add(5);
			equipara("if");
			equipara("(");
			E();
			equipara(")");
			Xp();
		} else if (sigT.equals("do")) {
			this.parse.add(6);
			equipara("do");
			equipara("{");
			C();
			equipara("}");
			equipara("while");
			E();	
			equipara(";");
		} else if (sigT.equals("while")) {
			this.parse.add(7);
			equipara("while");
			equipara("(");
			E();
			equipara(")");
			equipara("{");
			C();
			equipara("}");			
		} else if (FirstFollow.FIRST_S.contains(sigT) || this.esId()) {
			this.parse.add(8);
			S();
		} else {
			this.error();
		}
	}

	// --------------------------------------------------------------

	private void T() {
		if (sigT.equals("int")) {
			this.parse.add(9);
			equipara("int");
		} else if (sigT.equals("bool")) {
			this.parse.add(10);
			equipara("bool");
		} else if (sigT.equals("string")) {
			this.parse.add(11);
			equipara("string");
		}
	}

	private void L() {
		if(this.esId()) {
			this.parse.add(12);
			this.pedirToken();
			X();
			this.equipara(";");
		}else {
			this.error();
		}
	}

	private void X() {
		if(sigT.equals(",")) {
			this.parse.add(14);
			this.equipara(",");
			L();
		} else if(sigT.equals("=")) {
			this.parse.add(15);
			this.equipara("=");
			E();
			Y();
		}
		else if(FirstFollow.FOLLOW_X.contains(sigT)) {
			this.parse.add(13);
		}else {
			this.error();
		}
	}

	private void Y() {
		if (sigT.equals(",")) {
			this.parse.add(17);
			equipara(",");
			L();
		}
		else if(FirstFollow.FOLLOW_Y.contains(sigT)) {
			this.parse.add(16);
		}
		else {
			this.error();
		}
	}

	private void Xp() {
		if(FirstFollow.FIRST_S.contains(sigT) || this.esId()) {
			this.parse.add(18);
			S();
		}else if(sigT.equals("{")) {
			this.parse.add(19);
			this.equipara("{");
			C();
			this.equipara("}");
			Z();
		}else {
			this.error();
		}
	}

	private void Z() {
		if(sigT.equals("else")) {
			this.parse.add(21);
			this.equipara("else");
			this.equipara("{");
			C();
			this.equipara("}");
		}else if(FirstFollow.FOLLOW_Z.contains(sigT) || this.esId() || this.esEOF()) {
			this.parse.add(20);
			
		}else {
			this.error();
		}
	}

	private void S() {
		if(sigT.equals("print")) {
			this.parse.add(22);
			this.equipara("print");
			this.equipara("(");
			A();
			this.equipara(")");
			this.equipara(";");
		}else if(sigT.equals("prompt")) {
			this.parse.add(23);
			this.equipara("prompt");
			this.equipara("(");
			if(this.esId()) {
				this.pedirToken();
			}else {
				this.error();
			}
			this.equipara(")");
			this.equipara(";");
		}else if(sigT.equals("return")) {
			this.parse.add(24);
			this.equipara("return");
			G();
			this.equipara(";");
		}else if(this.esId()) {
			this.parse.add(25);			
			this.pedirToken();		
			H();
		}else {
			this.error();
		}
	}

	private void A() {
		if(FirstFollow.FIRST_E.contains(sigT) || this.esId() || this.esEnt() || this.esCad()  || this.esLog() ) {
			this.parse.add(26);
			E();
			I();
		}
		else{
			this.error();
		}
	}

	private void I() {
		if(sigT.equals(",")) {
			this.parse.add(27);
			this.equipara(",");
			E();
			I();
		}
		else if(FirstFollow.FOLLOW_I.contains(sigT)) {
			this.parse.add(28);			
		}else {
			this.error();
		}
	}

	private void G() {
		if(FirstFollow.FIRST_E.contains(sigT) || this.esId() || this.esEnt() || this.esCad()  || this.esLog() ) {
			this.parse.add(29);
			E();
		}else if(FirstFollow.FOLLOW_G.contains(sigT)) {
			this.parse.add(30);			
		}else {
			this.error();
		}
	}

	private void H() {
		if(sigT.equals("(")) {
			this.parse.add(32);
			this.equipara("(");
			J();
			this.equipara(")");
			this.equipara(";");
		}
		else if(FirstFollow.FIRST_D.contains(sigT)) {
			this.parse.add(31);
			D();
			E();
			this.equipara(";");
		}else {
			this.error();
		}
	}

	private void J() {
		if((FirstFollow.FIRST_E.contains(sigT)) || this.esId() || this.esEnt() || this.esLog()  || this.esCad()) {
			this.parse.add(33);
			E();
			I();
		}
		else if(FirstFollow.FOLLOW_J.contains(sigT)) {
			this.parse.add(34);		
		}else {
			this.error();
		}
	}

	private void D() {
		if(sigT.equals("=")) {
			this.parse.add(35);
			this.equipara("=");			
		}else if(sigT.equals("+=")) {
			this.parse.add(36);
			this.equipara("+=");
		}else if(sigT.equals("-=")) {
			this.parse.add(37);
			this.equipara("-=");
		}else if(sigT.equals("|=")) {
			this.parse.add(38);
			this.equipara("|=");
		}else {
			this.error();
		}
	}

	private void F() {
		if(sigT.equals("function")) {
			this.parse.add(39);
			this.equipara("function");
			K();
			if(this.esId()) {
				this.pedirToken();
			}else {
				this.error();
			}
			this.equipara("(");
			M();
			this.equipara(")");
			this.equipara("{");
			C();
			this.equipara("}");
		}else {
			this.error();
		}

	}

	private void K() {
		if(FirstFollow.FIRST_T.contains(sigT)) {
			this.parse.add(40);
			T();
		}else if(this.esId()) {
			this.parse.add(41);
		}else {
			this.error();
		}
	}

	private void M() {
		if(FirstFollow.FIRST_T.contains(sigT)) {
			this.parse.add(42);
			T();
			if(this.esId()) {
				this.pedirToken();
			}else {
				this.error();
			}
			N();			
		}else if(FirstFollow.FOLLOW_M.contains(sigT) ) {
			this.parse.add(43);
		}else {
			this.error();
		}
	}

	private void N() {
		if(sigT.equals(",")) {
			this.parse.add(44);
			this.equipara(",");
			T();
			if(this.esId()) {
				this.pedirToken();
			}else {
				this.error();
			}
			N();
		}else if(FirstFollow.FOLLOW_N.contains(sigT)) {
			this.parse.add(45);
		}else {
			this.error();
		}
	}

	private void C() {
		if(FirstFollow.FIRST_B.contains(sigT) || this.esId()) {
			this.parse.add(46);
			B();
			C();
		}else if(FirstFollow.FOLLOW_C.contains(sigT)) {
			this.parse.add(47);
		}else {
			this.error();
		}
	}

	private void E() {
		if(FirstFollow.FIRST_O.contains(sigT) || this.esEnt() || this.esId() || this.esLog()  || this.esCad()) {
			this.parse.add(48);
			O();
			Ep();
		}else{
			error();
		}
	}

	//E'
	private void Ep() {
		if(sigT.equals("||")) {
			this.parse.add(49);
			equipara("||");
			O();
			Ep();
		}
		else if(FirstFollow.FOLLOW_Ep.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(50);		
		}
		else{
			error();
		}
	}

	private void O() {
		if(FirstFollow.FIRST_Pp.contains(sigT) || this.esEnt() || this.esId() || this.esLog()  || this.esCad()) {
			this.parse.add(51);
			Pp();
			Op();
		}else{
			error();
		}
	}
	
	//O'
	private void Op() {
		if(sigT.equals("&&")) {
			this.parse.add(52);
			this.equipara("&&");
			Pp();
			Op();
		}
		else if(FirstFollow.FOLLOW_Op.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(53);			
		}		
		else{
			error();
		}
	}

	//P'
	private void Pp() {
		if(FirstFollow.FIRST_Q.contains(sigT)|| this.esEnt() || this.esCad() || this.esLog()  || this.esId()) {
			this.parse.add(54);
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
			this.parse.add(55);
			this.equipara("==");
			Q();
			Ppp();
		}
		else if(sigT.equals("!=")) {
			this.parse.add(56);
			this.equipara("!=");
			Q();
			Ppp();
		}
		
		else if(FirstFollow.FOLLOW_Ppp.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(57);			
		}
		else{
			this.error();
		}
	}

	private void Q() {
		if(FirstFollow.FIRST_R.contains(sigT) || this.esEnt() || this.esCad() || this.esLog()  || this.esId() ) {
			this.parse.add(58);
			R();
			Qp();
		}
		else{
			this.error();
		}
	}
	
	//Q'
	private void Qp() {
		if(sigT.equals(">")) {
			this.parse.add(59);
			this.equipara(">");
			R();
			Qp();
		}
		else if(sigT.equals("<")) {
			this.parse.add(60);
			this.equipara("<");
			R();
			Qp();
		}
		else if(FirstFollow.FOLLOW_Qp.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(61);			
		}else{
			this.error();
		}
	}

	private void R() {
		if(FirstFollow.FIRST_Sp.contains(sigT) || this.esEnt() || this.esCad() || this.esLog()  || this.esId() ) {
			this.parse.add(62);
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
			this.parse.add(63);
			this.equipara("+");
			Sp();
			Rp();
		}
		else if(sigT.equals("-")) {
			this.parse.add(64);
			this.equipara("-");
			Sp();
			Rp();
		}else if(FirstFollow.FOLLOW_Rp.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(65);		
		}else{
			this.error();
		}
	}

	//S'
	private void Sp() {
		if(FirstFollow.FIRST_U.contains(sigT)|| this.esEnt() || this.esCad() || this.esLog() || this.esId()) {
			this.parse.add(66);
			U();
			Spp();
		}
		else{
			this.error();
		}
	}

	//S''
	private void Spp() {
		if(sigT.equals("*")) {
			this.parse.add(67);
			this.equipara("*");
			U();
			Spp();
		}
		else if(sigT.equals("/")) {
			this.parse.add(68);
			this.equipara("/");
			U();
			Spp();
		}
		else if(FirstFollow.FOLLOW_Spp.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(69);		
		}	
		else{
			this.error();
		}
	}

	private void U() {
		if(sigT.equals("+")) {
			this.parse.add(70);
			this.equipara("+");
			V();
			Up();
		}
		else if(sigT.equals("-")) {
			this.parse.add(71);
			this.equipara("-");
			V();
			Up();
		}
		else if(sigT.equals("!")) {
			this.parse.add(72);
			this.equipara("!");
			V();
			Up();
		}
		else if(FirstFollow.FIRST_V.contains(sigT)|| this.esEnt() || this.esCad() || this.esLog()|| this.esId()){
			this.parse.add(73);
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
			this.parse.add(74);
			this.equipara("++");
			Up();
		}
		else if(sigT.equals("--")) {
			this.parse.add(75);
			this.equipara("--");
			Up();
		}
		else if(FirstFollow.FOLLOW_Up.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(76);			
		}
		else{
			this.error();
		}
	}

	private void V() {
		if(sigT.equals("(")) {
			this.parse.add(77);
			this.equipara("(");
			E();
			this.equipara(")");
		}
		else if(this.esId()) {
			this.parse.add(78);
			this.pedirToken();
			Vp();
		} 
		else if(this.esEnt()){
			this.parse.add(79);
			this.pedirToken();
		}
		else if(this.esCad()){
			this.parse.add(80);
			this.pedirToken();
		}else if(this.esLog()) {//anadido
			this.parse.add(81);
			this.pedirToken();
		}
		else{
			this.error();
		}
	}
	
	//V'
	private void Vp() {
		if(sigT.equals("(")) {
			this.parse.add(82);
			this.equipara("(");
			J();//L
			this.equipara(")");
		} 
		else if(FirstFollow.FOLLOW_Vp.contains(sigT) || this.esId() || this.esEOF()){
			this.parse.add(83);			
		}
		else{
			this.error();
		}
	}
	

	private String error() {//UNA EXCEPCION
		return "";
	}

	
}
