package tokens.constantes;

import tablas.tablasDinamicas.TablaTokens;
import tokens.Token;
import tokens.codigos.CodigoToken;

public class ConstanteLogica implements Token {

	private Integer codigo = CodigoToken.COD_BOOLEAN;
	private boolean valor;

	public ConstanteLogica(boolean valor) {
		this.setValor(valor);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public boolean isValor() {
		return valor;
	}

	public void setValor(boolean valor) {
		this.valor = valor;
	}

	public String toString() {
		return "<" + this.codigo + ", " + this.valor + ">" + CodigoToken.MSG_BOOLEAN;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.valor;
	}
	
	
}
