package uzytkownik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import rest.MainWindow;
import uzytkownik.ProfilUzytkownika;

/* Klasa tworzy kreator modyfikacji ustawien uzytkownika, uruchamiany przy pierwszym odpaleniu programu */
public class KreatorUsera extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private	String imie;
	private String nazwisko;
	private int wiek;
	private boolean plec=false;
	private JTextField textField;
	private JLabel lblInfo;

	private int iloscWczytanych=1;	//okresla ile elementów zostało już wczytanych i jaki element trzeba teraz wczytać
	private JLabel lblImie;
	private JComboBox comboBox;
	private JLabel lblZdradCoO;
	private JButton btnZatwierd;
	private JSpinner spinnerWieku;
	private boolean flagaUruchomienia=false;//zmieni sie na true, gdy wszystkie informacje zostaną zebrane i może 
											//byc uruchomiony program 
	private final MainWindow Rodzic;
	/**
	 * Create the dialog.
	 */
	public KreatorUsera(MainWindow rodzic) {
		Rodzic=rodzic;
		setAutoRequestFocus(false);
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 639, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 153, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPamitaczbukw = new JLabel("PamiętaczBuków");
		lblPamitaczbukw.setFont(new Font("Nimbus Mono L", Font.BOLD, 18));
		lblPamitaczbukw.setForeground(new Color(255, 0, 0));
		lblPamitaczbukw.setBounds(12, 12, 229, 31);
		contentPanel.add(lblPamitaczbukw);
		
		lblZdradCoO = new JLabel("        Zdradź coś o sobie :)");
		lblZdradCoO.setFont(new Font("Dialog", Font.BOLD, 20));
		lblZdradCoO.setForeground(new Color(255, 255, 153));
		lblZdradCoO.setBounds(117, 57, 449, 24);
		contentPanel.add(lblZdradCoO);
		
		lblImie = new JLabel("Imię");
		lblImie.setForeground(new Color(255, 204, 0));
		lblImie.setFont(new Font("Dialog", Font.BOLD, 20));
		lblImie.setBounds(58, 175, 183, 31);
		contentPanel.add(lblImie);
		
		textField = new JTextField();
		textField.setBounds(340, 181, 241, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		btnZatwierd = new JButton("Zatwierdź");
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!flagaUruchomienia){
					if(zczytajDane())	//jesli zwroci true to zmienInfo 
					zmienInfo();
				}
				else{
					Rodzic.sprawdzUzytkownika(uzytkownik.ProfilUzytkownika.INSTANCJA);//uruchamiam program jesli wszystko
					//wczytalem i moge zamknac ten kreator
					KreatorUsera.this.dispose();
				}
			}
		});
		btnZatwierd.setBounds(252, 238, 117, 25);
		contentPanel.add(btnZatwierd);
		
		lblInfo = new JLabel("");
		lblInfo.setBounds(117, 116, 413, 15);
		contentPanel.add(lblInfo);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"mężczyzna", "kobieta"}));
		comboBox.setEditable(true);
		comboBox.setBounds(350, 180, 152, 26);
		contentPanel.add(comboBox);
		
		spinnerWieku = new JSpinner();
		spinnerWieku.setModel(new SpinnerNumberModel(1, 1, 150, 1));
		spinnerWieku.setBounds(348, 183, 77, 20);
		contentPanel.add(spinnerWieku);
		spinnerWieku.setVisible(false);
		KreatorUsera.this.comboBox.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					KreatorUsera.this.setVisible(false);
					KreatorUsera.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	/* Metoda wczytuje dane z lineEdit'a wczytujac dane do poszczególnych pól imie,nazwisko,wiek,plec */
	private boolean zczytajDane(){
		if(textField.getText().equals("")){
			KreatorUsera.this.lblInfo.setText("Pole jest puste!");
			return false;	//tzn ze nie mam zmienic danych
		}
		else{
			switch(iloscWczytanych){
			case 1:{
				imie=textField.getText();
				iloscWczytanych++;
				break;
			}
			case 2:{
				nazwisko=textField.getText();
				iloscWczytanych++;
				break;
			}
			case 3:{
				wiek=(int)spinnerWieku.getValue();
				iloscWczytanych++;
				break;
			}
			case 4:{
				plec=weryfikujComboBox((String) comboBox.getSelectedItem());
				zapiszUseraDoPliku();
				lblImie.setVisible(false);	//ukrywam wszystkie niepotrzebne pola
				comboBox.setVisible(false);
				textField.setVisible(false);
				lblInfo.setVisible(false);
				lblZdradCoO.setText("   Ok, teraz możemy zaczynać!");
				btnZatwierd.setBounds(210, 238, 197, 25);
				btnZatwierd.setText("Uruchom program");
				flagaUruchomienia=true;//teraz mozna uruchomic program z parametrami uzytkownika tutaj podanego
				iloscWczytanych=5;	//teraz juz nie zmieniInfo w metodzie zmienInfo()
				break;
			}
			}
			return true;
		}
	}
	/* Metoda zmienia pola w oknie informujace jakie dane są teraz wczytywane'
	 * oraz czyści pole wczytywania danych 
	 */
	private void zmienInfo(){
		switch(iloscWczytanych){
		case 2:{
			lblImie.setText("Nazwisko");
			textField.setText("");
			lblInfo.setText("");
			break;
		}
		case 3:{
			lblImie.setText("Wiek");
			textField.setVisible(false);
			spinnerWieku.setVisible(true);
			lblInfo.setText("");
			break;
		}
		case 4:{
			spinnerWieku.setVisible(false);
			lblImie.setText("Plec");
			textField.setVisible(false);
			comboBox.setVisible(true);
			break;
		}
		}

	}
	/* Metoda na podstawie wartosci comboBox'a zwraca opowiednio true jesli wybrano mężczyzne i false jeśli kobiete 
	 * 
	 */
	private boolean weryfikujComboBox(String nazwa){
		if(nazwa.equals("mężczyzna"))
			return true;
		else if(nazwa.equals("kobieta"))
			return false;
	return true;
	}
	private void zapiszUseraDoPliku(){
		ProfilUzytkownika.INSTANCJA.ustawUzytkownika(imie, nazwisko, wiek, plec);	//utawiam wartosci INSTANCJI usera
		ProfilUzytkownika.INSTANCJA.tworzUzytkownika();//tworze plik user'a
		lblInfo.setText("Pomyslnie dodano uzytkownika!");
	}
}
