package tokens;

import java.util.ArrayList;
import java.util.HashMap;

import tablas.tablasDinamicas.TablaTokens;
import tokens.codigos.CodigoToken;
import tokens.codigos.TipoIdentificador;

public class Identificador implements Token {

	private final Integer identificador = CodigoToken.COD_IDENTIFICADOR;// para token

	private Object valor;
	private String lexema;// para tabla

	private String tipo;
	private int despl; // tambien para token

	private int numParam;
	private ArrayList<HashMap<String, Integer>> tipoModoParam;
	private String tipoRetorno;
	private String etiqFunction;
	private String param;

	public Identificador(String lexema) {
		this.lexema = lexema;
		this.tipoModoParam = new ArrayList<HashMap<String, Integer>>();
	}

	public Identificador(String lexema, Object valor) {
		this.lexema = lexema;
		this.valor = valor;
		this.tipoModoParam = new ArrayList<HashMap<String, Integer>>();
	}

	public Identificador(String lexema, String tipo, Object valor) {
		this.lexema = lexema;
		this.tipo = tipo;
		this.tipoModoParam = new ArrayList<HashMap<String, Integer>>();
	}

	public void setTipo(Object valor) {
		if (valor instanceof String) {
			this.tipo = TipoIdentificador.TIPO_CADENA;
			return;
		}
		if (valor instanceof Integer) {
			this.tipo = TipoIdentificador.TIPO_ENTERO;
			return;
		}
		if (valor instanceof Boolean) {
			this.tipo = TipoIdentificador.TIPO_LOGICO;
			return;
		}
		this.tipo = TipoIdentificador.TIPO_FUNCION;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getTipo() {
		return tipo;
	}

	public int getDespl() {
		return despl;
	}

	public void setDespl() {
		if (this.tipo == null)
			return;
	}

	public int getNumParam() {
		return numParam;
	}

	public void setNumParam(int numParam) {
		this.numParam = numParam;
	}

	public ArrayList<HashMap<String, Integer>> getTipoModoParam() {
		return tipoModoParam;
	}

	public void setTipoModoParam(ArrayList<HashMap<String, Integer>> tipoModoParam) {
		this.tipoModoParam = tipoModoParam;
	}

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public String getEtiqFunction() {
		return etiqFunction;
	}

	public void setEtiqFunction(String etiqFunction) {
		this.etiqFunction = etiqFunction;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public String toStringTablaSimbolos() {

		String lineaLexema = "* LEXEMA : " + '\'' + this.lexema + "\'" + "\r";
		String lineaOpcionalAtributos = "  ATRIBUTOS :\n";

		// ATRIBUTOS: + atributo : valor (cadena con comillas o entero) RC
		String atributoTipo = "  + Tipo : " + "\'" + this.tipo + "\'" + "\r";
		String atributoDespl = "";
		if (this.tipo == null || !this.tipo.equals(TipoIdentificador.TIPO_FUNCION)) {
			atributoDespl = "  + Despl : " + this.despl + "\r";
		}
		String numParam = "";
		String tipoParamXX = "";
		// String modoParamXX = ""; solo se admite paso por valor
		String tipoRetorno = "";
		String etiqFuncion = "";
		// String param = ""; solo cuando no es paso por valor
		if (this.tipo.equals(TipoIdentificador.TIPO_FUNCION)) {

			numParam = "   + numParam: " + this.numParam + "\r";
			for (int i = 0; i < this.tipoModoParam.size(); i++) {
				String tipo = this.tipoModoParam.get(i).keySet().toString();
				tipoParamXX += "    + TipoParam" + (i + 1) + " : " + "\'" + tipo + "\'" + "\r";
			}
			tipoRetorno = "   + TipoRetorno: " + "\'" + this.tipoRetorno + "\'" + "\r";
			etiqFuncion = "   + EtiqFuncion: " + "\'" + this.etiqFunction + "\'" + "\r";
		}

		String fin = "------------ ------------";

		return lineaLexema + lineaOpcionalAtributos + atributoTipo + atributoDespl + numParam + tipoParamXX
				+ tipoRetorno + etiqFuncion;
	}

	public String toString() {
		return "<" + this.identificador + ", " + this.despl + ">" + CodigoToken.MSG_IDENTIFICADOR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etiqFunction == null) ? 0 : etiqFunction.hashCode());
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		result = prime * result + ((lexema == null) ? 0 : lexema.hashCode());
		result = prime * result + numParam;
		result = prime * result + ((param == null) ? 0 : param.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((tipoModoParam == null) ? 0 : tipoModoParam.hashCode());
		result = prime * result + ((tipoRetorno == null) ? 0 : tipoRetorno.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identificador other = (Identificador) obj;
		if (lexema == null) {
			if (other.lexema != null)
				return false;
		} else if (!lexema.equals(other.lexema))
			return false;

		return true;
	}

	public void anadirToken(TablaTokens tokens) {
		tokens.anadirEntrada(this);
	}

	@Override
	public Object getValorToken() {
		return this.lexema;
	}
}
