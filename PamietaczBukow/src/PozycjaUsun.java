import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;


public class PozycjaUsun extends Pozycja {

	private final JPanel contentPanel = new JPanel();
	KatalogPozycji katalog;	
	private JLabel lblNewLabel_1Autor;
	private JLabel lblTytul;
	private JLabel lblNewLabelUsuwaniePozycji;
	private JButton cancelButton;
	private JSlider slider;
	private JButton btnNewButtonUsun;
	private JLabel lblNewLabelInfo;
	private JTextPane spinnerId;
	private JLabel lblNewLabelPozycjaNr;
	private JButton btnUsunWszystko;
	private JButton btnZatwierdzZamknij;
	

	/**
	 * Create the dialog.
	 */
	public PozycjaUsun(KatalogPozycji katalogGlowny,MainWindow rodzic) {
		Rodzic=rodzic;	//zapamietuje rodzica
		KatalogGlowny=katalogGlowny;
		katalog= new KatalogPozycji(katalogGlowny);	//tworze nowy katalog za pomoca konstruktora kopiujacego
		setBackground(new Color(105, 105, 105));
		setBounds(100, 100, 602, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblNewLabelUsuwaniePozycji = new JLabel("Usuwanie pozycji");
			lblNewLabelUsuwaniePozycji.setForeground(new Color(0, 0, 0));
			lblNewLabelUsuwaniePozycji.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
			lblNewLabelUsuwaniePozycji.setBounds(12, 12, 353, 47);
			contentPanel.add(lblNewLabelUsuwaniePozycji);
		}
		
		lblNewLabel_1Autor = new JLabel("Autor:");
		lblNewLabel_1Autor.setForeground(new Color(0, 0, 0));
		lblNewLabel_1Autor.setBounds(12, 83, 556, 30);
		contentPanel.add(lblNewLabel_1Autor);
		{
			lblTytul = new JLabel("Tytuł:");
			lblTytul.setForeground(new Color(0, 0, 0));
			lblTytul.setBounds(12, 160, 556, 30);
			contentPanel.add(lblTytul);
		}
		
		btnNewButtonUsun = new JButton("Usuń pozycję");
		btnNewButtonUsun.setBackground(new Color(128, 128, 128));
		btnNewButtonUsun.setForeground(new Color(255, 255, 204));
		btnNewButtonUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usunPozycje(slider.getValue(),katalog);
				slider.setMaximum(katalog.iloscElementow-1);
			}
		});
		btnNewButtonUsun.setBounds(440, 281, 128, 25);
		contentPanel.add(btnNewButtonUsun);
		
		slider = new JSlider();
		slider.setValue(0);
		slider.setMaximum(katalog.iloscElementow-1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				wyswietlPozycje(slider.getValue(),katalog);
				uzupelnijSpinner(spinnerId,slider,PozycjaUsun.this.lblNewLabelInfo);	//uzupelniam wartosciami slidera ->spinner
			}
		});

		slider.setBounds(417, 253, 169, 16);
		contentPanel.add(slider);
		
		lblNewLabelInfo = new JLabel("New label");
		this.lblNewLabelInfo.setText("");
		lblNewLabelInfo.setBounds(169, 56, 417, 57);
		contentPanel.add(lblNewLabelInfo);
		{
			spinnerId = new JTextPane();
			spinnerId.setEditable(false);
			spinnerId.setText("1");
			spinnerId.setBounds(539, 226, 47, 21);
			contentPanel.add(spinnerId);
		}
		{
			lblNewLabelPozycjaNr = new JLabel("Pozycja nr.");
			lblNewLabelPozycjaNr.setForeground(new Color(0, 0, 0));
			lblNewLabelPozycjaNr.setBounds(423, 226, 109, 21);
			contentPanel.add(lblNewLabelPozycjaNr);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(105, 105, 105));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnZatwierdzZamknij = new JButton("Zatwierdź i zamknij");
				btnZatwierdzZamknij.setForeground(new Color(255, 204, 0));
				btnZatwierdzZamknij.setBackground(new Color(255, 0, 0));
				btnZatwierdzZamknij.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Rodzic.katalogGlowny=zamknijZzapisem(katalog,Rodzic.katalogGlowny);
//						Rodzic.katalogGlowny=katalog;
//						PozycjaUsun.this.setVisible(false);
//						PozycjaUsun.this.dispose();
					}
				});
				
				btnUsunWszystko = new JButton("Usuń wszystko");
				btnUsunWszystko.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				btnUsunWszystko.setBackground(new Color(255, 0, 0));
				btnUsunWszystko.setForeground(new Color(0, 0, 0));
				btnUsunWszystko.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						katalog.usunWszystko();	//usuwam wszystko z katalogu
						usunietoWszystko(false);
					}
				});
				buttonPane.add(btnUsunWszystko);
				btnZatwierdzZamknij.setActionCommand("OK");
				buttonPane.add(btnZatwierdzZamknij);
				getRootPane().setDefaultButton(btnZatwierdzZamknij);
			}
			{
				cancelButton = new JButton("Anuluj");
				cancelButton.setBackground(new Color(255, 153, 102));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PozycjaUsun.this.setVisible(false);
						PozycjaUsun.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		if(katalog.iloscElementow>0)
			wyswietlPozycje(0,katalog);
		else
			usunietoWszystko(true);	//w przeciwnym wypadku wyswietl ze wszystko puste, true zeby ukryl guzik zapisz
		//i zamknij
	}
	/* Metoda wczytuje katalog i wyswietla odpowiednia pozycje */
	private void wyswietlPozycje(int numerPozycji,KatalogPozycji katalog){
		if((numerPozycji<katalog.iloscElementow)&&(numerPozycji>=0)){
			this.lblNewLabel_1Autor.setText("Autor: "+katalog.listaPozycji.get(numerPozycji).autor);
			this.lblTytul.setText("Tytul: "+katalog.listaPozycji.get(numerPozycji).tytul);
			}
	}
	
	/*Metoda usuwa pozycje z katalogu i wyswietlacza automatycznie */
	private void usunPozycje(int nrPozycji,KatalogPozycji katalog){
		katalog.usun(nrPozycji);
		if(katalog.iloscElementow>0)	//jesli jest co wyswietlac to wyswietlam
			wyswietlPozycje(nrPozycji-1,katalog);
		else
			usunietoWszystko(false);//jak nie to ukryj wszystko i spierdalamy stąd, 0 jest do picu tylko
		
	}

	/* Metoda wyswietla wiadomosc ze usunieto wszystko oraz ukrywa co trzeba w oknie 
	 * Flaga jest po to, że jesli otwieram to okno i odrazu katalog jest pusty, to nie ma potrzeby
	 * odslaniania guzika zapisz i zamknij, on jest potrzebny wtedy gdy usunelismy wszystkie pozycje wlasnie
	 * przez to okno :)
	 */
	private void usunietoWszystko(boolean flaga){
		this.lblNewLabelInfo.setText("Katalog pozycji jest pusty!");
		this.lblNewLabel_1Autor.setVisible(false);
		this.lblNewLabelPozycjaNr.setVisible(false);
		this.btnNewButtonUsun.setVisible(false);
		this.slider.setVisible(false);
		this.lblTytul.setVisible(false);
		this.spinnerId.setVisible(false);
		this.btnUsunWszystko.setVisible(false);
		if(flaga==true)
			this.btnZatwierdzZamknij.setVisible(false);
	}
}
