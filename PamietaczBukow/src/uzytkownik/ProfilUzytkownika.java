package uzytkownik;

public class ProfilUzytkownika {
	public	String imie;
	public 	String nazwisko;
	public	int wiek;
	public	boolean plec;	//1 - facet, 0 - kobieta
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
		
	}
	
}
