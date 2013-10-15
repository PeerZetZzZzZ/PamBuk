package uzytkownik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import rest.MainWindow;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import katalog.KatalogPozycji;

public class UzytkownikStatystyki extends Uzytkownik {

	private final JPanel contentPanel = new JPanel();
	private ProfilUzytkownika profilUzytkownika;
	private MainWindow Rodzic;
	private KatalogPozycji katalogGlowny;
	private JLabel labelPrzeczytaneStrony;
	private JButton cancelButton;
	private JButton okButton;
	private int iloscStron=0;//okresla ile stron przeczytal dany uzytkownik
	private int iloscDni=0;	//okresla ile dni spędzil użytkownik na czytaniu
	private JLabel labelLiczbDni;
	/**
	 * Launch the application.
	 */
	public UzytkownikStatystyki(MainWindow rodziC,KatalogPozycji kataloG,ProfilUzytkownika profiL) {
		profilUzytkownika = profiL;
		katalogGlowny=kataloG;
		Rodzic=rodziC;
		setBackground(new Color(102, 102, 102));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblStatystykiUytkownika = new JLabel("Statystyki użytkownika");
		lblStatystykiUytkownika.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 16));
		lblStatystykiUytkownika.setBounds(12, 12, 239, 15);
		contentPanel.add(lblStatystykiUytkownika);
		
		JLabel lblPrzeczytaneStrony = new JLabel("Przeczytane strony:");
		lblPrzeczytaneStrony.setBounds(12, 39, 160, 15);
		contentPanel.add(lblPrzeczytaneStrony);
		
		labelPrzeczytaneStrony = new JLabel("");
		labelPrzeczytaneStrony.setBounds(181, 39, 219, 15);
		contentPanel.add(labelPrzeczytaneStrony);
		
		JLabel lblSumaSpdzonychDni = new JLabel("Suma spędzonych dni:");
		lblSumaSpdzonychDni.setBounds(12, 71, 160, 15);
		contentPanel.add(lblSumaSpdzonychDni);
		
		labelLiczbDni = new JLabel("");
		labelLiczbDni.setBounds(191, 71, 70, 15);
		contentPanel.add(labelLiczbDni);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(102, 102, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UzytkownikStatystyki.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		iloscStron=liczStrony(katalogGlowny,iloscStron);
		iloscDni=liczDni(katalogGlowny,iloscDni);
		ustawPola();
	}
	/*	Metoda liczy wszystkie przeczytane strony dla danego użytkownika
	 * DOBRA JUŻ WIA! by Koralgoll
	 */
	private int liczStrony(KatalogPozycji katalog,int licznikStron){
		licznikStron=0;
		for(int i=0;i<katalog.iloscElementow;i++)
			licznikStron+=katalog.zczytajPozycje(i).liczbaStron;	//sumuje strony każdej pozycji
		return licznikStron;
	}
	/*	Metoda ustawia pola danego okna wg podanych wartosci 
	 * 
	 */
	private void ustawPola(){
		labelPrzeczytaneStrony.setText(Integer.toString(iloscStron));
		labelLiczbDni.setText(Integer.toString(iloscDni));
	}
	private int liczDni(KatalogPozycji katalog,int licznikDni){
		licznikDni=0;
		for(int i=0;i<katalog.iloscElementow;i++)
			licznikDni+=Rodzic.wyznaczDate(katalog, i);	//sumuje strony każdej pozycji
		return licznikDni;
	}
}
