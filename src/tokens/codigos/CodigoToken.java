package tokens.codigos;

public class CodigoToken {
	
	private static Integer inicial = 1;
	
	///// CONSTANTES //////
	public static final Integer COD_ENTERO = inicial++;
	public static final String MSG_ENTERO = " // Entero";
	public static final Integer COD_BOOLEAN = inicial++;
	public static final String MSG_BOOLEAN = " // L�gico";
	public static final Integer COD_CADENA = inicial++;
	public static final String MSG_CADENA = " // Cadena";
	
	///// OPERADORES //////	
		////// ARITMETICOS //////
	public static final Integer COD_OP_SUMA = inicial++;
	public static final String MSG_SUMA = " // Suma";
	public static final Integer COD_OP_RESTA = inicial++;
	public static final String MSG_RESTA = " // Resta";
	public static final Integer COD_OP_MUL = inicial++;
	public static final String MSG_MUL = " // Multiplicaci�n";
	public static final Integer COD_OP_DIV = inicial++;
	public static final String MSG_DIV = " // Divisi�n";
		////// LOGICOS //////
	public static final Integer COD_OP_AND = inicial++;
	public static final String MSG_AND = " // &&";
	public static final Integer COD_OP_OR = inicial++;
	public static final String MSG_OR = " // ||";
		////// RELACIONALES //////
	public static final Integer COD_OP_IGUAL = inicial++;
	public static final String MSG_IGUAL = " // Igualdad";
	public static final Integer COD_OP_DESIGUAL = inicial++;
	public static final String MSG_DESIGUAL = " // Desigualdad";
	public static final Integer COD_OP_MENORQUE = inicial++;
	public static final String MSG_MENORQUE = " // Menor que";
	public static final Integer COD_OP_MAYORQUE = inicial++;
	public static final String MSG_MAYORQUE = " // Mayor que";
		////// INCREMENTO/DECREMENTO //////
	public static final Integer COD_OP_INC = inicial++;
	public static final String MSG_INC = " // Incremento";
	public static final Integer COD_OP_DEC = inicial++;
	public static final String MSG_DEC = " // Decremento";
		////// ASIGNACION //////
	public static final Integer COD_OP_ASIG = inicial++;
	public static final String MSG_ASIG = " // Asignaci�n";
	public static final Integer COD_OP_ASIGINC = inicial++;
	public static final String MSG_ASIGINC = " // Asignaci�n con incremento";
	public static final Integer COD_OP_ASIGDEC = inicial++;
	public static final String MSG_ASIGDEC = " // Asignaci�n con decremento";
	public static final Integer COD_OP_ASIGMUL = inicial++;
	public static final String MSG_ASIGMUL = " // Asignaci�n con multiplicaci�n";
	public static final Integer COD_OP_ASIGDIV = inicial++;
	public static final String MSG_ASIGDIV = " // Asignaci�n con divisi�n";
	
	///// IDENTIFICADOR /////
	public static final Integer COD_IDENTIFICADOR = inicial++;
	public static final String MSG_IDENTIFICADOR = " // Identificador";
	
	///// ENTRADA/SALIDA /////
	public static final Integer PRINT = inicial++;
	public static final String MSG_PRINT = " // Print";
	public static final Integer PROMPT = inicial++;
	public static final String MSG_PROMPT = " // Prompt";
	
	///// EOF //////
	public static final Integer COD_EOF = inicial++;
	public static final String MSG_EOF = " // Fin de fichero";
	
	///// PARENTESIS //////
	public static final Integer COD_PAR_ABI = inicial++;
	public static final String MSG_PAR_ABI = " // Par�ntesis abierto";
	public static final Integer COD_PAR_CERR = inicial++;
	public static final String MSG_PAR_CERR = " // Par�ntesis cerrado";
	
	///// CORCHETES ///////
	public static final Integer COD_COR_AP = inicial++;
	public static final String MSG_COR_AP = " // Corchete apertura";
	public static final Integer COD_COR_CERR = inicial++;
	public static final String MSG_COR_CERR = " // Corchete cierre";
	
	///// LLAVES //////
	public static final Integer COD_LLAVE_AP = inicial++;
	public static final String MSG_LLAVE_AP = " // Llave apertura";
	public static final Integer COD_LLAVE_CERR = inicial++;
	public static final String MSG_LLAVE_CERR = " // Llave cierre";
	
	///// PUNTUACION //////
	public static final Integer COD_PUNTOCOMA = inicial++;
	public static final String MSG_PUNTOCOMA = " // Punto y coma";
	public static final Integer COD_DOSPUNTOS = inicial++;
	public static final String MSG_DOSPUNTOS = " // Dos puntos";
	public static final Integer COD_COMA = inicial++;	
	public static final String MSG_COMA = " // Coma";
	
	///// PALABRA RESERVADA //////
	public static final Integer COD_PALRES = inicial++;
	public static final String MSG_PALRES = " // Palabra reservada";
	
	///// FUNCION //////
	//public static final Integer COD_FUNCION = 4;
}
