package errores;

import tokens.codigos.CodigoError;

public class Error {

	private int codigo;
	private String mensaje;
	private int estadoError; // en que estado se produjo el error
	private int lineaError;

	public Error(int estadoError, int lineaError) {
		this.estadoError = estadoError;
		this.lineaError = lineaError;
		this.setAtributosError(estadoError);
	}

	private void setAtributosError(int estadoError) {
		switch (estadoError) {
		case 0:
			this.codigo = CodigoError.COD_0;
			this.mensaje = CodigoError.MSG_0;
			break;
		case 16:
			this.codigo = CodigoError.COD_16;
			this.mensaje = CodigoError.MSG_16;
			break;
		case 22:
			this.codigo = CodigoError.COD_22;
			this.mensaje = CodigoError.MSG_22;
			break;
		case 24:
			this.codigo = CodigoError.COD_24;
			this.mensaje = CodigoError.MSG_24;
			break;
		case 26:
			this.codigo = CodigoError.COD_26;
			this.mensaje = CodigoError.MSG_26;
			break;
		case 30:
			this.codigo = CodigoError.COD_30;
			this.mensaje = CodigoError.MSG_30;
			break;
		case 31:
			this.codigo = CodigoError.COD_30;
			this.mensaje = CodigoError.MSG_30;
			break;
		case 36:
			this.codigo = CodigoError.COD_36;
			this.mensaje = CodigoError.MSG_36;
			break;
		case 37:
			this.codigo = CodigoError.COD_37;
			this.mensaje = CodigoError.MSG_37;
			break;
		}
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
	
	public String toString() {
		return "Linea " + this.lineaError + ". " + this.mensaje;
	}
	
}
