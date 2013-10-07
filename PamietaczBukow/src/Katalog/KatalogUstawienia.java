package Katalog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rest.MainWindow;


public class KatalogUstawienia extends Katalog{

	private final JPanel contentPanel = new JPanel();
	private MainWindow Rodzic;
	private KatalogPozycji katalogGlowny;	//referencja na katalogGlowny z MainWindow
	/**
	 * Create the dialog.
	 */
	public KatalogUstawienia(MainWindow rodziC,KatalogPozycji kataloG) {
		setTitle("Ustawienia Katalogu");
		Rodzic=rodziC;
		katalogGlowny=kataloG;
		setBackground(Color.GRAY);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Label labelUstawieniaKatalogu = new Label("Ustawienia Katalogu");
		labelUstawieniaKatalogu.setForeground(new Color(0, 0, 0));
		labelUstawieniaKatalogu.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		labelUstawieniaKatalogu.setBounds(10, 10, 195, 21);
		contentPanel.add(labelUstawieniaKatalogu);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zapisz");
				okButton.setForeground(new Color(255, 204, 0));
				okButton.setBackground(new Color(255, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Anuluj");
				cancelButton.setBackground(new Color(255, 153, 102));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
