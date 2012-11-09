package main;

import com.jep.facturacion.GeneradorCodControl;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GeneradorCodControl codcontrol = new GeneradorCodControl
						("29040011007","1503", 
						"4189179011", "20070702",
						"2500",
						"9rCB7Sv4X29d)5k7N%3ab89p-3(5[A");
		String str = codcontrol.getCodigoControl();
		System.out.println(str );
        
	}

}
