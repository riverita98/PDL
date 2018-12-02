package tokens.constantes;

import tablas.tablasDinamicas.TablaTokens;
import tokens.Token;
import tokens.codigos.CodigoToken;

public class ConstanteCadena implements Token {

	private Integer codigo = CodigoToken.COD_CADENA;
	private String valor;

	public ConstanteCadena(String valor) {
		this.setValor(valor);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String toString() {
		return "<" + this.codigo + ", " + this.valor + ">" + CodigoToken.MSG_CADENA;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.valor;
	}
}
