package main;

import javax.swing.SpringLayout.Constraints;
import tablas.tablasConsulta.noTerminales;

public class AnalizadorSemantico {

	 private String sigT;
	 
//--------------------------------------------------------------	
	private void B(){
		if(!contains(noTerminales.FIRST_B)){
			//ERROR
		}
		if(sigT.equals("val")){
			equipara("val");
			T();
			L();
		}
		else if(sigT.equals("id")){
			equipara("id");
			equipara("{");
			C();
			equipara("}");
			equipara("while");
			E();
		}
		else if(sigT.equals("while")){
			equipara("while");
			equipara("(");
			E();
			equipara(")");
			equipara("{");
			C();
			equipara("}");
		}
	}
	
	//--------------------------------------------------------------
	 
	private void T(){
		if(!contains(noTerminales.FIRST_T)){
			//ERROR
		}
		if(sigT.equals("int")){
			equipara("int");
		}
		else if(sigT.equals("bool")){
			equipara("bool");
		}
		else if(sigT.equals("String")){
			equipara("String");
		}
	}
	
	//--------------------------------------------------------------
	
	private void L(){
		if(!contains(noTerminales.FIRST_L)){
			//ERROR
		}
		if(sigT.equals("id")){
			equipara("id");
			X();
		}
	}
	
	//--------------------------------------------------------------
	
	private void X(){
		if(!contains(noTerminales.FIRST_X)){
			//ERROR
		}
		if(sigT.equals(";")){
			equipara(";");
		}
		else if(sigT.equals(",")){
			equipara(",");
			equipara("id");
			X();
		}
		else if(sigT.equals("=")){
			equipara("=");
			E();
			Y();
		}
	}
	
	//--------------------------------------------------------------
	
	private void Y(){
		if(!contains(noTerminales.FIRST_Y)){
			//ERROR
		}
		if(sigT.equals(";")){
			equipara(";");
		}
		else if(sigT.equals(",")){
			equipara(",");
			L();
		}
	}
	
	//--------------------------------------------------------------
	
	
	private void D(){
		if(!contains(noTerminales.FIRST_D)){
			//ERROR
		}
		if(sigT.equals("{")){
			equipara("{");
			C();
			equipara("}");
			F();
		}
		else { //ya estoy comprobando previamente que sigT==(print || prompt || return ||id)
			
			S();
		}
	}
	
	//--------------------------------------------------------------
	
	private void F(){
		if(!contains(noTerminales.FIRST_F)){
			//ERROR
		}
		if(sigT.equals("else")){
			equipara("else");
			equipara("{");
			C();
			equipara("}");
		}
		else if(contains(noTerminales.FOLLOW_F)){ //follows no hechos aun
			//como tiene prod lambda compruebo el follow de F
			//No hace nada
		}
		else{
			//tratar error
		}
			
	}
	
	//--------------------------------------------------------------
	
	
	public static void main(String[] args) {
		
	}
	
	
	
	/*
	 * DEDE COMPARAR EL TOKEN ACTUAL CON EL PREVISTO QUE SEA
	 * SE AVANZA AL SIGUIENTE TOKEN DEJANDO EL LEXEMA DEL TOKEN EN sigT  >>>>
	 *   >>> sigT = nextToken().getLex();     //Ejemplo pseudocodigo
	 */
	@SuppressWarnings("unused")
	private void equipara(String s){
		//to do
	}
	
	private  boolean contains(String[]first){
		int p;
		for (p=0;p<first.length && !first[p].equals(sigT);p++);
		return p==first.length;
	}
}
