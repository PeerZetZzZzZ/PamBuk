import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class OknoZamykania extends JDialog {

	private final JPanel contentPanel = new JPanel();
	KatalogPozycji katalogPozycji; //wskazuje na katalogPozycji z glownego okna
	PlikKatalogu plikKatalogu; //wskazuje na plikKatalogu z glownego okna
	MainWindow Rodzic;//referencja na rodzica :D
	/**
	 * Create the dialog.
	 */
	public OknoZamykania(MainWindow rodziC,KatalogPozycji kataloG, PlikKatalogu plikKatalogU) {
		katalogPozycji = kataloG;
		plikKatalogu = plikKatalogU;
		Rodzic=rodziC;
		setTitle("PamietaczBukow_Zamknij");
		setBackground(new Color(255, 102, 0));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 102, 51));
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNastpiWyjcieZ = new JLabel("Nastąpi wyjście z programu.");
			lblNastpiWyjcieZ.setForeground(new Color(0, 0, 0));
			lblNastpiWyjcieZ.setBackground(new Color(102, 102, 0));
			lblNastpiWyjcieZ.setFont(new Font("Dialog", Font.BOLD, 16));
			lblNastpiWyjcieZ.setBounds(12, 12, 385, 33);
			contentPanel.add(lblNastpiWyjcieZ);
		}
		
		JLabel lblJeliChceszDokona = new JLabel("Jeśli chcesz dokonać jeszcze jakiś zmian, kliknij");
		lblJeliChceszDokona.setForeground(new Color(0, 0, 0));
		lblJeliChceszDokona.setBounds(12, 57, 349, 15);
		contentPanel.add(lblJeliChceszDokona);
		
		JLabel lblJeliChceszWyj = new JLabel("Jeśli chcesz wyjść bez zapisu, kliknij");
		lblJeliChceszWyj.setForeground(new Color(0, 0, 0));
		lblJeliChceszWyj.setBounds(12, 93, 422, 33);
		contentPanel.add(lblJeliChceszWyj);
		
		JLabel lblJeliChceszZapisa = new JLabel("Jeśli chcesz zapisać bierzące zmiany, kliknij");
		lblJeliChceszZapisa.setForeground(new Color(0, 0, 0));
		lblJeliChceszZapisa.setBounds(12, 148, 318, 15);
		contentPanel.add(lblJeliChceszZapisa);
		
		JLabel label = new JLabel("<-Cofnij");
		label.setForeground(new Color(102, 0, 102));
		label.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 16));
		label.setBounds(364, 57, 70, 15);
		contentPanel.add(label);
		
		JLabel lblNieZapisuj = new JLabel("Nie zapisuj");
		lblNieZapisuj.setForeground(new Color(102, 0, 102));
		lblNieZapisuj.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 16));
		lblNieZapisuj.setBounds(289, 101, 145, 15);
		contentPanel.add(lblNieZapisuj);
		
		JLabel lblZapisz = new JLabel("ZAPISZ");
		lblZapisz.setForeground(new Color(102, 0, 102));
		lblZapisz.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 16));
		lblZapisz.setBounds(347, 147, 87, 15);
		contentPanel.add(lblZapisz);
		
		JLabel lblDzikid = new JLabel("Dzięki :D");
		lblDzikid.setForeground(new Color(0, 0, 0));
		lblDzikid.setBounds(291, 208, 70, 15);
		contentPanel.add(lblDzikid);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 102, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("<- Cofnij");
				okButton.setForeground(new Color(0, 0, 0));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						OknoZamykania.this.dispose();
						Rodzic.setVisible(true);
					}
				});
				okButton.setBackground(new Color(255, 255, 153));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnNieZapisuj = new JButton("Nie zapisuj");
				btnNieZapisuj.setForeground(new Color(0, 0, 0));
				btnNieZapisuj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Rodzic.dispose();//zabijam rodzica i nasze okienko 
						OknoZamykania.this.dispose();
					}
				});
				btnNieZapisuj.setBackground(new Color(255, 153, 51));
				btnNieZapisuj.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				buttonPane.add(btnNieZapisuj);
			}
			{
				JButton cancelButton = new JButton("ZAPISZ");
				cancelButton.setForeground(new Color(255, 255, 0));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						zapiszKatalogDoPliku(katalogPozycji,plikKatalogu);
						Rodzic.dispose();//zamykam rodzica i nasze okienko
						OknoZamykania.this.dispose();
					}
				});
				cancelButton.setBackground(new Color(204, 0, 0));
				cancelButton.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void zapiszKatalogDoPliku(KatalogPozycji katalog,PlikKatalogu plikKatalogU){
		try {
			if(katalog.iloscElementow>0)
				plikKatalogu.zapiszKatalogPelny(katalog);
			else if(katalog.iloscElementow==0)
				plikKatalogu.zapiszKatalogPusty(katalog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
