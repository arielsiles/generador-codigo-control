package com.jep.facturacion;

public class Base64 {

	private String resultado;
	private long va;

	public Base64(long valor) {
		va = valor;
	}

	/**
	 * @return the resultado
	 */
	public String getResultado() {
		long cociente = 1;
		long resto = 0;
		String resul = "";
		while (cociente > 0) {
			cociente = va / 64;
			resto = va % 64;
			Diccionario dic = new Diccionario();
			resul = dic.getValor((int) resto) + resul;
			va = cociente;
		}
		resultado = resul;
		return resultado;
	}

	class Diccionario {

		public String valores[];

		public Diccionario() {
			String val[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
					"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
					"Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
					"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
					"w", "x", "y", "z", "+", "/" };
			valores = val;
		}

		public String getValor(int posicion) {
			return valores[posicion];
		}
	}
}