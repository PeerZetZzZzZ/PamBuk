package rest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import katalog.KatalogPozycji;
import pozycja.EgzemplarzPozycji;
public class Plik {
	public BufferedReader plik_do_odczytu; //zmienna do odczytywania z pliku
	public BufferedWriter plik_do_zapisu; //zmienna do zapisywania w pliku
	int iloscLinii; //ile linii zajmuje dana pozycja w pliku, potrzebne przy wczytywaniu
	public void nowy_do_odczytu(String sciezka_pliku) throws IOException{	/*Funkcja otwiera plik do odczytu o podanej nazwie*/
		Path sciezka = FileSystems.getDefault().getPath(sciezka_pliku);
		this.plik_do_odczytu = Files.newBufferedReader(sciezka, StandardCharsets.UTF_8); /*zmienna klasowa plik_odczyt do uzytku */
	}
	public String czytaj_linie(BufferedReader plik_odczyt) throws IOException{ /*odczytuje linie tekstu i zwraca*/
		return plik_odczyt.readLine();
	}
	public void nowy_do_zapisu(String sciezka_pliku) throws IOException{ /*otwiera do zapisu plik o podanej nazwie*/
		Path sciezka= FileSystems.getDefault().getPath(sciezka_pliku);
		try{
			this.plik_do_zapisu = Files.newBufferedWriter(sciezka,StandardCharsets.UTF_8,StandardOpenOption.TRUNCATE_EXISTING);
//			this.plik_do_zapisu
		}
		catch(NoSuchFileException e){//jak nie bedzie pliku to stworzy
			this.plik_do_zapisu = Files.newBufferedWriter(sciezka,StandardCharsets.UTF_8,StandardOpenOption.CREATE_NEW);
		}
		}
	public void zapisz_tekst(BufferedWriter plik_zapis, String tekst) throws IOException{ /* zapisuje podany tekst do pliku */ 
		plik_zapis.write(tekst); //zapisuje tekst i przechodze do nowej linii
		plik_zapis.write("\n");
	}
	public void zamknij_plik() throws IOException{	/* metoda zamykajaca pliki */
		if(plik_do_odczytu!=null)
			plik_do_odczytu.close();
		if(plik_do_zapisu!=null)
		plik_do_zapisu.close();
	}
	/* Metoda ponizej wczytuje 1 egzemplarz pozycji z pliku i go zwraca. Posluguje sie klasowym polem nowy_do_odczytu
	 * zeby pamietac wskaznik, gdzie juz jest w pliku. Dlatego go nie przekazuje jako parametr */
	public EgzemplarzPozycji wczytajPozycje() throws IOException{ //bedzie to rzucac gdy zle wczyta juz
		iloscLinii=0;
		String bufor="";	//do wczytywania opisu tak dlugo az nie ma pustej linii
		try{
			EgzemplarzPozycji pozycja = new EgzemplarzPozycji();
			pozycja.idEgzemplarza=Integer.parseInt(plik_do_odczytu.readLine());	//konwertuje stringa tego co wczyta na int'a
			pozycja.tytul=plik_do_odczytu.readLine();
			pozycja.autor=plik_do_odczytu.readLine();
			pozycja.kategoria=plik_do_odczytu.readLine();
			pozycja.liczbaStron=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.rokRozpoczecia=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.miesiacRozpoczecia=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.dzienRozpoczecia=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.rokZakonczenia=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.miesiacZakonczenia=Integer.parseInt(plik_do_odczytu.readLine());
			pozycja.dzienZakonczenia=Integer.parseInt(plik_do_odczytu.readLine());
			iloscLinii=11;
			bufor=plik_do_odczytu.readLine();
			String buforDodatkowy=plik_do_odczytu.readLine();//zczytuje kolejna linie zeby sprawdzic czy pusta
			while(!bufor.contentEquals("")){
				if(buforDodatkowy.contentEquals("")){
					pozycja.opis+=bufor;//wczytuje linie i dopisuje koniec linii bo on nie wczyta
					iloscLinii+=1;	
					break;
				}
				else{
					pozycja.opis+=bufor+"\n";
					iloscLinii+=1;	
				}
				bufor=buforDodatkowy;
				buforDodatkowy=plik_do_odczytu.readLine();
			}
				return pozycja;
		}
		catch(IOException e){
			throw e;
		}
		catch(NumberFormatException a){
			throw new IOException();
		}
	}
	public boolean wczytajKatalog(KatalogPozycji katalog) throws IOException{
		try{
			boolean flaga=true;						//troche podupione, najpierw otwieram i licze linie
			nowy_do_odczytu("plikKatalogu.txt");	//potem zamykam i znowu otwieram ;D 
			int licznik = liczLinie(plik_do_odczytu);	//zwroci mi ilosc linii w pliku
			if(licznik==0)
				return false;//jesli jest 0 linii to zwroci false 
			nowy_do_odczytu("plikKatalogu.txt");
			int sprawdzak=0;	//dopoki nie osiagnie sprawdzak licznika to wczytuj
			while(sprawdzak!=licznik){
				EgzemplarzPozycji nowaPozycja = wczytajPozycje();
				sprawdzak+=iloscLinii+1;	//inkrementuje o ilosc linii jaka zajela dana pozycja
				katalog.dodaj(nowaPozycja);
				flaga=false;
			}
			zamknij_plik();
			return true;
			
		}
		catch(IOException e){
			throw e;
		}
	}
	/* Metoda ponizej zapisuje aktualny katalog do pliku */
	public void zapiszKatalogPelny(KatalogPozycji katalog) throws IOException{
		try{
			nowy_do_zapisu("plikKatalogu.txt");
			for(int i=0;i<katalog.iloscElementow;i++){
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).idEgzemplarza)+"\n");
				plik_do_zapisu.write(katalog.listaPozycji.get(i).tytul+"\n");
				plik_do_zapisu.write(katalog.listaPozycji.get(i).autor+"\n");
				plik_do_zapisu.write(katalog.listaPozycji.get(i).kategoria+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).liczbaStron)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).rokRozpoczecia)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).miesiacRozpoczecia)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).dzienRozpoczecia)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).rokZakonczenia)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).miesiacZakonczenia)+"\n");
				plik_do_zapisu.write(Integer.toString(katalog.listaPozycji.get(i).dzienZakonczenia)+"\n");
				plik_do_zapisu.write(katalog.listaPozycji.get(i).opis+"\n");
				plik_do_zapisu.write("\n");
			}
		}
		catch(IOException e){
			throw e;
		}
		zamknij_plik();
//		plik_do_zapisu.close();
	}
	/* Metoda zapisuje, czysci plik katalogu gdy usunie sie wszystkie elmenety i chcemy miec pusty katalog */
	public void zapiszKatalogPusty(KatalogPozycji katalog) throws IOException{
		try{
			nowy_do_zapisu("plikKatalogu.txt");
			plik_do_zapisu.write("");	//plik będzie czysty ;)
		}
		catch(IOException e){
			throw e;
		}
	}

	public int liczLinie(BufferedReader plik){ // funkcja liczy ilosc liń w pliku, i zwraca ta ilosc
		int licznik=0;
		try {
			while(plik.readLine()!=null)
				licznik++;
			plik.close();
		} catch (IOException e){
		}
		return licznik;
	}
}