package Pozycja;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextPane;

import Katalog.KatalogPozycji;
import Rest.MainWindow;
public abstract class Pozycja extends JDialog {
	/* Skopiowane z MainWindow uzupelnia spinnerId na podstawie slider'a */
	protected MainWindow Rodzic; //okresla rodzica jaki przyjdzie w konstruktorze kazdej Pozycji...
	protected KatalogPozycji KatalogGlowny;	//jesli uzytkownik bedzie chcial zapisac, do tego beda zapisywane wszystkie pozycje
	
	
	/* Metoda zamyka okno i katalogGlowny z MainWindow jest teraz katalogiem z Pozycji
	 * czyli usunieto elementy. Nie zapisuje jeszcze do pliku, dopiero w MainWindow bedzie mozna zapisac ostatecznie
	 * zeby tutaj od razu nie zapadala decyzja czy to ma byc finalna postac listy :D zeby nie bylo przypadkow
	 */
	protected KatalogPozycji zamknijZzapisem(KatalogPozycji katalog, KatalogPozycji katalogGlowny){
		katalogGlowny=katalog;
		Pozycja.this.setVisible(false);
		Pozycja.this.dispose();
		Rodzic.uaktualnijSlider(katalogGlowny.iloscElementow);//zeby byl max tyle ile jest elementow
		Rodzic.wyswietlPozycje(0);
		return katalogGlowny;	// zwracam bo to jest tak: dostaje katalogGlowny i przyrownuje go do katalogu. Mimo
		//ze to refrencja to sie dzieje jakby wewnatrz metody i nie dotyczy zewnetrzego. Troche pojebane, ale to 
		//dziala w ten sposob, jak zwracam to zwracam to co sie tu stalo i przypisuje do zewnetrznej refrencji
		//gdybym edytowal obiekt to by sie zmienil zewnetrznie
		//moglem tez w srodku metody przyrowna a nie przekazywac jako argument kataloguGlownego
	}
	protected void uzupelnijSpinner(JTextPane spinnerId,JSlider slider,JLabel label){
		try{
				spinnerId.setText(Integer.toString(slider.getValue()+1));	//wyswietlam o 1 wiecej :D
		}
		catch(NullPointerException e){
			label.setText("Blad uzupelniania spinnera!");
		}
	}
}
