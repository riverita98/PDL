package lector;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;

public class Lector {

	private String file;
	private File openedFile;
	private RandomAccessFile randomFile;

	public Lector(String pathFile) {
		this.file = "./resources/"+pathFile;
		try {
			this.openedFile = new File(this.file);
			this.randomFile = new RandomAccessFile(this.openedFile,"r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int readChar() throws IOException {
		return this.randomFile.read();
	}
	
	public void seek(long bytes) {
		try {
			this.randomFile.seek(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
