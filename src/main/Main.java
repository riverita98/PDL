package main;

public class Main {

	public static void main(String[] args) {
		AnalizadorSintactico as = new AnalizadorSintactico("prueba3.txt", "1");
		
		as.init();
		String parse = "Des ";
		for(int p=0;p<as.parse.size();p++)
			parse+= as.parse.get(p)+" ";
			
		as.lexico.tablaTokens.exportarTabla("tablaTokens.txt");		
		System.out.print(parse);
	}

}
