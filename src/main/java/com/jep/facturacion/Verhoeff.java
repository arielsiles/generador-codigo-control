package com.jep.facturacion;

public class Verhoeff 
{
	private int[][] arrayMultiplicacion = {
											{0,1,2,3,4,5,6,7,8,9},
											{1,2,3,4,0,6,7,8,9,5},
											{2,3,4,0,1,7,8,9,5,6},
											{3,4,0,1,2,8,9,5,6,7},
											{4,0,1,2,3,9,5,6,7,8},
											{5,9,8,7,6,0,4,3,2,1},
											{6,5,9,8,7,1,0,4,3,2},
											{7,6,5,9,8,2,1,0,4,3},
											{8,7,6,5,9,3,2,1,0,4},
											{9,8,7,6,5,4,3,2,1,0}};
	
		
	private int[][] arrayPermutacion = {
											{0,1,2,3,4,5,6,7,8,9},
											{1,5,7,6,2,8,3,0,9,4},
											{5,8,0,3,7,9,6,1,4,2},
											{8,9,1,6,0,4,3,5,2,7},
											{9,4,5,3,1,2,6,8,7,0},
											{4,2,8,6,5,7,3,9,0,1},
											{2,7,9,3,8,0,6,4,1,5},
											{7,0,4,6,9,1,3,2,5,8}};
	
	private int[] Inv ={0,4,3,2,1,5,6,7,8,9};
	public Verhoeff()
	{
		
	}

	public String ObtenerVerhoeff(String cifra) {
	
		   String cifrainvertida = reverseMe (cifra);
           int check = 0;
           for (int i = 0; i < cifrainvertida.length(); i++)
           {
               check = arrayMultiplicacion[check][ (
                                                   arrayPermutacion[(i + 1) % 8]
                                                		   	       [Integer.valueOf(cifrainvertida.substring(i,i+1))]
                                                    )];
           }
           
		return String.valueOf( Inv[check]);
	}
	private String reverseMe(String s) {
		   if(s.length() == 0)
		     return "";
		   return s.charAt(s.length() - 1) + reverseMe(s.substring(0,s.length()-1));
		 }
	

}
