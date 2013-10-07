package Katalog;
import java.util.LinkedList;

import Pozycja.EgzemplarzPozycji;

public class KatalogPozycji {
	public LinkedList<EgzemplarzPozycji> listaPozycji;		//lista EgzemplarzowPozycji
	public int iloscElementow; //wartosc bedzie inkrementowana przy dodawaniu elementu
	public void dodaj(EgzemplarzPozycji pozycja){
		listaPozycji.add(pozycja);
		iloscElementow++;
	}
	public void usun(int nrPozycji){
		listaPozycji.remove(nrPozycji);
		iloscElementow--;
	}
	public EgzemplarzPozycji zczytajPozycje(int nr_pozycji){
		return listaPozycji.get(nr_pozycji);
	}
	public KatalogPozycji(){
		listaPozycji = new LinkedList<EgzemplarzPozycji>();
		iloscElementow=0;
	}
	/* Konstruktor kopiujacy, dla tworzenia tego samego katalogu na podstawie innego. Musze zrobic
	 * bo trzeba przekopiowac ta liste a nie tylko referencje */
	public KatalogPozycji(KatalogPozycji katalogZewnetrzny){
		listaPozycji = new LinkedList<EgzemplarzPozycji>();
		this.iloscElementow=katalogZewnetrzny.iloscElementow;
		for(int i=0;i<katalogZewnetrzny.listaPozycji.size();i++)
			this.listaPozycji.add(katalogZewnetrzny.listaPozycji.get(i));
	}
	public void usunWszystko(){
		this.listaPozycji.clear();	//ja pierdole, a ja szukam jakies Remove...
		iloscElementow=0;
	}
	public void zamknij(){
		listaPozycji.clear();
		listaPozycji=null;
	}
	
}
