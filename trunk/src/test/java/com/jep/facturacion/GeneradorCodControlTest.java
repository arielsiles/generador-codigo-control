package com.jep.facturacion;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

public class GeneradorCodControlTest 
{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
		
		InputStream in =getClass().getClassLoader().getResourceAsStream("com/jep/facturacion/5000CasosPruebaCCVer7.txt");
		DataInputStream din = new DataInputStream(in);
		BufferedReader br = new BufferedReader(new InputStreamReader(din));
		String strLine;
		strLine =br.readLine();
		GeneradorCodControl codcontrol ;
		while ((strLine = br.readLine()) != null) 
		{
			String[] str= strLine.split("\\|");
			
			
			codcontrol = new GeneradorCodControl(str[0],
												str[1],
												str[2],
												str[3].replace("/", "") ,
												String.valueOf( Math.round( Double.parseDouble(str[4].replace(',','.')))),
												str[5]);
			String codContrlGenerado=codcontrol.getCodigoControl();
			String codControlEsperado=str[10];
			//System.out.println (codContrlGenerado  + "   "+ codControlEsperado);
			Assert.assertEquals(codControlEsperado,codContrlGenerado);
			  
		}			  
		in.close();
	}

	
}