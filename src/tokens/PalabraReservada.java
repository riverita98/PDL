package tokens;

import tablas.tablasConsulta.TablaPalabrasReservadas;
import tablas.tablasDinamicas.TablaTokens;
import tokens.codigos.CodigoToken;
import utils.Pair;

public class PalabraReservada implements Token {

	private Integer codigo = CodigoToken.COD_PALRES;
	private Integer entrada;
	private String mensaje;
	private String valor;

	public PalabraReservada(String valor) {
		this.valor = valor;
		Pair<Integer, String> entrada = TablaPalabrasReservadas.getEntrada(valor);
		if (entrada != null) {
			this.entrada = entrada.getKey();
			this.mensaje = entrada.getValue();
		}
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Integer getEntrada() {
		return entrada;
	}

	public String toString() {
		return "<" + this.codigo + ", " + this.entrada + ">" + this.mensaje;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.valor;
	}
}
