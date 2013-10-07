package Pozycja;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import Katalog.KatalogPozycji;
import Rest.MainWindow;

public class PozycjaDodaj extends Pozycja {

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
	private JButton okButton;
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
	public PozycjaDodaj(KatalogPozycji katalogGlowny,
			MainWindow rodzicMainWindow) {
		setBackground(new Color(105, 105, 105));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				katalog=null;
			}
		});
		katalog = new KatalogPozycji();	//tworze tymczasowy katalog, potem w przypadku wybrania opcji zapisz i wyjdz
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

		lblDodawanieNowejPozycji = new JLabel("Dodawanie nowej pozycji");
		lblDodawanieNowejPozycji.setForeground(new Color(0, 0, 0));
		lblDodawanieNowejPozycji.setBounds(12, 12, 349, 34);
		lblDodawanieNowejPozycji.setFont(new Font("Century Schoolbook L",
				Font.BOLD | Font.ITALIC, 16));
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
		{
			okButton = new JButton("Stworz pozycje");
			okButton.setForeground(new Color(0, 0, 0));
			okButton.setBackground(new Color(255, 255, 102));
			okButton.setBounds(709, 612, 196, 34);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EgzemplarzPozycji ksiazka = new EgzemplarzPozycji(); // tworze nowy egzemplarz ksiazki
					if(zczytajDanePoyzcji(ksiazka)==true) // i zczytuje do niego wartosci 
						wyczyscPola();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}

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

		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(128, 128, 128));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Anuluj");
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setBackground(new Color(255, 153, 102));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zamknijBezZapisu();
					}
				});

				btnNewButtonZamknij = new JButton("Zatwierdz i zamknij");
				btnNewButtonZamknij.setBackground(new Color(255, 0, 0));
				btnNewButtonZamknij.setForeground(new Color(255, 204, 0));
				btnNewButtonZamknij.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						zamknijZDodaniem(katalog,KatalogGlowny);	
					}
				});
				buttonPane.add(btnNewButtonZamknij);
				buttonPane.add(cancelButton);
			}

		}
	}
	/* Metoda nie zapisuje z zapisem do pliku ale dodaje do katalogu glownego z MainWindow, wszystko co tu dodalem */
	private void zamknijZDodaniem(KatalogPozycji katalogDoZapisu,KatalogPozycji katalogGLOWNY) {
		if(katalogDoZapisu.iloscElementow>0){
			for(int i=0;i<katalogDoZapisu.iloscElementow;i++){
				katalogGLOWNY.dodaj(katalogDoZapisu.listaPozycji.get(i));
			}
			Rodzic.sprawdzDodaniePozycji(true,katalogGLOWNY);
			this.setVisible(false);
			dispose();
		}
		else{
			this.dispose();
		}
	}
	private void zamknijBezZapisu(){
//		katalog=null;
		dispose();
	}

	public boolean zczytajDanePoyzcji(EgzemplarzPozycji pozycja) {
		if (!textFieldTytul.getText().equals(""))
			pozycja.tytul = this.textFieldTytul.getText();
		else {
			this.lblNewLabelInfo.setText("Prosze uzupelnic tytul!\n");
			return false;
		}
		if (!this.textFieldAutor.getText().equals(""))
			pozycja.autor = this.textFieldAutor.getText();
		else {
			this.lblNewLabelInfo.setText("Prosze uzupelnic autora!");
			return false;
		}
		if (!this.textAreaOpis.getText().equals("")
				&& !this.textAreaOpis.getText().contains("\n\n")){//czyli wystepuje pusta linia i cos po niej lub nie
				if(!pozycja.opis.endsWith("\n"))	//jesli na koncu nie ma znaku nowej linii
//					pozycja.opis.substring(0, pozycja.opis.length()-1);
					pozycja.opis = this.textAreaOpis.getText();
		
				
		}
		else {
			this.lblNewLabelInfo.setText("Opis jest niezbedny!!");
			return false;
		}
		pozycja.kategoria = (String) this.comboBoxKategoria.getSelectedItem();
		pozycja.liczbaStron = (int) this.spinnerLiczbaStron.getValue();
		pozycja.dzienRozpoczecia = (int) this.spinner_1Dzien.getValue();
		pozycja.miesiacRozpoczecia = zczytajMiesiac(this.comboBoxMiesiac);
		pozycja.rokRozpoczecia = (int) this.spinner_2Rok.getValue();
		pozycja.dzienZakonczenia = (int) this.spinnerDzien2.getValue();
		pozycja.miesiacZakonczenia = zczytajMiesiac(this.comboBoxMiesiac2);
		pozycja.rokZakonczenia = (int) this.spinnerRok2.getValue();
		pozycja.idEgzemplarza = katalog.iloscElementow;
		katalog.dodaj(pozycja);
		this.lblNewLabelInfo.setText("Dodano nową pozycje do katalogu!");
		return true;
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

	public void wyswietlDane(EgzemplarzPozycji pozycja) {
		// this.textAreaOpis.(pozycja.tytul);
	}
}
