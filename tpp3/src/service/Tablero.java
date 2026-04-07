package service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Tablero {
	private int [][] A;
	private int [][] B;
	private int posF;
	private int posC;
	private boolean gano;
	private int contMovimientos;
	private LinkedList lista;

	public Tablero(int n) {
		A = new int [n][n];
		lista=new LinkedList<>();
		agregarNumeros(A);
		posPuntero();
		B = new int [n][n];
		agregarNumeros(B);
		mezclarMatriz();
		contMovimientos=0;  
	}

	private void agregarNumeros(int[][] tab) {
		int contador=1;
		for (int f=0; f<tab[0].length;f++) {
			for (int c=0;c< tab.length;c++) {
				tab[f][c]= contador;
				contador++;
			}
		}
	}

	private void posPuntero() {
		int maximo=A.length*A.length;
		for (int f = 0; f < A.length; f++) {
			for (int c = 0; c < A.length; c++) {
				if(A[f][c]==maximo) {
					this.posF=f;
					this.posC=c;
				}
			}
		}
	}
	/*c=-1 IZQ c= 1 DER f=-1 ARR f=1 ABJ USAR 0 si no se quiere mover columna o fila*/
	public boolean mover(int c, int f) {
		if(gano)
			return false;
		if(this.posC==0 && c==-1 || this.posC==A.length-1 && c==1 ||
				this.posF==0 && f==-1 || this.posF==A.length-1 && f==1    ) {
			return false;
		}
		int temp = A[this.posF+f][this.posC+c];
		A[this.posF+f][this.posC+c]=A[this.posF][this.posC];
		A[this.posF][this.posC]=temp;
		posF=posF+f;
		posC=posC+c;
		contMovimientos++;
		if(c==0 && f==1) {
			agregarALista("arr");
		}
		if(c==0 &&f==-1) {
			agregarALista("abj");
		}
		if(c==1 && f==0) {
			agregarALista("izq");
		}
		if(c==-1 && f==0) {
			agregarALista("der");
		}
		condicionGano();
		return true;
	}
	private boolean moverAyuda(int c, int f) {
		if(gano)
			return false;
		if(this.posC==0 && c==-1 || this.posC==A.length-1 && c==1 ||
				this.posF==0 && f==-1 || this.posF==A.length-1 && f==1    ) {
			return false;
		}
		int temp = A[this.posF+f][this.posC+c];
		A[this.posF+f][this.posC+c]=A[this.posF][this.posC];
		A[this.posF][this.posC]=temp;
		posF=posF+f;
		posC=posC+c;
		contMovimientos++;	
		condicionGano();	
		return true;
	}
	public int devuelveF() {
		return this.posF;
	}
	public int devuelveC() {
		return this.posC;
	}
	public int consultarTablero(int i,int j) {
		return A[i][j];
	}
	public Integer consultarMovs() {
		return contMovimientos;
	}

	//ACUMULADORES
	private boolean iguales(int[][] n,int[][] m) { 
		boolean ret= true;
		for (int f = 0; f <n.length; f++) {
			ret= ret && iguales2(n[f],m[f]);
		}
		return ret;
	}
	private boolean iguales2(int[] n, int[] m) {
		boolean ret= true;
		for (int f = 0; f <n.length; f++) {
			ret= ret && iguales3(n[f],m[f]);
		}
		return ret;
	}
	private boolean iguales3(int i, int j) {
		return (i==j);
	}
	public boolean ganaste() {
		return this.gano;
	}
	public void condicionGano() {
		if(iguales(A,B)) {
			this.gano=true;
		}
	}
	private void mezclarMatriz() {
		Random matRandom=new Random();
		String ultimoMov="der";
		for(int i=0; i<50;i=i) {
			int casos=matRandom.nextInt(4);
			switch(casos) {
			case 1:if(ultimoMov!="izq") {
				if(mover(1, 0) ) {
					ultimoMov="der";              
					i++;
					break;
				}
			}
			case 2:if(ultimoMov!="der") { 
				if(mover(-1, 0)) {
					ultimoMov="izq";	                    
					i++;
					break;
				}
			}        
			case 3:if(ultimoMov!="abj") { 
				if(mover(0, -1)) {
					ultimoMov="arr";	                   
					i++;
					break;
				}
			}
			case 4:if(ultimoMov!="arr") {
				if (mover(0, 1)) {
					ultimoMov="abj";	                        
					i++;
					break;
				}
			}
			}
		}
	}

	public void ayuda() {
		// Almacena el valor actual del último elemento de la lista
		String ultimoValor =dameLista();
		if(!lista.isEmpty()) {
			// Realiza las comparaciones con la variable almacenada
			if(ultimoValor.equals("arr")) {
				if(moverAyuda(0,-1)) {
					removerDeLista();
				}
			}
			else if(ultimoValor.equals("izq")) {
				if(moverAyuda(-1,0)) {
					removerDeLista();
				}
			}
			else if(ultimoValor.equals("der")) {
				if(moverAyuda(1,0)) {
					removerDeLista();
				}
			}
			else if(ultimoValor.equals("abj")) {
				if(moverAyuda(0,1)) {
					removerDeLista();
				}
			}   
		}
	}
	
	private void removerDeLista() {
		lista.remove(lista.size()-1);
	}
	private void agregarALista(String x) {
		lista.add(x);
	}
	private String dameLista() {
		String ret=(String)	lista.get(lista.size()-1);
		return ret;
	}
}
