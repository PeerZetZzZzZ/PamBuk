package uzytkownik;

import java.io.IOException;

import rest.Plik;

public class ProfilUzytkownika {
	public	String imie;
	public 	String nazwisko;
	public	int wiek;
	public	boolean plec;	//1  facet, 0 - kobieta
	
	private Plik plikProfiluUzytkownika = new Plik();	//plik obslugujacy baze danych zwiazanych z uzytkownikiem
	public static final ProfilUzytkownika INSTANCJA = new ProfilUzytkownika();
	private ProfilUzytkownika(){
		imie="";
		nazwisko="";
		wiek=0;
		plec=false;
	}
	/* Metoda sprawdza czy INSTANCJA uzytkownika zostala ustawiona, tj. czy zostaly przypisane wartosci. Jesli
	 * wszystkie pola maja domyslne wartosci, oznacza to że nie uzupelniono profilu uzytkownika w programie bądź
	 * zresetowano jego dane i trzeba je ustawic. Sprawdzane za kazdym wlaczeniem MainWindow, na początku.
	 */
	public void weryfikujUzytkownika() throws UserException{
		if((this.imie.equals(""))||(this.nazwisko.equals(""))||this.wiek<0)
			throw  new UserException();
		
	}
	/* Metoda ustawia wartosci dla danego uzytkownika */
	public void ustawUzytkownika(String imiE, String nazwiskO, int wieK, boolean pleC){
		this.imie=imiE;
		this.nazwisko=nazwiskO;
		this.plec=pleC;
		this.wiek=wieK;
	}
	public void tworzUzytkownika(){
		try {
			plikProfiluUzytkownika.nowy_do_zapisu("profilUzytkownika.txt");
			plikProfiluUzytkownika.zapisz_tekst(plikProfiluUzytkownika.plik_do_zapisu, this.imie);
			plikProfiluUzytkownika.zapisz_tekst(plikProfiluUzytkownika.plik_do_zapisu, this.nazwisko);
			plikProfiluUzytkownika.zapisz_tekst(plikProfiluUzytkownika.plik_do_zapisu, Integer.toString(this.wiek));
			plikProfiluUzytkownika.zapisz_tekst(plikProfiluUzytkownika.plik_do_zapisu, Boolean.toString(this.plec));
			plikProfiluUzytkownika.zamknij_plik();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void wczytajUzytkownika(){
		try {
			plikProfiluUzytkownika.nowy_do_odczytu("profilUzytkownika.txt");
			this.imie=plikProfiluUzytkownika.czytaj_linie(plikProfiluUzytkownika.plik_do_odczytu);
			this.nazwisko=plikProfiluUzytkownika.czytaj_linie(plikProfiluUzytkownika.plik_do_odczytu);
			this.wiek=Integer.parseInt(plikProfiluUzytkownika.czytaj_linie(plikProfiluUzytkownika.plik_do_odczytu));
			this.plec=Boolean.parseBoolean(plikProfiluUzytkownika.czytaj_linie(plikProfiluUzytkownika.plik_do_odczytu));
			plikProfiluUzytkownika.zamknij_plik();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
