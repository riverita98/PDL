package escritor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escritor {

	private String file;
	private FileWriter fw;
	private BufferedWriter bw;
	
	public Escritor(String pathFile) {
		try {
			this.file = "./resources/"+pathFile;
			fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void escribir(String datos) {
		try {
			this.bw.write(datos+"\r");
			this.bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrar() {

	}
	
}
