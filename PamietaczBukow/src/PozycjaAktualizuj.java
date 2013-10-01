import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.TextArea;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

/*
public class PozycjaAktualizuj extends Pozycja {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		try {
			PozycjaAktualizuj dialog = new PozycjaAktualizuj();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
/*
	public PozycjaAktualizuj() {
		setBackground(new Color(105, 105, 105));
		setBounds(100, 100, 585, 373);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAktualizujPozycje = new JLabel("Aktualizuj pozycje");
		lblAktualizujPozycje.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblAktualizujPozycje.setBounds(12, 12, 247, 27);
		contentPanel.add(lblAktualizujPozycje);
		{
			JLabel lblTytu = new JLabel("Tytuł");
			lblTytu.setBounds(12, 69, 70, 15);
			contentPanel.add(lblTytu);
		}
		
		textField = new JTextField();
		textField.setBounds(100, 67, 203, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(12, 109, 70, 15);
		contentPanel.add(lblAutor);
		{
			textField_1 = new JTextField();
			textField_1.setBounds(100, 107, 203, 19);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblKategoria = new JLabel("Kategoria");
			lblKategoria.setBounds(12, 151, 76, 15);
			contentPanel.add(lblKategoria);
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(100, 146, 333, 20);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(105, 105, 105));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}*/
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;

public class PozycjaAktualizuj extends Pozycja {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTytul;
	private JTextField textFieldAutor;
	private JLabel lblTytul;
	private JLabel lblAutor;
	private JLabel lblKategoria;
	private JComboBox comboBoxKategoria;
	private JLabel lblDodawanieNowejPozycji;
	private JLabel lblLiczbaStron;
	private JSpinner spinnerLiczbaStron;
	private JLabel lblDataRozpoczciaCzytania;
	private JSpinner spinner_1Dzien;
	private JComboBox comboBoxMiesiac;
	private JSpinner spinner_2Rok;
	private JLabel lblOpis;
	private JLabel lblWazneMysli;
	private JButton btnDodaj;
	private JPanel buttonPane;
	private JButton cancelButton;
	private JLabel lblNewLabelInfo;
	private JButton btnNewButtonZamknij;
	private String miesiace[] = { "Styczen", "Luty", "Marzec", "Kwiecien",
			"Maj", "Czerwiec", "Lipiec", "Sierpien", "Wrzesien", "Pazdziernik",
			"Listopad", "Grudzien" };
	private String kategorie[] = { "Biografie, wspomnienia",
			"Dramaty, utworzy sceniczne", "Fantasy", "Historyczne", "Horrory",
			"Humor, parodia, satyra", "Kryminal",
			"Literatura piekna - proza polska",
			"Literatura piekna - proza zagraniczna", "Pozeja", "Przygodowe",
			"Reportaz, literatura faktu", "Romanse", "Science fiction",
			"Spoleczno-obyczajowe", "Thrillery, sensacje", "Zestawy, kolekcje",
			"Pozostale" };

	KatalogPozycji katalog; //tymczasowo tworzony katalog
	private JLabel lblDataZakoczeniaCzytania;
	private JSpinner spinnerDzien2;
	private JComboBox comboBoxMiesiac2;
	private JSpinner spinnerRok2;
	private JTextArea textAreaOpis;
	private JScrollPane scrollPane;
	private JTextArea textAreaWazneMysli;
	private JScrollPane scrollPane2;

	int poprzednik=0;//numer wartosci slidera zanim sie zmienilo
	int aktualny=0;	//aktualna wartosc slidera; uzywane przy zapisie automatycznym
	int numerPozycji=0;
	private JSlider slider;
	private JTextPane spinnerId;
	private JButton btnAktualizujPozycje;
	boolean flagaWyboruZapisu=false;//decyduje o tym czy automatycznie zapisujemy ( wcisniety zapisAutomatyczny )
	private JToggleButton tglbtnAktualizujAutomatycznie;
	//czyli po kazdym przesunieciu nastepnej pozycji ta co byla sie zapisuje bez potrzeby wciskania klawisza zapisz
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { PozycjaDodaj dialog = new
	 * PozycjaDodaj();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public PozycjaAktualizuj(KatalogPozycji katalogGlowny,
			MainWindow rodzicMainWindow) {
		setBackground(new Color(128, 128, 128));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				katalog=null;
			}
		});
		katalog = new KatalogPozycji(katalogGlowny);	//tworze tymczasowy katalog, potem w przypadku wybrania opcji zapisz i wyjdz
										//wszystkie jego elementy zapisze do glownego katalogu
		KatalogGlowny=katalogGlowny;
		Rodzic = rodzicMainWindow;
		setBounds(100, 100, 934, 720);
		/* Tutaj ustawiam contentPanele, te getContentPane, ustawia panele jakie tam mam... dziwne ale tak je */
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTytul = new JLabel("Tytuł");
		lblTytul.setForeground(new Color(0, 0, 0));
		lblTytul.setBounds(22, 61, 70, 15);
		contentPanel.add(lblTytul);

		lblAutor = new JLabel("Autor");
		lblAutor.setForeground(new Color(0, 0, 0));
		lblAutor.setBounds(22, 105, 70, 15);
		contentPanel.add(lblAutor);

		lblKategoria = new JLabel("Kategoria");
		lblKategoria.setForeground(new Color(0, 0, 0));
		lblKategoria.setBounds(22, 149, 82, 19);
		contentPanel.add(lblKategoria);

		comboBoxKategoria = new JComboBox(kategorie);
		comboBoxKategoria.setForeground(new Color(0, 0, 0));
		comboBoxKategoria.setBackground(new Color(204, 204, 204));
		comboBoxKategoria.setBounds(136, 146, 333, 24);
		contentPanel.add(comboBoxKategoria);

		textFieldTytul = new JTextField();
		textFieldTytul.setBackground(new Color(204, 204, 204));
		textFieldTytul.setBounds(136, 59, 174, 19);
		contentPanel.add(textFieldTytul);
		textFieldTytul.setColumns(10);

		textFieldAutor = new JTextField();
		textFieldAutor.setBackground(new Color(204, 204, 204));
		textFieldAutor.setBounds(138, 103, 174, 19);
		textFieldAutor.setText(null);
		contentPanel.add(textFieldAutor);
		textFieldAutor.setColumns(10);

		lblDodawanieNowejPozycji = new JLabel("Aktualizowanie pozycji");
		lblDodawanieNowejPozycji.setForeground(new Color(0, 0, 0));
		lblDodawanieNowejPozycji.setBounds(12, 12, 349, 34);
		lblDodawanieNowejPozycji.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		contentPanel.add(lblDodawanieNowejPozycji);

		lblLiczbaStron = new JLabel("Liczba stron");
		lblLiczbaStron.setForeground(new Color(0, 0, 0));
		lblLiczbaStron.setBounds(22, 190, 96, 24);
		contentPanel.add(lblLiczbaStron);

		// SpinnerNumberModel modelLiczbyStron = new SpinnerNumberModel();
		spinnerLiczbaStron = new JSpinner();
		spinnerLiczbaStron.setBackground(new Color(204, 204, 204));
		spinnerLiczbaStron.setModel(new SpinnerNumberModel(new Integer(1),
				new Integer(1), null, new Integer(1)));
		spinnerLiczbaStron.setBounds(136, 193, 70, 20);
		contentPanel.add(spinnerLiczbaStron);

		lblDataRozpoczciaCzytania = new JLabel("Data rozpoczęcia czytania");
		lblDataRozpoczciaCzytania.setForeground(new Color(0, 0, 0));
		lblDataRozpoczciaCzytania.setBounds(22, 239, 196, 15);
		contentPanel.add(lblDataRozpoczciaCzytania);

		spinner_1Dzien = new JSpinner();
		spinner_1Dzien.setBackground(new Color(204, 204, 204));
		spinner_1Dzien.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_1Dzien.setBounds(268, 237, 76, 20);
		contentPanel.add(spinner_1Dzien);

		comboBoxMiesiac = new JComboBox(miesiace);
		comboBoxMiesiac.setForeground(new Color(255, 0, 0));
		comboBoxMiesiac.setBackground(new Color(204, 204, 204));
		comboBoxMiesiac.setBounds(356, 237, 128, 19);
		contentPanel.add(comboBoxMiesiac);

		spinner_2Rok = new JSpinner();
		spinner_2Rok.setBackground(new Color(204, 204, 204));
		spinner_2Rok.setModel(new SpinnerNumberModel(new Integer(2013),
				new Integer(1), null, new Integer(1)));
		spinner_2Rok.setBounds(496, 237, 75, 20);
		contentPanel.add(spinner_2Rok);

		lblOpis = new JLabel("Opis");
		lblOpis.setForeground(new Color(0, 0, 0));
		lblOpis.setBounds(22, 353, 110, 15);
		contentPanel.add(lblOpis);

		lblWazneMysli = new JLabel("Ważne myśli");
		lblWazneMysli.setForeground(new Color(0, 0, 0));
		lblWazneMysli.setBounds(22, 502, 96, 15);
		contentPanel.add(lblWazneMysli);

		btnDodaj = new JButton("Dodaj");
		btnDodaj.setForeground(new Color(0, 0, 0));
		btnDodaj.setBackground(new Color(255, 255, 204));
		btnDodaj.setBounds(280, 621, 117, 25);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		contentPanel.add(btnDodaj);

		lblNewLabelInfo = new JLabel("labelInfo");
		lblNewLabelInfo.setBounds(496, 85, 427, 54);
		contentPanel.add(lblNewLabelInfo);
		this.lblNewLabelInfo.setText("");

		lblDataZakoczeniaCzytania = new JLabel("Data zakończenia czytania");
		lblDataZakoczeniaCzytania.setForeground(new Color(0, 0, 0));
		lblDataZakoczeniaCzytania.setBounds(22, 290, 196, 15);
		contentPanel.add(lblDataZakoczeniaCzytania);

		comboBoxMiesiac2 = new JComboBox(miesiace);
		comboBoxMiesiac2.setForeground(new Color(255, 0, 153));
		comboBoxMiesiac2.setBackground(new Color(204, 204, 204));
		comboBoxMiesiac2.setBounds(356, 285, 128, 24);
		contentPanel.add(comboBoxMiesiac2);

		spinnerDzien2 = new JSpinner();
		spinnerDzien2.setBackground(new Color(204, 204, 204));
		spinnerDzien2.setModel(new SpinnerNumberModel(2, 1, 31, 1));
		spinnerDzien2.setBounds(268, 288, 76, 20);
		contentPanel.add(spinnerDzien2);

		spinnerRok2 = new JSpinner();
		spinnerRok2.setBackground(new Color(204, 204, 204));
		spinnerRok2.setModel(new SpinnerNumberModel(new Integer(2013),
				new Integer(1), null, new Integer(1)));
		spinnerRok2.setBounds(496, 288, 75, 20);
		contentPanel.add(spinnerRok2);

		/* Dodanie JTextArea do JScrollPanel */
		textAreaOpis = new JTextArea();
		textAreaOpis.setBackground(Color.LIGHT_GRAY);
		scrollPane = new JScrollPane(textAreaOpis);
		scrollPane.setBounds(32, 380, 381, 106);
		contentPanel.add(scrollPane);

		textAreaWazneMysli = new JTextArea();
		textAreaWazneMysli.setBackground(Color.LIGHT_GRAY);
		scrollPane2 = new JScrollPane(textAreaWazneMysli);
		scrollPane2.setBounds(32, 529, 375, 83);
		contentPanel.add(scrollPane2);
		
		slider = new JSlider();
		slider.setMaximum(katalog.iloscElementow-1);
		slider.setValue(0);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				aktualny=slider.getValue();//zczytuje aktualna wartosc
				uzupelnijSpinner(spinnerId,slider,PozycjaAktualizuj.this.lblNewLabelInfo);	//uzupelniam wartosciami slidera ->spinner
				if(flagaWyboruZapisu==true){
					wyswietlPozycje(katalog,slider.getValue());
					odslon();
				}
				else if(flagaWyboruZapisu==false){
					aktualizujPozycje(katalog,poprzednik);//aktualizuje poprzednika, ale odslniam
					wyswietlPozycje(katalog,aktualny);//kolejny ;]
				}
				poprzednik=aktualny;	//zapisuje wartosc poprzednia automatycznie, a teraz zapmiatuje juz ta jaka jest
			}
		});
		slider.setBounds(723, 630, 200, 16);
		contentPanel.add(slider);
		
		spinnerId = new JTextPane();
		spinnerId.setEditable(false);
		spinnerId.setText("1");
		spinnerId.setBounds(765, 593, 50, 19);
		contentPanel.add(spinnerId);
		
		btnAktualizujPozycje = new JButton("Aktualizuj pozycje");
		btnAktualizujPozycje.setForeground(new Color(0, 0, 0));
		btnAktualizujPozycje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(aktualizujPozycje(katalog,slider.getValue()))
					zaktualizowanoPozycje(true);
				else
					zaktualizowanoPozycje(false);
				}
		});
		btnAktualizujPozycje.setBackground(new Color(255, 51, 51));
		btnAktualizujPozycje.setBounds(502, 621, 196, 25);
		contentPanel.add(btnAktualizujPozycje);
		
		tglbtnAktualizujAutomatycznie = new JToggleButton("Aktualizuj automatycznie");
		tglbtnAktualizujAutomatycznie.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				aktualizujAutomatycznie();
			}
		});
		tglbtnAktualizujAutomatycznie.setBackground(new Color(0, 0, 0));
		tglbtnAktualizujAutomatycznie.setForeground(new Color(255, 51, 102));
		tglbtnAktualizujAutomatycznie.setBounds(496, 587, 213, 25);
		contentPanel.add(tglbtnAktualizujAutomatycznie);

		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(128, 128, 128));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Anuluj");
				cancelButton.setBackground(new Color(255, 153, 102));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zamknijBezZapisu();
					}
				});

				btnNewButtonZamknij = new JButton("Zatwierdz i zamknij");
				btnNewButtonZamknij.setBackground(new Color(255, 0, 51));
				btnNewButtonZamknij.setForeground(new Color(255, 204, 0));
				btnNewButtonZamknij.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Rodzic.katalogGlowny=zamknijZzapisem(katalog,Rodzic.katalogGlowny);
					}
				});
				
				JButton btnPoprzedni = new JButton("Poprzedni <-");
				btnPoprzedni.setForeground(new Color(0, 0, 0));
				btnPoprzedni.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						przewinZpowrotem(katalog);
						uzupelnijSpinner(spinnerId,slider,PozycjaAktualizuj.this.lblNewLabelInfo);	//uzupelniam wartosciami slidera ->spinner
						odslon();
					}
				});
				buttonPane.add(btnPoprzedni);
				
				JButton btnNewButtonKolejny = new JButton("Kolejny ->");
				btnNewButtonKolejny.setForeground(new Color(0, 0, 0));
				btnNewButtonKolejny.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						przewinDalej(katalog);
						uzupelnijSpinner(spinnerId,slider,PozycjaAktualizuj.this.lblNewLabelInfo);	//uzupelniam wartosciami slidera ->spinner
						odslon();
					}
				});
				buttonPane.add(btnNewButtonKolejny);
				buttonPane.add(btnNewButtonZamknij);
				buttonPane.add(cancelButton);
			}

		}
		wczytajPozycje(katalog);
	}

	private void zamknijBezZapisu(){
		dispose();
	}


	public int zczytajMiesiac(JComboBox combo) {
		int numer = 0;
		for (int i = 0; i < 12; i++) {
			if (combo.getSelectedItem().equals(miesiace[i]))		//zczytuje nazwe miesiaca i porownuje 
				numer = i + 1; // zwraca numer miesiac o podanej nazwie
		}
		return numer;
	}

	private void wyczyscPola() {
		this.textFieldAutor.setText("");
		this.textAreaOpis.setText("");
		this.textFieldTytul.setText("");
		this.spinnerLiczbaStron.setValue(0);
	}

	private void wczytajPozycje(KatalogPozycji katalog){
		if((numerPozycji<katalog.iloscElementow)&&(numerPozycji>=0)){
			this.textFieldAutor.setText(katalog.listaPozycji.get(numerPozycji).autor);
			this.textFieldTytul.setText(katalog.listaPozycji.get(numerPozycji).tytul);
			this.comboBoxKategoria.setName(katalog.listaPozycji.get(numerPozycji).kategoria);
			this.spinnerLiczbaStron.setValue(katalog.listaPozycji.get(numerPozycji).liczbaStron);
			this.spinner_1Dzien.setValue(katalog.listaPozycji.get(numerPozycji).dzienRozpoczecia);
			this.spinnerDzien2.setValue(katalog.listaPozycji.get(numerPozycji).dzienZakonczenia);
			this.spinner_2Rok.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.spinnerRok2.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.comboBoxMiesiac.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacRozpoczecia-1]);
			this.comboBoxMiesiac2.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacZakonczenia-1]);
			this.textAreaOpis.setText(katalog.listaPozycji.get(numerPozycji).opis);
		}
	}
	private void przewinDalej(KatalogPozycji katalog){
		numerPozycji++;
		if((numerPozycji<katalog.iloscElementow)&&(numerPozycji>=0)){
			if(flagaWyboruZapisu==true)	//jesli nie ma automatycznego aktualizowania
				odslon();//odlsaniam guziki aktualizowania	
			this.slider.setValue(numerPozycji);	//slider ustawiam zeby mial wartosc jaka chce wyswietlic, to oczywiste
			this.textFieldAutor.setText(katalog.listaPozycji.get(numerPozycji).autor);
			this.textFieldTytul.setText(katalog.listaPozycji.get(numerPozycji).tytul);
			this.comboBoxKategoria.setName(katalog.listaPozycji.get(numerPozycji).kategoria);
			this.spinnerLiczbaStron.setValue(katalog.listaPozycji.get(numerPozycji).liczbaStron);
			this.spinner_1Dzien.setValue(katalog.listaPozycji.get(numerPozycji).dzienRozpoczecia);
			this.spinnerDzien2.setValue(katalog.listaPozycji.get(numerPozycji).dzienZakonczenia);
			this.spinner_2Rok.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.spinnerRok2.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.comboBoxMiesiac.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacRozpoczecia-1]);
			this.comboBoxMiesiac2.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacZakonczenia-1]);
			this.textAreaOpis.setText(katalog.listaPozycji.get(numerPozycji).opis);
		}
		else
			numerPozycji--;

	}
	private void przewinZpowrotem(KatalogPozycji katalog){
		numerPozycji--;
		if((numerPozycji<katalog.iloscElementow)&&(numerPozycji>=0)){
			if(flagaWyboruZapisu==true)
				odslon();//odlsaniam guziki aktualizowania
			this.slider.setValue(numerPozycji);	//slider ustawiam zeby mial wartosc jaka chce wyswietlic, to oczywiste
			this.textFieldAutor.setText(katalog.listaPozycji.get(numerPozycji).autor);
			this.textFieldTytul.setText(katalog.listaPozycji.get(numerPozycji).tytul);
			this.comboBoxKategoria.getItemAt(3);// ((katalog.listaPozycji.get(numerPozycji).kategoria));
			this.spinnerLiczbaStron.setValue(katalog.listaPozycji.get(numerPozycji).liczbaStron);
			this.spinner_1Dzien.setValue(katalog.listaPozycji.get(numerPozycji).dzienRozpoczecia);
			this.spinnerDzien2.setValue(katalog.listaPozycji.get(numerPozycji).dzienZakonczenia);
			this.spinner_2Rok.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.spinnerRok2.setValue(katalog.listaPozycji.get(numerPozycji).rokRozpoczecia);
			this.comboBoxMiesiac.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacRozpoczecia-1]);
			this.comboBoxMiesiac2.setName(miesiace[katalog.listaPozycji.get(numerPozycji).miesiacZakonczenia-1]);
			this.textAreaOpis.setText(katalog.listaPozycji.get(numerPozycji).opis);
		}
		else
			numerPozycji++;
	}
	private void wyswietlPozycje(KatalogPozycji katalog,int numer){
		if((numerPozycji<katalog.iloscElementow)&&(numer>=0)){
			if(flagaWyboruZapisu==true)
				odslon();//odlsaniam guziki aktualizowania
			this.textFieldAutor.setText(katalog.listaPozycji.get(numer).autor);
			this.textFieldTytul.setText(katalog.listaPozycji.get(numer).tytul);
			this.comboBoxKategoria.setName(katalog.listaPozycji.get(numer).kategoria);
			this.spinnerLiczbaStron.setValue(katalog.listaPozycji.get(numer).liczbaStron);
			this.spinner_1Dzien.setValue(katalog.listaPozycji.get(numer).dzienRozpoczecia);
			this.spinnerDzien2.setValue(katalog.listaPozycji.get(numer).dzienZakonczenia);
			this.spinner_2Rok.setValue(katalog.listaPozycji.get(numer).rokRozpoczecia);
			this.spinnerRok2.setValue(katalog.listaPozycji.get(numer).rokRozpoczecia);
			this.comboBoxMiesiac.setName(miesiace[katalog.listaPozycji.get(numer).miesiacRozpoczecia-1]);
			this.comboBoxMiesiac2.setName(miesiace[katalog.listaPozycji.get(numer).miesiacZakonczenia-1]);
			this.textAreaOpis.setText(katalog.listaPozycji.get(numer).opis);
		}
	}
	private boolean aktualizujPozycje(KatalogPozycji katalog,int nrPozycji){
		if (!textFieldTytul.getText().equals(""))
			katalog.listaPozycji.get(nrPozycji).tytul = this.textFieldTytul.getText();
		else {
			this.lblNewLabelInfo.setText("Prosze uzupelnic tytul!\n");
			return false;
		}
		if (!this.textFieldAutor.getText().equals(""))
			katalog.listaPozycji.get(nrPozycji).autor = this.textFieldAutor.getText();
		else {
			this.lblNewLabelInfo.setText("Prosze uzupelnic autora!");
			return false;
		}
		if (!this.textAreaOpis.getText().equals("")
				&& !this.textAreaOpis.getText().contains("\n\n"))
			katalog.listaPozycji.get(nrPozycji).opis = this.textAreaOpis.getText();
		else {
			this.lblNewLabelInfo.setText("Opis jest niezbedny!!");
			return false;
		}
		katalog.listaPozycji.get(nrPozycji).kategoria = (String) this.comboBoxKategoria.getSelectedItem();
		katalog.listaPozycji.get(nrPozycji).liczbaStron = (int) this.spinnerLiczbaStron.getValue();
		katalog.listaPozycji.get(nrPozycji).dzienRozpoczecia = (int) this.spinner_1Dzien.getValue();
		katalog.listaPozycji.get(nrPozycji).miesiacRozpoczecia = zczytajMiesiac(this.comboBoxMiesiac);
		katalog.listaPozycji.get(nrPozycji).rokRozpoczecia = (int) this.spinner_2Rok.getValue();
		katalog.listaPozycji.get(nrPozycji).dzienZakonczenia = (int) this.spinnerDzien2.getValue();
		katalog.listaPozycji.get(nrPozycji).miesiacZakonczenia = zczytajMiesiac(this.comboBoxMiesiac2);
		katalog.listaPozycji.get(nrPozycji).rokZakonczenia = (int) this.spinnerRok2.getValue();
		katalog.listaPozycji.get(nrPozycji).idEgzemplarza = katalog.iloscElementow;
		return true;
	}
	private void zaktualizowanoPozycje(boolean decyzja){
		if(decyzja==true){
			PozycjaAktualizuj.this.btnAktualizujPozycje.setVisible(false);//ukrywam guzik bo juz zaktualizowane
			PozycjaAktualizuj.this.lblNewLabelInfo.setText("Zaktualizowano wybraną pozycję!");
		}
		else
			PozycjaAktualizuj.this.lblNewLabelInfo.setText("Hmm, coś jest nie tak!");
	}
	/*Metoda ponizej odslania guziki i czysci labelInfo po dodaniu pozycji */
	private void odslon(){
		this.btnAktualizujPozycje.setVisible(true);
		this.lblNewLabelInfo.setText("");
	}
	private void aktualizujAutomatycznie(){
		if(tglbtnAktualizujAutomatycznie.isSelected()){
			flagaWyboruZapisu=false;//jak automatycznie to zaslaniam reczne aktualizowanie 
			btnAktualizujPozycje.setVisible(false);
			PozycjaAktualizuj.this.lblNewLabelInfo.setText("Aktualizowanie automatyczne...");
		}
		else{
			flagaWyboruZapisu=true;
			btnAktualizujPozycje.setVisible(true);
			PozycjaAktualizuj.this.lblNewLabelInfo.setText("");

		}
	}
	
}

