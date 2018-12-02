package tokens.constantes;

import tablas.tablasDinamicas.TablaTokens;
import tokens.Token;
import tokens.codigos.CodigoToken;

public class ConstanteEntero implements Token {

	private Integer codigo = CodigoToken.COD_ENTERO;
	private Integer valor;

	public ConstanteEntero(Integer valor) {
		this.setValor(valor);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String toString() {
		return "<" + this.codigo + ", " + this.valor + ">" + CodigoToken.MSG_ENTERO;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.valor;
	}

}
