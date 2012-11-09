package com.jep.facturacion;
import java.util.ArrayList;
import java.util.List;

public class GeneradorCodControl 
{
	private String nroAutorizacion;
	private String nroFactura;
	private String ciNit;
	private String fechaTransaccion;
	private String montoTransaccion;
	private String keyDosificacion;
	private String strGenerado;
	
	public GeneradorCodControl(String _nroAutorizacion,String _nroFactura, 
							String _nitCi, String _fechaTransaccion,
							String _montoTransaccion , String _keyDosificacion)
	{
		nroAutorizacion =_nroAutorizacion;
		nroFactura=_nroFactura; 
		ciNit = _nitCi;
		fechaTransaccion=_fechaTransaccion;
		montoTransaccion = _montoTransaccion ;
		keyDosificacion =_keyDosificacion;
	}
	public String getCodigoControl()
	{
		String strNroVerhoeff;
		
		 //Paso 1
		 strNroVerhoeff = sumatoriaInputs();
		 strNroVerhoeff = strNroVerhoeff.substring(strNroVerhoeff.length()-5);
		 
		 //Paso 2
		 String strllave=setInputKey(strNroVerhoeff);
		 AllegedRC4 ged = new AllegedRC4();
		 
		 //paso 3
		 String str = keyDosificacion + strNroVerhoeff;
	     String cifrado = ged.encriptaSinguion(strllave, str);

	     //paso 4
	    
	     int sumatoria=0;
	     
	     int sumas[] = new int[5];
	     
	     for (int i = 0; i < cifrado.length() ; i++) 
	     {
	    	 
			int intchr = (int)(cifrado.charAt(i));
			sumas[i%5]+=intchr;
			sumatoria +=intchr;
		}
	    
	    //paso 5
	     String strEncrypt=   sumatoriaResultadosb64(strNroVerhoeff, sumatoria, sumas);
	     //paso 6
	     String key = keyDosificacion +strNroVerhoeff;
	     AllegedRC4 ge = new AllegedRC4();
	     String codgoCotnrol= ge.encripta(strEncrypt, key);
	     
	    
		return codgoCotnrol;
	}
	private String sumatoriaResultadosb64(String strNroVerhoeff, int sumatoria, int[] sumas) {
		int suma=0;
	     for (int i = 0; i < sumas.length; i++) {
	    	
	    	 int delta=(Integer.valueOf(strNroVerhoeff.substring(i, i + 1)) + 1);
	    	 sumas[i] = (sumas[i] * sumatoria)/delta ;
	    	 suma+=sumas[i] ; 
		}
	     Base64 b64 = new Base64(suma);
	     return b64.getResultado();
	}
	
	public String setInputKey(String strNroVerhoeff )
	{
		char[] cArray = strNroVerhoeff.toCharArray();
		List<String> listAdd = new ArrayList<String>();
		int start =0;
		for (char valorVerhoeff : cArray) 
		{
			int addVerhoeff = Character.getNumericValue(valorVerhoeff) + 1;
			listAdd.add(keyDosificacion.substring(start,start+addVerhoeff));
			start +=addVerhoeff;
		}
		nroAutorizacion=nroAutorizacion+ listAdd.get(0);
		nroFactura = nroFactura + listAdd.get(1);
        ciNit = ciNit + listAdd.get(2);
        fechaTransaccion = fechaTransaccion + listAdd.get(3);
        montoTransaccion = montoTransaccion + listAdd.get(4);
        
        strGenerado = nroAutorizacion+nroFactura+ciNit+fechaTransaccion+montoTransaccion;
        
		return strGenerado;
	}
	 private String sumatoriaInputs()
     {
		 calculoInputVerhoef();
         long sumatotal = 0;
         String cifra;
         sumatotal += Long.parseLong(nroFactura);
         sumatotal += Long.parseLong(ciNit);
         sumatotal += Long.parseLong(fechaTransaccion);
         sumatotal += Long.parseLong(montoTransaccion);
         cifra = String.valueOf( sumatotal);
         
         Verhoeff verhoef = new Verhoeff();
         cifra = cifra + verhoef.ObtenerVerhoeff(cifra);
         cifra = cifra + verhoef.ObtenerVerhoeff(cifra);
         cifra = cifra + verhoef.ObtenerVerhoeff(cifra);
         cifra = cifra + verhoef.ObtenerVerhoeff(cifra);
         cifra = cifra + verhoef.ObtenerVerhoeff(cifra);
         return cifra;

     }
     
     public void calculoInputVerhoef()
     {
    	 //Primer valor verhoeff
         Verhoeff verhoef = new Verhoeff();
         nroFactura = nroFactura + verhoef.ObtenerVerhoeff(nroFactura);
         ciNit = ciNit + verhoef.ObtenerVerhoeff(ciNit);
         fechaTransaccion = fechaTransaccion + verhoef.ObtenerVerhoeff(fechaTransaccion);
         montoTransaccion = montoTransaccion + verhoef.ObtenerVerhoeff(montoTransaccion);
    	 //Segundo Valor verhoeff
         nroFactura = nroFactura + verhoef.ObtenerVerhoeff(nroFactura);
         ciNit = ciNit + verhoef.ObtenerVerhoeff(ciNit);
         fechaTransaccion = fechaTransaccion + verhoef.ObtenerVerhoeff(fechaTransaccion);
         montoTransaccion = montoTransaccion + verhoef.ObtenerVerhoeff(montoTransaccion);
     }
	
}
