package rest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import katalog.Katalog;
import katalog.KatalogPozycji;
import katalog.KatalogUstawienia;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.omg.CORBA.UserException;

import pozycja.Pozycja;
import pozycja.PozycjaAktualizuj;
import pozycja.PozycjaDodaj;
import pozycja.PozycjaUsun;
import uzytkownik.KreatorUsera;
import uzytkownik.ProfilUzytkownika;
import uzytkownik.Uzytkownik;
import uzytkownik.UzytkownikUstawienia;
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabelInfo;
	private final JLabel lblWitajWPamietaczubukow = new JLabel("Witaj w PamietaczuBukow");
	//glowny obiekt klasy PlikKatalogu
	private JLabel lblNewLabelAutor;
	private JLabel lblNewLabelTytul;
	private JLabel lblNewLabel_1Kategoria;
	private JLabel lblNewLabelIloscStron;
	private JLabel lblNewLabelCzasCzytania;
	private JLabel lblNewLabelWazneMysli;
	private JButton btnNewButtonKolejny;
	private JButton btnPoprzedni;
	private JSlider slider;
	private JButton btnWczytajKatalog;
	
	int numerPozycji;	//okresla jaki numer pozycji jest okreslony, do poruszania sie w prawo i w lewo
	public KatalogPozycji katalogGlowny;	//glowny katalogPozycji
	public Plik plikKatalogu;	
	boolean flagaWczytania=false;//inforumuje o tym czy kliknieto klawisz wczytanie pozycji jak sie uruchomi program
	boolean flagaDodawania=false;//okresla czy mozna dodac pozycje ( najpierw trzeba wczytac katalog ) potem mozna dodawac
	private JTextArea textAreaOpis;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JTextArea textAreaWazneMysli;
	private JButton btnZamknij;
	private JPanel buttonPanel;
	private JTextPane spinnerIdPozycji;
	private JLabel lblPozycjaNr;
	private JLabel lblNewLabelInfo2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.sprawdzUzytkownika(uzytkownik.ProfilUzytkownika.INSTANCJA);//sprawdzam czy profilUzytkownika jest wypelniony, jesli nie wlaczam kreator
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(255, 165, 0));
		
		/* Ponizej definicje zwiazane z wygladem */
		setTitle("Pamietacz Bukow v.1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 627);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 102));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		lblWitajWPamietaczubukow.setForeground(new Color(0, 0, 0));


		lblWitajWPamietaczubukow.setBackground(Color.RED);
		lblWitajWPamietaczubukow.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 22));
		lblWitajWPamietaczubukow.setBounds(10, 41, 417, 49);
		contentPane.add(lblWitajWPamietaczubukow);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 153, 51));
		menuBar.setBounds(0, 0, 341, 21);
		contentPane.add(menuBar);
		JMenu mnNewMenu = new JMenu("Pozycja");
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		JMenuItem mntmDodaj = new JMenuItem("Dodaj");
		mntmDodaj.setForeground(new Color(0, 0, 0));
		mntmDodaj.setBackground(new Color(255, 140, 0));
		mntmDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sprawdzWczytanie(flagaWczytania)){
					Pozycja dodaj = new PozycjaDodaj(katalogGlowny,MainWindow.this);	
					dodaj.setVisible(true);
					dodaj.setTitle("Dodawanie nowej pozycji");
				}
			}
		})	;
		
		mnNewMenu.add(mntmDodaj);
		
		JMenuItem mntmUsun = new JMenuItem("Usun");
		mntmUsun.setForeground(new Color(0, 0, 0));
		mntmUsun.setBackground(new Color(255, 165, 0));
		mntmUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sprawdzWczytanie(flagaWczytania)){
					Pozycja usun = new PozycjaUsun(katalogGlowny,MainWindow.this);
					usun.setVisible(true);
					usun.setTitle("Usuwanie pozycji");
				}
			}
		});
		mnNewMenu.add(mntmUsun);
		
		JMenuItem mntmAktualizuj = new JMenuItem("Aktualizuj");
		mntmAktualizuj.setForeground(new Color(0, 0, 0));
		mntmAktualizuj.setBackground(new Color(255, 215, 0));
		mntmAktualizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sprawdzWczytanie(flagaWczytania)){
					PozycjaAktualizuj aktualizuj = new PozycjaAktualizuj(katalogGlowny,MainWindow.this);
					aktualizuj.setVisible(true);
					aktualizuj.setTitle("Aktualizowanie pozycji");
				}
			}
		});
		mnNewMenu.add(mntmAktualizuj);
		
		JMenu mnKatalog = new JMenu("Katalog");
		mnKatalog.setForeground(new Color(0, 0, 0));
		menuBar.add(mnKatalog);
		
		JMenuItem mntmUstawienia = new JMenuItem("Ustawienia");
		mntmUstawienia.setForeground(new Color(0, 0, 0));
		mntmUstawienia.setBackground(new Color(255, 140, 0));
		mntmUstawienia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Katalog ustawieniaKatalogu = new KatalogUstawienia(MainWindow.this,katalogGlowny);
				ustawieniaKatalogu.setVisible(true);
			}
		});
		mnKatalog.add(mntmUstawienia);
		
		JMenu mnUytkownik = new JMenu("Użytkownik");
		mnUytkownik.setForeground(new Color(0, 0, 0));
		menuBar.add(mnUytkownik);
		
		JMenuItem mntmUstawienia_1 = new JMenuItem("Ustawienia");
		mntmUstawienia_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(sprawdzWczytanie(flagaWczytania)){
					Uzytkownik uzytkownikUstawienia = new UzytkownikUstawienia();
					uzytkownikUstawienia.setVisible(true);
				}
			}
		});
		mntmUstawienia_1.setForeground(new Color(0, 0, 0));
		mntmUstawienia_1.setBackground(new Color(255, 140, 0));
		mnUytkownik.add(mntmUstawienia_1);
		
		JMenuItem mntmStatystki = new JMenuItem("Statystki");
		mntmStatystki.setForeground(new Color(0, 0, 0));
		mntmStatystki.setBackground(new Color(255, 165, 0));
		mnUytkownik.add(mntmStatystki);
		
		JMenuBar menuBar_1 = new JMenuBar();
		mnUytkownik.add(menuBar_1);
		
		JMenu mnPomoc = new JMenu("Pomoc");
		mnPomoc.setForeground(new Color(0, 0, 0));
		menuBar.add(mnPomoc);
		
		JMenuItem mntmOProgramie = new JMenuItem("O programie");
		mntmOProgramie.setForeground(new Color(255, 140, 0));
		mntmOProgramie.setBackground(new Color(0, 0, 0));
		mntmOProgramie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OProgramie program = new OProgramie();
				program.setVisible(true);
			}
		});
		mnPomoc.add(mntmOProgramie);
		
		lblNewLabelInfo = new JLabel("");
		lblNewLabelInfo.setForeground(new Color(0, 0, 0));
		lblNewLabelInfo.setBounds(361, 23, 503, 49);
		contentPane.add(lblNewLabelInfo);
		
		btnWczytajKatalog = new JButton("Wczytaj Katalog");
		btnWczytajKatalog.setBackground(new Color(0, 0, 0));
		btnWczytajKatalog.setForeground(Color.RED);
		btnWczytajKatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				katalogGlowny=sprawdzKatalog(katalogGlowny);
				wczytajKatalog(plikKatalogu,katalogGlowny);
			}
		});
		btnWczytajKatalog.setBounds(689, 477, 149, 25);
		
		lblNewLabelAutor = new JLabel("Autor");
		lblNewLabelAutor.setForeground(new Color(0, 0, 0));
		lblNewLabelAutor.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		lblNewLabelAutor.setBounds(12, 93, 389, 15);
		contentPane.add(lblNewLabelAutor);
		
		lblNewLabelTytul = new JLabel("Tytul");
		lblNewLabelTytul.setForeground(new Color(0, 0, 0));
		lblNewLabelTytul.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		lblNewLabelTytul.setBounds(12, 131, 377, 15);
		contentPane.add(lblNewLabelTytul);
		
		lblNewLabelIloscStron = new JLabel("Ilosc Stron");
		lblNewLabelIloscStron.setForeground(new Color(0, 0, 0));
		lblNewLabelIloscStron.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		lblNewLabelIloscStron.setBounds(12, 211, 201, 15);
		contentPane.add(lblNewLabelIloscStron);
		
		lblNewLabel_1Kategoria = new JLabel("Kategoria");
		lblNewLabel_1Kategoria.setForeground(new Color(0, 0, 0));
		lblNewLabel_1Kategoria.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		lblNewLabel_1Kategoria.setBounds(10, 172, 352, 15);
		contentPane.add(lblNewLabel_1Kategoria);
		
		lblNewLabelCzasCzytania = new JLabel("Data Rozpoczecia data zakonczenia");
		lblNewLabelCzasCzytania.setForeground(new Color(0, 0, 0));
		lblNewLabelCzasCzytania.setFont(new Font("Century Schoolbook L", Font.BOLD, 14));
		lblNewLabelCzasCzytania.setBounds(10, 252, 540, 15);
		contentPane.add(lblNewLabelCzasCzytania);
		
		lblNewLabelWazneMysli = new JLabel("Wazne mysli");
		lblNewLabelWazneMysli.setForeground(new Color(0, 0, 0));
		lblNewLabelWazneMysli.setBounds(10, 406, 529, 31);
		contentPane.add(lblNewLabelWazneMysli);
		
		slider = new JSlider();
		slider.setBackground(new Color(0, 0, 0));
		slider.setForeground(new Color(0, 0, 0));
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
					wyswietlPozycje(slider.getValue());
					uzupelnijSpinner(spinnerIdPozycji,slider);	//uzupelniam wartosciami slidera ->spinner

			}
		});
		
		slider.setBounds(664, 520, 200, 16);
		contentPane.add(slider);
		
		btnNewButtonKolejny = new JButton("Kolejny ->");
		btnNewButtonKolejny.setForeground(new Color(255, 140, 0));
		btnNewButtonKolejny.setBackground(new Color(0, 0, 0));
		btnNewButtonKolejny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				przewinDalej();
				uzupelnijSpinner(spinnerIdPozycji,slider);	//uzupelniam wartosciami slidera ->spinner
			}
		});
		btnNewButtonKolejny.setBounds(708, 341, 130, 25);
		
		btnPoprzedni = new JButton("Poprzedni <-");
		btnPoprzedni.setForeground(new Color(255, 140, 0));
		btnPoprzedni.setBackground(new Color(0, 0, 0));
		btnPoprzedni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				przewinZpowrotem();
				uzupelnijSpinner(spinnerIdPozycji,slider);	//uzupelniam wartosciami slidera ->spinner
			}
		});
		btnPoprzedni.setBounds(708, 410, 130, 25);
		
		
		/* Definicja scroll panelu i dodaniu do niego TextArea :) to samo w PozycjiDodaj */
		textAreaOpis = new JTextArea();
		textAreaOpis.setForeground(new Color(0, 0, 0));
		textAreaOpis.setEditable(false);
		textAreaOpis.setFont(new Font("Century Schoolbook L", Font.BOLD, 13));
		textAreaOpis.setBackground(new Color(255, 153, 102));
		scrollPane = new JScrollPane(textAreaOpis);
		scrollPane.setBounds(20, 279, 519, 103);
		contentPane.add(scrollPane);
		
		textAreaWazneMysli = new JTextArea();
		textAreaWazneMysli.setForeground(new Color(0, 0, 0));
		textAreaWazneMysli.setEditable(false);
		textAreaWazneMysli.setFont(new Font("Century Schoolbook L", Font.BOLD, 13));
		textAreaWazneMysli.setBackground(new Color(255, 153, 102));
		scrollPane2 = new JScrollPane(textAreaWazneMysli);
		scrollPane2.setBounds(20, 448, 519, 88);
		contentPane.add(scrollPane2);
		
		spinnerIdPozycji = new JTextPane();
		spinnerIdPozycji.setForeground(new Color(255, 165, 0));
		spinnerIdPozycji.setBackground(new Color(0, 0, 0));
		spinnerIdPozycji.setEditable(false);
		spinnerIdPozycji.setBounds(817, 489, 47, 21);
		contentPane.add(spinnerIdPozycji);
		
		lblPozycjaNr = new JLabel("Pozycja nr.");
		lblPozycjaNr.setFont(new Font("Dialog", Font.BOLD, 13));
		lblPozycjaNr.setForeground(new Color(255, 0, 0));
		lblPozycjaNr.setBounds(719, 495, 92, 15);
		contentPane.add(lblPozycjaNr);
		
		lblNewLabelInfo2 = new JLabel("");
		lblNewLabelInfo2.setForeground(new Color(0, 0, 0));
		lblNewLabelInfo2.setBounds(357, 93, 488, 15);
		contentPane.add(lblNewLabelInfo2);
		
		btnZamknij = new JButton("Zamknij");	//zamykam okno mainwindow i wszystkie childreny
		btnZamknij.setForeground(new Color(255, 0, 0));
		btnZamknij.setBackground(new Color(0, 0, 0));
		btnZamknij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sprawdzWczytanie(flagaWczytania)){	//jesli wczytano katalog to wyswietl okienko dajace mozliwosc wyjscia i mozna zapisac wsio
					OknoZamykania oknoZamykania = new OknoZamykania(MainWindow.this,katalogGlowny,plikKatalogu,ProfilUzytkownika.INSTANCJA);
					MainWindow.this.setVisible(false);
					oknoZamykania.setVisible(true);
				}
				else
					MainWindow.this.dispose();
			}
		});
		btnZamknij.setBounds(721, 511, 117, 25);
		
		/* Definicja buttonPanelu i dodawania do niego przyciskow */
		buttonPanel = new JPanel();
		buttonPanel.setBounds(20, 548, 844, 40);
		buttonPanel.setBackground(new Color(255, 153, 0));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);		
		buttonPanel.add(this.btnPoprzedni);
		buttonPanel.add(this.btnNewButtonKolejny);
		buttonPanel.add(this.btnWczytajKatalog);
		buttonPanel.add(btnZamknij);
		/* ************************************************** */
		katalogGlowny = new KatalogPozycji();
	    plikKatalogu = new Plik();
	    numerPozycji=0;
	    ukryj(true);
	  
	}
	/*Metoda ponizej ukrywa jesli labele okna jesli jest dostanie true, w przeciwnym wypadku odslania */
	private void ukryj(boolean decyzja){
		if(decyzja==true){
			this.lblNewLabelAutor.setVisible(false);
			this.lblNewLabelTytul.setVisible(false);
			this.lblNewLabel_1Kategoria.setVisible(false);
			this.lblNewLabelCzasCzytania.setVisible(false);
			this.scrollPane.setVisible(false);
			this.scrollPane2.setVisible(false);
			this.lblNewLabelWazneMysli.setVisible(false);
			this.lblNewLabelIloscStron.setVisible(false);
			this.btnNewButtonKolejny.setVisible(false);
			this.btnPoprzedni.setVisible(false);
			this.spinnerIdPozycji.setVisible(false);
			this.slider.setVisible(false);
			this.lblPozycjaNr.setVisible(false);
		}
		else if(decyzja==false){
			this.lblNewLabelAutor.setVisible(true);
			this.lblNewLabelTytul.setVisible(true);
			this.lblNewLabel_1Kategoria.setVisible(true);
			this.lblNewLabelCzasCzytania.setVisible(true);
			this.scrollPane.setVisible(true);
			this.scrollPane2.setVisible(true);
			this.lblNewLabelWazneMysli.setVisible(true);
			this.lblNewLabelIloscStron.setVisible(true);
			this.btnNewButtonKolejny.setVisible(true);
			this.btnPoprzedni.setVisible(true);
			this.spinnerIdPozycji.setVisible(true);
			this.slider.setVisible(true);
			this.lblPozycjaNr.setVisible(true);
		}
	}
	private void wczytajKatalog(Plik plikKat,KatalogPozycji katalog ){
		try {
			if(plikKat.wczytajKatalog(katalog)==true){
				this.spinnerIdPozycji.setText(Integer.toString(katalog.listaPozycji.get(0).idEgzemplarza));
				this.lblNewLabelAutor.setText(katalog.listaPozycji.get(0).autor);	
				this.lblNewLabelTytul.setText(katalog.listaPozycji.get(0).tytul);
				this.lblNewLabel_1Kategoria.setText(katalog.listaPozycji.get(0).kategoria);
				this.lblNewLabelIloscStron.setText("Ilość stron: "+Integer.toString(katalog.listaPozycji.get(0).liczbaStron));
				this.textAreaOpis.setText(katalog.listaPozycji.get(0).opis);
				this.lblNewLabelCzasCzytania.setText("Czas czytania: "+Integer.toString(wyznaczDate(katalogGlowny,0))+" dni");
				uaktualnijSlider(katalog.iloscElementow);
				ukryj(false);
				flagaWczytania=true;	//teraz mozna otwierac kolejne okna
				this.lblNewLabelInfo.setText("Pomyślnie wczytano katalog :D");
			}
			else{
				this.lblNewLabelInfo.setText("Nie udało się wczytać katalogu. ");
				this.lblNewLabelInfo2.setText("Katalog nie zawiera jeszcze żadnych pozycji! Stwórz jakąś :) ");
				flagaWczytania=true;
			}
		} catch (IOException e) {
			this.lblNewLabelInfo.setText("Nie udało się wczytać katalogu. ");
			this.lblNewLabelInfo2.setText("Katalog nie zawiera jeszcze żadnych pozycji! Stwórz jakąś :) ");
			flagaWczytania=true;

		}
		this.btnWczytajKatalog.setVisible(false);//ukrywam zeby nie klikac 100 razy	
	}
	private void przewinDalej(){
		numerPozycji++;
		this.lblNewLabelInfo.setText("");
		if((numerPozycji<katalogGlowny.iloscElementow)&&(numerPozycji>=0)){
			this.slider.setValue(numerPozycji);
			this.spinnerIdPozycji.setText(Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).idEgzemplarza));
			this.lblNewLabelAutor.setText(katalogGlowny.listaPozycji.get(numerPozycji).autor);
			this.lblNewLabelTytul.setText(katalogGlowny.listaPozycji.get(numerPozycji).tytul);
			this.lblNewLabel_1Kategoria.setText(katalogGlowny.listaPozycji.get(numerPozycji).kategoria);
			this.lblNewLabelIloscStron.setText("Ilość stron: "+Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).liczbaStron));
			this.lblNewLabelCzasCzytania.setText("Czas czytania: "+Integer.toString(wyznaczDate(katalogGlowny,numerPozycji))+" dni");
			this.textAreaOpis.setText(katalogGlowny.listaPozycji.get(numerPozycji).opis);
		}
		else
			numerPozycji--;//zeby nie inkrementowal bez konca jak sie wiecej razy poklika
	}
	private void przewinZpowrotem(){
		numerPozycji--;
		this.lblNewLabelInfo.setText("");
		if((numerPozycji<katalogGlowny.iloscElementow)&&(numerPozycji>=0)){
			this.slider.setValue(numerPozycji);
			this.spinnerIdPozycji.setText(Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).idEgzemplarza));
			this.lblNewLabelAutor.setText(katalogGlowny.listaPozycji.get(numerPozycji).autor);
			this.lblNewLabelTytul.setText(katalogGlowny.listaPozycji.get(numerPozycji).tytul);
			this.lblNewLabel_1Kategoria.setText(katalogGlowny.listaPozycji.get(numerPozycji).kategoria);
			this.lblNewLabelIloscStron.setText("Ilość stron: "+Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).liczbaStron));
			this.lblNewLabelCzasCzytania.setText("Czas czytania: "+Integer.toString(wyznaczDate(katalogGlowny,numerPozycji))+" dni");
			this.textAreaOpis.setText(katalogGlowny.listaPozycji.get(numerPozycji).opis);
		}
		else
			numerPozycji++;	//zeby nie dekremtnowal bez konca jak sie wiecej razy poklika
		
	}
	/* Metoda korzysta z zewnetrznej biblioteki Joda. Najpierw tworze pierwsza date, potem druga.
	 * Nastepnie licze liczbe dni pomiedzy i zwracam 
	 */
	private int wyznaczDate(KatalogPozycji katalog,int index){
		LocalDate dataRozpoczecia = new LocalDate(katalog.listaPozycji.get(index).rokRozpoczecia,katalog.listaPozycji.get(index).miesiacRozpoczecia,katalog.listaPozycji.get(index).dzienRozpoczecia);
		LocalDate dataZakonczenia = new LocalDate(katalog.listaPozycji.get(index).rokZakonczenia,katalog.listaPozycji.get(index).miesiacZakonczenia,katalog.listaPozycji.get(index).dzienZakonczenia);
		Days dni = Days.daysBetween(dataRozpoczecia,dataZakonczenia);
		return dni.getDays();
	}
	/* Wyswietla pozycje o wybranym numerze, public bo wykorzystane
	 * w Pozycji do wyswietlania pozycji nie usunietej, tej ktora zostala przy zamknieciu okna
	 */
	public void wyswietlPozycje(int numerPozycji){
		this.lblNewLabelInfo.setText("");
		if((numerPozycji<katalogGlowny.iloscElementow)&&(numerPozycji>=0)){
			this.spinnerIdPozycji.setText(Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).idEgzemplarza));
			this.lblNewLabelAutor.setText(katalogGlowny.listaPozycji.get(numerPozycji).autor);
			this.lblNewLabelTytul.setText(katalogGlowny.listaPozycji.get(numerPozycji).tytul);
			this.lblNewLabel_1Kategoria.setText(katalogGlowny.listaPozycji.get(numerPozycji).kategoria);
			this.lblNewLabelIloscStron.setText("Ilość stron: "+Integer.toString(katalogGlowny.listaPozycji.get(numerPozycji).liczbaStron));
			this.lblNewLabelCzasCzytania.setText("Czas czytania: "+Integer.toString(wyznaczDate(katalogGlowny,numerPozycji))+" dni");
			this.textAreaOpis.setText(katalogGlowny.listaPozycji.get(numerPozycji).opis);
		}
	}
	/* Metoda uzupelnia spinnerID(de facto nie spiner a textPane ) na podstawie wartosci slider'a */
	private void uzupelnijSpinner(JTextPane spinnerId,JSlider slider){
		try{
			    numerPozycji=slider.getValue();
				spinnerId.setText(Integer.toString(slider.getValue()+1));
		}
		catch(NullPointerException e){
			this.lblNewLabelInfo.setText("Blad uzupelniania spinnera!");
		}
	}
	/* Metoda uaktualnia slider po tym jak dodam np. nowy element. Wtedy slider ma max wartosc
	 * tyle ile jest aktualnie elementow , pozycji w liscie */
	public void uaktualnijSlider(int max){	//max -1 bo jest od 0 wiec elementow jest o 1 mniej
		if(max>0){
			MainWindow.this.slider.setMaximum(max-1);	//specjalnie tak, zeby nie robic publicznego slidera i przekazywac
			slider.setValue(0);
			MainWindow.this.lblNewLabelInfo2.setText("");
		}
		else if(max==0){
			MainWindow.this.slider.setMaximum(0);
			ukryj(true);//bo lista jest pusta nie ma co wyswietlac 
			MainWindow.this.lblNewLabelInfo2.setText("Katalog pozycji jest pusty. Nie ma co wyświetlić...");
		}
		//jako parametr
	}
	/* Metoda czysci katalogGlowny z tego co dodalem w PozycjiDodaj i potem jak  klikne
	 * wczytajKatalog to on wczyta wsystko na nowo z pliku, a tak by 2 razy wczytal
	 * bo mialby to co dodalem + to co jest w pliku :) to tylko w sytuacji gdy najpierw dodaje, a potem dopiero wczytuje
	 */
	private KatalogPozycji sprawdzKatalog(KatalogPozycji katalog){
		if(katalog.iloscElementow>0){		//to jest po to zeby zrobil sobie czysty pusty katalog Pozycji
			KatalogPozycji nowyKatalog = new KatalogPozycji();	//i wczytal na nowo wszystko z pliku
			katalog=nowyKatalog;//bo teraz ma te stare zapisane, co dodalem wczesniej :)
		}
		return katalog;
	}
	/* Metoda odslania element MainWindow gdy dodam pozycje do katalgu, ale nie zapisalem jej jeszcze do pliku
	 * i tym samym nie wczytalem ( a wtedy sa odslaniane ) tutaj bede oznaczal ze dodano pozycje i po zamknieciu 
	 * PozycjiDodaj bedzie wyswietlona dana pozycja/-e bez info ze nie wczytano jeszcze katalogu
	 */
	public void sprawdzDodaniePozycji(boolean flagaDodania,KatalogPozycji katalog){
		if(flagaDodania==true){
			this.lblNewLabelInfo.setText("");
			this.spinnerIdPozycji.setText(Integer.toString(katalog.listaPozycji.get(0).idEgzemplarza));
			this.lblNewLabelAutor.setText(katalog.listaPozycji.get(0).autor);	
			this.lblNewLabelTytul.setText(katalog.listaPozycji.get(0).tytul);
			this.lblNewLabel_1Kategoria.setText(katalog.listaPozycji.get(0).kategoria);
			this.lblNewLabelIloscStron.setText("Ilość stron: "+Integer.toString(katalog.listaPozycji.get(0).liczbaStron));
			this.textAreaOpis.setText(katalog.listaPozycji.get(0).opis);
			this.lblNewLabelCzasCzytania.setText("Czas czytania: "+Integer.toString(wyznaczDate(katalogGlowny,0))+" dni");
			uaktualnijSlider(katalog.iloscElementow);
			this.lblNewLabelInfo2.setText("");
			ukryj(false);
		}
	}
	private boolean sprawdzWczytanie(boolean flagaWczytywania){
		if(flagaWczytywania==true){	//jesli true mozna otwierac kolejne okna, tam sprawdzaja co zwroci ta metoda
			this.lblNewLabelInfo.setText("");
			return true;
		}
		else{
			this.lblNewLabelInfo.setText("Najpierw wczytaj katalog :)!");
			return false;
		}
	}
	/* Metoda weryfikuje czy zostaly uzupelnione dane dotyczące użytkownika. Jeśli nie, wyświetlany jest kreator uzupelnienia danych */
	public void sprawdzUzytkownika(ProfilUzytkownika user){
		try{
			if(user.wczytajUzytkownika()){	//metoda wczytajUzytkownika wczytuje wartosci uzytownika z pliku
				user.weryfikujUzytkownika();//sprawdzam czy są różne od domyslnych
				MainWindow.this.setVisible(true);//jesli wszystko OK to pokazuje MainWindow, jesli nie jest to wyrzuca sie wyjatek i nie pokaze
			}
			else 
				throw( new uzytkownik.UserException());
		}
		catch (uzytkownik.UserException e) {//jesli nie to uruchamiam kreator
			KreatorUsera kreator = new KreatorUsera(MainWindow.this);
			kreator.setVisible(true);
		}
	}
}	