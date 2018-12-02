package tokens;

import tablas.tablasConsulta.TablaPuntuacion;
import tablas.tablasDinamicas.TablaTokens;
import utils.Pair;

public class General implements Token {

	private Integer codigo;
	private String mensaje;
	private String valor;

	public General(String car) {
		this.valor = car;
		Pair<Integer, String> entrada = TablaPuntuacion.getEntrada(valor);
		if (entrada != null) {
			codigo = entrada.getKey();
			mensaje = entrada.getValue();
		}
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getCar() {
		return valor;
	}

	public String toString() {
		return "<" + this.codigo + ",>" + this.mensaje;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.valor;
	}
}
